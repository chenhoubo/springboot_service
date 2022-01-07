package com.xsjt.order.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xsjt.core.config.UserInfoProperties;
import com.xsjt.core.distributed.annotation.DataSource;
import com.xsjt.core.distributed.enums.DataSourceEnum;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.core.page.PageFactory;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetCode;
import com.xsjt.core.ret.RetResult;
import com.xsjt.core.util.Func;
import com.xsjt.core.util.RedisUtil;
import com.xsjt.core.util.TokenUtil;
import com.xsjt.core.util.tool.AesUtil;
import com.xsjt.core.util.tool.DateUtil;
import com.xsjt.order.entity.Product;
import com.xsjt.order.entity.Role;
import com.xsjt.order.entity.User;
import com.xsjt.order.mapper.one.ProductMapper;
import com.xsjt.order.mapper.one.UserMapper;
import com.xsjt.order.service.IProductService;
import com.xsjt.order.service.IRoleService;
import com.xsjt.order.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author harriss
 * @since 2021-09-07
 */
@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    IRoleService roleService;
    ProductMapper productMapper;

    private UserInfoProperties userInfoProperties;
    private RedisUtil redisUtil;

    @Override
    @DataSource(DataSourceEnum.DB1)
    public RetResult<String> saveUser(Map map) throws ServiceException {
        try {
            String username = (String)map.get("username");
            List<User> userList = baseMapper.selectByUserName(username);
            if(userList.size() > 0){
                return new RetResult<String>().setCode(RetCode.FAIL).setMsg("用户已注册");
            }
            String key = AesUtil.genAesKey();
            String str = Func.toStr(map.get("password"), "123456");
            String password = AesUtil.encrypt(str, key);
            map.put("ekey",key);
            map.put("password",password);
            User user = JsonUtil.mapToEntity(map, User.class);
            user.setCreateTime(DateUtil.getTime());
            user.setUpdateTime(DateUtil.getTime());
            if (baseMapper.insert(user) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> updateUser(User user) throws ServiceException {
        try {
            user.setUpdateTime(DateUtil.getTime());
            if (baseMapper.updateById(user) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> deleteUser(Long id) throws ServiceException {
        try {
            Wrapper<User> wrapper = new EntityWrapper<User>().eq("id", id);
            if (baseMapper.updateForSet("is_deleted=1",wrapper) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Map> selectUser(Long id) throws ServiceException {
        try {
            User user = baseMapper.selectById(id);
            if (Func.isNotEmpty(user)) {
                if(user.getIsDeleted() == 1){
                    return new RetResult<Map>().setCode(RetCode.UNAUTHZ).setMsg("用户已被删除");
                }else{
                    Map map = JsonUtil.entityToMap(user);
                    return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(map);
                }
            } else {
                return new RetResult<Map>().setCode(RetCode.FAIL).setData(null);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<User> selectUserByUsername(String username) throws ServiceException {
        try {
            List<User> userList = baseMapper.selectByUserName(username);
            return new RetResult<User>().setCode(RetCode.SUCCESS).setData(userList.get(0));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Page> pageUser(Query query) throws ServiceException {
        try {
            Page<User> page = new PageFactory<User>().defaultPage(query.getCurrent(), query.getSize(), null, null);
            Wrapper<User> wrapper = new EntityWrapper<User>()
                    .eq("is_deleted", 0);
            if(Func.isNotEmpty(query.getStatus())){
                wrapper.eq("status",Func.toInt(query.getStatus(), 0));
            }
            if(Func.isNotEmpty(query.getId())){
                wrapper.eq("id",query.getId());
            }
            Page selectPage = selectPage(page,wrapper);
            Page<Map> mapPage = JsonUtil.entitysToMaps(selectPage);
            RetResult msg = new RetResult<Page>().setCode(RetCode.SUCCESS).setData(mapPage);
            return msg;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> login(Map<String,String> map) throws ServiceException {
        try {
            String username = map.get("username");
            List<User> userList = baseMapper.selectByUserName(username);
            User user = userList.get(0);
            if(Func.isEmpty(user)){
                return new RetResult<String>().setCode(RetCode.UNAUTHZ);
            }
            if(user.getIsDeleted() == 1 || user.getStatus() == 1 ){
                return new RetResult<String>().setCode(RetCode.UNAUTHZ).setMsg("用户已被删除/禁用");
            }

            JSONObject object = new JSONObject(user.getJson());
            String ekey = object.getStr("ekey");
            String webPassword = AesUtil.encrypt(map.get("password"), ekey);
            String dbPassword = object.getStr("password");
            if(webPassword.equals(dbPassword)){
                String token = TokenUtil.getToken(user);
//                访问次数加1
                Long count = object.getLong("count") + 1;
                object.remove("count");
                object.putOnce("count",count);
                user.setJson(JsonUtil.toJson(object));
                user.setUpdateTime(DateUtil.getTime());
                baseMapper.updateById(user);
                return new RetResult<String>().setCode(RetCode.SUCCESS).setData(token);
            }else{
                return new RetResult<String>().setCode(RetCode.UNAUTHEN).setMsg("密码错误");
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Map> getInfo(User user) throws ServiceException {
        try {
            Map map = JsonUtil.entityToMap(user);
            map.remove("password");
            Long roleId = Long.parseLong((String)map.get("role"));
            Role role = roleService.selectById(roleId);
            Map r = JsonUtil.entityToMap(role);
            map.put("menus",r.get("menus"));
            map.put("rolename",r.get("name"));
            map.put("rolevalue",r.get("value"));
            RetResult result = new RetResult<Map>().setCode(RetCode.SUCCESS).setData(map);
            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> resetPas(Long id) throws ServiceException {
        try {
            User baseUser = baseMapper.selectById(id);
            if(Func.isEmpty(baseUser)){
                return new RetResult<String>().setCode(RetCode.FAIL).setMsg("用户不存在");
            }
            Map map = JsonUtil.entityToMap(baseUser);
            String key = AesUtil.genAesKey();
            String password = AesUtil.encrypt("123456", key);
            map.put("ekey",key);
            map.put("password",password);
            User user = JsonUtil.mapToEntity(map, User.class);
            user.setUpdateTime(DateUtil.getTime());
            if (baseMapper.updateById(user) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<List> getUserByRole(Long id) throws ServiceException {
        try {
            List<User> users = baseMapper.selectByRoleId(id);
            List<Map> maps = JsonUtil.entitysToMaps(users);
            return new RetResult<List>().setCode(RetCode.SUCCESS).setData(maps);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<List> getMsgList(User user) throws ServiceException {
        try {
            List<Product> productList = productMapper.selectByExamine(user.getId());
            List<Map> maps = new ArrayList<>();
            for (int i = 0; i < productList.size(); i++) {
                HashMap<String, Object> map = new HashMap<>();
                Product product = productList.get(i);
                String content = "新增商品：" + product.getName() + ",需要你审核";
                map.put("content",content);
                map.put("id",product.getId());
                map.put("time",DateUtil.formatDateTime(new Date(product.getUpdateTime())) );
                maps.add(map);
            }
            RetResult result = new RetResult<List>().setCode(RetCode.SUCCESS).setData(maps);
            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
