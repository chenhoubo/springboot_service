package com.xsjt.order.service.impl;

import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetCode;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.service.IAnalogService;
import com.xsjt.order.service.IHomeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Harriss
 * @Date 2021/12/28 15:12
 * @Des 获取首页信息接口服务实现类
 */
@Slf4j
@AllArgsConstructor
@Service
public class AnalogServiceImpl implements IAnalogService {


    @Override
    public RetResult<Map> getPageData1(Query query) throws ServiceException {
        try {
            HashMap<Object, Object> result = new HashMap<>();
            ArrayList<Map> list = new ArrayList<>();
            for(int i = 0 ; i < 10 ; i++){
                HashMap<Object, Object> map = new HashMap<>();
                map.put("id","52000019970429475X"+i);
                map.put("name","商品名称"+i);
                map.put("num",(26*i+8)/3);
                map.put("price",14*i+4);
                map.put("quantity",14*i+4);
                map.put("status",i%3);
                list.add(map);
            }
            result.put("tableList",list);
            result.put("total",1000);
            return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(result);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Map> getPageData2() throws ServiceException {
        try {
            HashMap<Object, Object> result = new HashMap<>();
            ArrayList<Map> list = new ArrayList<>();
            for(int i = 0 ; i < 100 ; i++){
                HashMap<Object, Object> map = new HashMap<>();
                map.put("address","河北省 石家庄市"+i);
                map.put("id",i+1);
                map.put("name","张"+i);
                map.put("order","110000198204122344"+i);
                map.put("phone","13542645232");
                map.put("status",i%3);
                map.put("time","1973-07-21 13:56:44");
                list.add(map);
            }
            result.put("tableList",list);
            result.put("total",100);
            return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(result);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
