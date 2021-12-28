package com.xsjt.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.core.page.PageFactory;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetCode;
import com.xsjt.core.ret.RetResult;
import com.xsjt.core.util.Func;
import com.xsjt.core.util.tool.DateUtil;
import com.xsjt.order.entity.Role;
import com.xsjt.order.mapper.one.RoleMapper;
import com.xsjt.order.service.IRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author chenhb27
 * @Date 2021/12/27 14:17
 * @Des 菜单表 服务实现类
 */
@Slf4j
@AllArgsConstructor
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {


    @Override
    public RetResult<String> save(Role role) throws ServiceException {
        try {
            role.setCreateTime(DateUtil.getTime());
            role.setUpdateTime(DateUtil.getTime());
            if (baseMapper.insert(role) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> update(Role role) throws ServiceException {
        try {
            role.setUpdateTime(DateUtil.getTime());
            if (baseMapper.updateById(role) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> delete(Long id) throws ServiceException {
        try {
            Wrapper<Role> wrapper = new EntityWrapper<Role>().eq("id", id);
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
    public RetResult<Map> select(Long id) throws ServiceException {
        try {
            Role role = baseMapper.selectById(id);
            if (Func.isNotEmpty(role) && role.getIsDeleted() == 0) {
                Map map = JsonUtil.entityToMap(role);
                return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(map);
            } else {
                return new RetResult<Map>().setCode(RetCode.FAIL).setData(null);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Page> page(Query query) throws ServiceException {
        try {
            Page<Role> page = new PageFactory<Role>().defaultPage(query.getCurrent(), query.getSize(), null, null);
            Wrapper<Role> wrapper = new EntityWrapper<Role>().eq("is_deleted", 0);
            Page selectPage = selectPage(page,wrapper);
            Page<Map> mapPage = JsonUtil.entitysToMaps(selectPage);
            RetResult<Page> msg = new RetResult<Page>().setCode(RetCode.SUCCESS).setData(mapPage);
            return msg;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Map> getRoles() throws ServiceException {
        try {
            HashMap<Object, Object> result = new HashMap<>();
            ArrayList<Map> list = new ArrayList<>();
            HashMap<Object, Object> map1 = new HashMap<>();
            map1.put("description","I am Admin");
            map1.put("key","admin");
            String [] pages1=new String[]{"Home","Dashbord","Driver","Driver-index","Permission","PageUser","PageAdmin","Roles","Table","BaseTable","ComplexTable","Icons","Icons-index","Components","Sldie-yz","Upload","Carousel","Echarts","Sldie-chart","Dynamic-chart","Map-chart","Excel","Excel-out","Excel-in","Mutiheader-out","Error","Page404","Github","NavTest","Nav1","Nav2","Nav2-1","Nav2-2","Nav2-2-1","Nav2-2-2"};
            map1.put("pages",pages1);
            list.add(map1);
            HashMap<Object, Object> map2 = new HashMap<>(map1);
            map2.put("description","I am User");
            map2.put("key","user");
            String [] pages2=new String[]{"Home","Dashbord","Driver","Driver-index","PageUser","Table","BaseTable","ComplexTable","Icons","Icons-index","Components","Sldie-yz","Upload","Carousel","Echarts","Sldie-chart","Dynamic-chart","Map-chart","Excel","Excel-out","Excel-in","Mutiheader-out","Error","Page404","Github","NavTest","Nav1","Nav2","Nav2-1","Nav2-2","Nav2-2-1","Nav2-2-2"};
            map2.put("pages",pages2);
            list.add(map2);
            HashMap<Object, Object> map3 = new HashMap<>(map1);
            map3.put("description","I am Example");
            map3.put("key","example");
            String [] pages3=new String[]{"Home","Dashbord","Driver","Driver-index","PageUser","BaseTable","Sldie-yz","Echarts","Sldie-chart","Dynamic-chart","Map-chart","Mutiheader-out","Error","Page404","Github"};
            map3.put("pages",pages3);
            list.add(map3);
            result.put("allRoles",list);
            return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(result);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
