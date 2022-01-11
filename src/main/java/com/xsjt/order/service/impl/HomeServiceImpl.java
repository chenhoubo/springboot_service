package com.xsjt.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.core.ret.RetCode;
import com.xsjt.core.ret.RetResult;
import com.xsjt.core.util.Func;
import com.xsjt.core.util.tool.DateUtil;
import com.xsjt.order.entity.Product;
import com.xsjt.order.mapper.one.ProductMapper;
import com.xsjt.order.mapper.one.UserMapper;
import com.xsjt.order.service.IHomeService;
import com.xsjt.order.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    UserMapper userMapper;
    ProductMapper productMapper;

    @Override
    public RetResult<Map> getCardsData() throws ServiceException {
        try {
            HashMap<Object, Object> hashMap = new HashMap<>();
            Long vistors = userMapper.selectTotalVisits();
            hashMap.put("vistors",vistors);
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
    public RetResult<Map> getTableList(Integer status, Integer type) throws ServiceException {
        try {
            List<Product> productList;
            if(Func.isEmpty(status)){
                EntityWrapper<Product> wrapper = new EntityWrapper<>();
                wrapper.eq("is_deleted", 0);
                productList = productMapper.selectList(wrapper);
            }else{
                productList = productMapper.selectByTypeAndStatus(status,type);
            }
            List<Map> mapList = JsonUtil.entitysToMaps(productList);
            HashMap<Object, Object> result = new HashMap<>();
            result.put("tableList",mapList);
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
