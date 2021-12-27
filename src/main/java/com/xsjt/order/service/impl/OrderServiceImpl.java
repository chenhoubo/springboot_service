package com.xsjt.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.core.page.PageFactory;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetCode;
import com.xsjt.core.ret.RetResult;
import com.xsjt.core.util.Func;
import com.xsjt.core.util.tool.DateUtil;
import com.xsjt.order.entity.Order;
import com.xsjt.order.mapper.one.OrderMapper;
import com.xsjt.order.service.IOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * @author chenhb27
 * @Date 2021/12/27 14:17
 * @Des 订单 服务实现类
 */
@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {


    @Override
    public RetResult<String> save(Order order) throws ServiceException {
        try {
            order.setCreateTime(DateUtil.getTime());
            order.setUpdateTime(DateUtil.getTime());
            if (baseMapper.insert(order) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> update(Order order) throws ServiceException {
        try {
            order.setUpdateTime(DateUtil.getTime());
            if (baseMapper.updateById(order) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> delete(Long id) throws ServiceException {
        try {
            Wrapper<Order> wrapper = new EntityWrapper<Order>().eq("id", id);
            if (baseMapper.updateForSet("is_deleted=1",wrapper) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Map> select(Long id) throws ServiceException {
        try {
            Order order = baseMapper.selectById(id);
            if (Func.isNotEmpty(order) && order.getIsDeleted() == 0) {
                Map map = JsonUtil.entityToMap(order);
                return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(map);
            } else {
                return new RetResult<Map>().setCode(RetCode.FAIL).setData(null);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Page> page(Query query) throws ServiceException {
        try {
            Page<Order> page = new PageFactory<Order>().defaultPage(query.getCurrent(), query.getSize(), null, null);
            Wrapper<Order> wrapper = new EntityWrapper<Order>().eq("is_deleted", 0);
            Page selectPage = selectPage(page,wrapper);
            Page<Map> mapPage = JsonUtil.entitysToMaps(selectPage);
            RetResult<Page> msg = new RetResult<Page>().setCode(RetCode.SUCCESS).setData(mapPage);
            return msg;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
