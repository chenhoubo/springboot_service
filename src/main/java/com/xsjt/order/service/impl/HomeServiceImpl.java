package com.xsjt.order.service.impl;

import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.ret.RetCode;
import com.xsjt.core.ret.RetResult;
import com.xsjt.core.util.tool.DateUtil;
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
public class HomeServiceImpl implements IHomeService {

    @Override
    public RetResult<Map> getCardsData() throws ServiceException {
        try {
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("vistors",79757);
            hashMap.put("message",767);
            hashMap.put("order",79757);
            hashMap.put("profit",51904);
            return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(hashMap);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Map> getLineData() throws ServiceException {
        try {
            HashMap<Object, Object> hashMap = new HashMap<>();
            int [] inPrice=new int[]{98644, 65357, 94297, 66756, 65562, 80035};
            int [] outPrice=new int[]{98455, 80578, 25750, 74347, 11775, 35402};
            hashMap.put("inPrice",inPrice);
            hashMap.put("outPrice",outPrice);
            return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(hashMap);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Map> getTableList() throws ServiceException {
        try {
            HashMap<Object, Object> result = new HashMap<>();
            ArrayList<Map> list = new ArrayList<>();
            HashMap<Object, Object> map1 = new HashMap<>();
            map1.put("id","52000019970429475X");
            map1.put("52000019970429475X","O@X$s(rsr");
            map1.put("price",8844);
            map1.put("quantity",35);
            map1.put("status",1);
            list.add(map1);
            HashMap<Object, Object> map2 = new HashMap<>(map1);
            map2.put("status",2);
            list.add(map2);
            HashMap<Object, Object> map3 = new HashMap<>(map1);
            map2.put("status",0);
            list.add(map3);
            HashMap<Object, Object> map4 = new HashMap<>(map1);
            map4.put("status",2);
            list.add(map4);
            HashMap<Object, Object> map5 = new HashMap<>(map1);
            map5.put("status",0);
            list.add(map5);
            HashMap<Object, Object> map6 = new HashMap<>(map1);
            map6.put("status",1);
            list.add(map6);
            result.put("tableList",list);
            return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(result);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Map> getBarData() throws ServiceException {
        try {
            HashMap<Object, Object> hashMap = new HashMap<>();
            int [] y2017=new int[]{112130, 253645, 213714, 252304, 187140, 173509, 248815, 147393, 263584, 254905, 166321, 281561};
            int [] y2018=new int[]{346557, 349106, 343430, 306515, 287410, 233259, 272205, 193890, 215902, 150678, 308462, 163273};
            int [] y2019=new int[]{304916, 296686, 390368, 338526, 261428, 349908, 230785, 399947, 305069, 275095, 395099, 231594};
            hashMap.put("y2017",y2017);
            hashMap.put("y2018",y2018);
            hashMap.put("y2019",y2019);
            return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(hashMap);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
