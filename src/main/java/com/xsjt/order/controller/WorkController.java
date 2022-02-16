package com.xsjt.order.controller;

import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.entity.WorkPool;
import com.xsjt.order.service.IWorkPoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "工单服务", description = "工单服务")
@RestController
@RequestMapping("/work")
@AllArgsConstructor
@Slf4j
public class WorkController {

    IWorkPoolService iWorkPoolService;

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "新增工单", notes="新增工单")
    public RetResult save(@RequestBody Map map) {
        log.info("save  start......:{}",map);
        RetResult result = iWorkPoolService.saveWork(map);
        log.info("save end......response:{}",result);
        return result;
    }
    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value = "更新工单", notes="更新工单")
    public RetResult update(@RequestBody Map map) {
        log.info("update  start......:{}",map);
        WorkPool workPool = JsonUtil.mapToEntity(map, WorkPool.class);
        RetResult result = iWorkPoolService.updateWork(workPool);
        log.info("update end......response:{}",result);
        return result;
    }

    @PostMapping("/page")
    @ResponseBody
    @ApiOperation(value = "获取工单", notes="分页获取工单")
    public RetResult page(@RequestBody Query query) {
        log.info("page  start......:{}",query);
        RetResult result = iWorkPoolService.pageWork(query);
        log.info("page end......response:{}",result);
        return result;
    }

    @GetMapping("/getOne")
    @ApiOperation(value = "获取工单", notes="获取工单")
    public RetResult getOne(Long id) {
        log.info("getUser  start......:{}",id);
        RetResult result = iWorkPoolService.selectWork(id);
        log.info("getUser end......response:{}",result);
        return result;
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除工单", notes="删除工单")
    public RetResult delete(Long id) {
        log.info("delete  start......:{}",id);
        RetResult result = iWorkPoolService.deleteWork(id);
        log.info("delete end......response:{}",result);
        return result;
    }

}
