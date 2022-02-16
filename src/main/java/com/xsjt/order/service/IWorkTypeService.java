package com.xsjt.order.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.entity.WorkType;

import java.util.List;
import java.util.Map;

/**
 * @author Harriss
 * @Date 2022/2/15 17:01
 * @Des
 */
public interface IWorkTypeService extends IService<WorkType> {

//    基本增删改查接口
    RetResult<String> saveWorkType(Map map) throws ServiceException;

    RetResult<String> updateWorkType(WorkType user) throws ServiceException;

    RetResult<String> deleteWorkType(Long id) throws ServiceException;

    RetResult<Map> selectWorkType(Long id) throws ServiceException;

    RetResult<Page> pageWorkType(Query query) throws ServiceException;


}
