package com.xsjt.order.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.entity.User;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author harriss
 * @since 2021-09-07
 */
public interface IUserService extends IService<User> {

//    基本增删改查接口
    RetResult<String> saveUser(User user) throws ServiceException;

    RetResult<String> updateUser(User user) throws ServiceException;

    RetResult<String> deleteUser(Long id) throws ServiceException;

    RetResult<Map> selectUser(Long id) throws ServiceException;

    RetResult<User> selectUserByUsername(String username) throws ServiceException;

    RetResult<Page> pageUser(Query query) throws ServiceException;

//    业务接口
    RetResult<String> login(Map<String,String> user) throws ServiceException;

}
