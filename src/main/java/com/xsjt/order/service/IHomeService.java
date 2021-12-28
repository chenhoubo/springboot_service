package com.xsjt.order.service;

import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.ret.RetResult;

import java.util.Map;

/**
 * @author Harriss
 * @Date 2021/12/28 15:09
 * @Des 首页数据信息接口
 */
public interface IHomeService {

    RetResult<Map> getCardsData() throws ServiceException;

    RetResult<Map> getLineData() throws ServiceException;

    RetResult<Map> getTableList() throws ServiceException;

    RetResult<Map> getBarData() throws ServiceException;

}
