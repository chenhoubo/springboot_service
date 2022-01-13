package com.xsjt.order.service;

import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;

import java.util.Map;

/**
 * @author Harriss
 * @Date 2021/12/28 15:52
 * @Des 模拟页面数据
 */
public interface IAnalogService {

    RetResult<Map> getPageData1(Query query) throws ServiceException;

    RetResult<Map> getPageData2() throws ServiceException;

}
