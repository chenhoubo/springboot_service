package com.xsjt.order.controller;

import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.entity.WorkType;
import com.xsjt.order.service.IWorkTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "工单类型", description = "工单类型")
@RestController
@RequestMapping("/work/type")
@AllArgsConstructor
@Slf4j
public class WorkTypeController {

    IWorkTypeService iWorkTypeService;

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "新增", notes="新增")
    public RetResult save(@RequestBody Map map) {
        log.info("save  start......:{}",map);
        RetResult result = iWorkTypeService.saveWorkType(map);
        log.info("save end......response:{}",result);
        return result;
    }
    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value = "更新", notes="更新")
    public RetResult update(@RequestBody Map map) {
        log.info("update  start......:{}",map);
        WorkType workType = JsonUtil.mapToEntity(map, WorkType.class);
        RetResult result = iWorkTypeService.updateWorkType(workType);
        log.info("update end......response:{}",result);
        return result;
    }

    @PostMapping("/page")
    @ResponseBody
    @ApiOperation(value = "获取", notes="分页获取")
    public RetResult page(@RequestBody Query query) {
        log.info("page  start......:{}",query);
        RetResult result = iWorkTypeService.pageWorkType(query);
        log.info("page end......response:{}",result);
        return result;
    }

    @GetMapping("/getOne")
    @ApiOperation(value = "获取", notes="获取")
    public RetResult getOne(Long id) {
        log.info("getUser  start......:{}",id);
        RetResult result = iWorkTypeService.selectWorkType(id);
        log.info("getUser end......response:{}",result);
        return result;
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除", notes="删除")
    public RetResult delete(Long id) {
        log.info("delete  start......:{}",id);
        RetResult result = iWorkTypeService.deleteWorkType(id);
        log.info("delete end......response:{}",result);
        return result;
    }

}
