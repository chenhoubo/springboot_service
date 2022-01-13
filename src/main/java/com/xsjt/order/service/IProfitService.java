package com.xsjt.order.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.entity.Default;

import java.util.Map;

/**
 * @author chenhb27
 * @Date 2021/12/27 14:18
 * @Des 收益汇总 服务类
 */
public interface IProfitService extends IService<Default> {

//    基本增删改查接口
    RetResult<String> save(Default profit) throws ServiceException;

    RetResult<String> update(Default profit) throws ServiceException;

    RetResult<String> delete(Long id) throws ServiceException;

    RetResult<Map> select(Long id) throws ServiceException;

    RetResult<Page> page(Query query) throws ServiceException;

}
