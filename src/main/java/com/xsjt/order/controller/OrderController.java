package com.xsjt.order.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.entity.Order;
import com.xsjt.order.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "订单", description = "订单")
@RestController
@RequestMapping("/order")
@AllArgsConstructor
@Slf4j
public class OrderController {

    IOrderService orderService;

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "添加", notes="添加")
    public RetResult<String> save(@RequestBody Map map) {
        log.info("save  start......:{}",map);
        Order order = JsonUtil.mapToEntity(map, Order.class);
        RetResult<String> result = orderService.save(order);
        log.info("save end......response:{}",result);
        return result;
    }
    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value = "更新", notes="更新")
    public RetResult<String> update(@RequestBody Map map) {
        log.info("update  start......:{}",map);
        Order order = JsonUtil.mapToEntity(map, Order.class);
        RetResult<String> result = orderService.update(order);
        log.info("update end......response:{}",result);
        return result;
    }

    @PostMapping("/page")
    @ResponseBody
    @ApiOperation(value = "分页获取", notes="分页获取")
    public RetResult<Page> page(@RequestBody Query query) {
        log.info("page  start......:{}",query);
        RetResult<Page> result = orderService.page(query);
        log.info("page end......response:{}",result);
        return result;
    }

    @GetMapping("/getOne")
    @ResponseBody
    @ApiOperation(value = "获取", notes="获取")
    public RetResult<Map> getOne(Long id) {
        log.info("getUser  start......:{}",id);
        RetResult<Map> result = orderService.select(id);
        log.info("getUser end......response:{}",result);
        return result;
    }

    @GetMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除", notes="删除")
    public RetResult<String> delete(Long id) {
        log.info("delete  start......:{}",id);
        RetResult<String> result = orderService.delete(id);
        log.info("delete end......response:{}",result);
        return result;
    }
}
