package com.xsjt.order.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.entity.WorkPool;

import java.util.List;
import java.util.Map;

/**
 * @author Harriss
 * @Date 2022/2/15 17:00
 * @Des
 */
public interface IWorkPoolService extends IService<WorkPool> {

//    基本增删改查接口
    RetResult<String> saveWork(Map map) throws ServiceException;

    RetResult<String> updateWork(WorkPool workPool) throws ServiceException;

    RetResult<String> deleteWork(Long id) throws ServiceException;

    RetResult<Map> selectWork(Long id) throws ServiceException;

    RetResult<Page> pageWork(Query query) throws ServiceException;

}
