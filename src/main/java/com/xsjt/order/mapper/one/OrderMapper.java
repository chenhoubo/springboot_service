package com.xsjt.order.mapper.one;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xsjt.order.entity.Order;
import org.springframework.stereotype.Repository;



/**
 * @author chenhb27
 * @Date 2021/12/27 13:55
 * @Des 订单表 Mapper 接口
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {
    Long selectTotalOrder();
    Double selectTotalProfit();
}
