package com.xsjt.order.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.service.IAnalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "模拟数据", description = "模拟数据")
@RestController
@RequestMapping("/analog")
@AllArgsConstructor
@Slf4j
public class AnalogController {

    IAnalogService analogService;

    @PostMapping("/getPageData1")
    @ApiOperation(value = "获取分页表格信息", notes="获取分页表格信息")
    public RetResult<Map> getPageData1(@RequestBody Query query) {
        log.info("getPageData1  start......");
        RetResult<Map> result = analogService.getPageData1(query);
        log.info("getPageData1 end......response:{}",result);
        return result;
    }

    @GetMapping("/getPageData2")
    @ApiOperation(value = "获取分页表格信息", notes="获取分页表格信息")
    public RetResult<Map> getPageData2() {
        log.info("getPageData2  start......");
        RetResult<Map> result = analogService.getPageData2();
        log.info("getPageData2 end......response:{}",result);
        return result;
    }

}
