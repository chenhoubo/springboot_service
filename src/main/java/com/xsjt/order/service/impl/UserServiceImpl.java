package com.xsjt.order.service.impl;

import cn.hutool.json.JSONObject;
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
import com.xsjt.order.entity.User;
import com.xsjt.order.mapper.db1.UserMapper;
import com.xsjt.order.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    private UserInfoProperties userInfoProperties;

    private RedisUtil redisUtil;


    @Override
    @DataSource(DataSourceEnum.DB1)
    public RetResult saveUser(User user) throws ServiceException {
        try {
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
    public RetResult updateUser(User user) throws ServiceException {
        try {
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
    public RetResult deleteUser(Long id) throws ServiceException {
        try {
            if (baseMapper.deleteById(id) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult selectUser(Long id) throws ServiceException {
        try {
            User user = baseMapper.selectById(id);
            if (Func.isNotEmpty(user)) {
                Map map = JsonUtil.entityToMap(user);
                return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(map);
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
            User user = baseMapper.selectByUserName(username);
            return new RetResult<User>().setCode(RetCode.SUCCESS).setData(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult pageUser(Query query) throws ServiceException {
        try {
            Page<User> page = new PageFactory<User>().defaultPage(query.getCurrent(), query.getSize(), null, null);
            Page selectPage = selectPage(page);
            Page<Map> mapPage = JsonUtil.entitysToMaps(selectPage);
            RetResult msg = new RetResult<Page>().setCode(RetCode.SUCCESS).setData(mapPage);
            return msg;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult login(Map<String,String> map) throws ServiceException {
        try {
            User user = baseMapper.selectByUserName(map.get("username"));
            JSONObject object = new JSONObject(user.getJson());
            String password = object.getStr("password");
            if(Func.isEmpty(user)){
                return new RetResult<String>().setCode(RetCode.UNAUTHZ);
            }else if(map.get("password").equals(password)){
                String token = TokenUtil.getToken(user);
                return new RetResult<String>().setCode(RetCode.SUCCESS).setData(token);
            }else{
                return new RetResult<String>().setCode(RetCode.UNAUTHEN).setMsg("密码错误");
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
