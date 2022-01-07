package com.xsjt.order.mapper.one;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xsjt.order.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author chenhb27
 * @Date 2021/12/27 13:55
 * @Des 产品表 Mapper 接口
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> selectByExamine(Long id);
}
