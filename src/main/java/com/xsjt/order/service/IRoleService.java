package com.xsjt.order.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @author chenhb27
 * @Date 2021/12/27 14:18
 * @Des 角色 服务类
 */
public interface IRoleService extends IService<Role> {

//    基本增删改查接口
    RetResult<String> save(Role role) throws ServiceException;

    RetResult<String> update(Role role) throws ServiceException;

    RetResult<String> delete(Long id) throws ServiceException;

    RetResult<Map> select(Long id) throws ServiceException;

    RetResult<Page> page(Query query) throws ServiceException;

//业务接口
    RetResult<List> getRoles() throws ServiceException;

}
