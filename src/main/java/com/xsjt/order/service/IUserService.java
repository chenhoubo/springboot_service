package com.xsjt.order.service;

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
    RetResult saveUser(User user) throws ServiceException;

    RetResult updateUser(User user) throws ServiceException;

    RetResult deleteUser(Long id) throws ServiceException;

    RetResult selectUser(Long id) throws ServiceException;

    RetResult<User> selectUserByUsername(String username) throws ServiceException;

    RetResult pageUser(Query query) throws ServiceException;

//    业务接口
    RetResult login(Map<String,String> user) throws ServiceException;

}
