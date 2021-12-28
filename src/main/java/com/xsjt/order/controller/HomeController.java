package com.xsjt.order.controller;

import com.xsjt.core.ret.RetResult;
import com.xsjt.order.service.IHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "首页数据", description = "菜单")
@RestController
@RequestMapping("/home")
@AllArgsConstructor
@Slf4j
public class HomeController {

    IHomeService homeService;

    @GetMapping("/getCardsData")
    @ApiOperation(value = "获取卡片信息", notes="获取卡片信息")
    public RetResult<Map> getCardsData() {
        log.info("getCardsData  start......");
        RetResult<Map> result = homeService.getCardsData();
        log.info("getCardsData end......response:{}",result);
        return result;
    }

    @GetMapping("/getLineData")
    @ApiOperation(value = "获取曲线图信息", notes="获取曲线图信息")
    public RetResult<Map> getLineData() {
        log.info("getLineData  start......");
        RetResult<Map> result = homeService.getLineData();
        log.info("getLineData end......response:{}",result);
        return result;
    }
    @GetMapping("/getTableList")
    @ApiOperation(value = "获取表格信息", notes="获取表格信息")
    public RetResult<Map> getTableList() {
        log.info("getTableList  start......");
        RetResult<Map> result = homeService.getTableList();
        log.info("getTableList end......response:{}",result);
        return result;
    }
    @GetMapping("/getBarData")
    @ApiOperation(value = "获取柱状图信息", notes="获取柱状图信息")
    public RetResult<Map> getBarData() {
        log.info("getBarData  start......");
        RetResult<Map> result = homeService.getBarData();
        log.info("getBarData end......response:{}",result);
        return result;
    }
}
