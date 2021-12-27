package com.xsjt.order.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.entity.Role;
import com.xsjt.order.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "角色", description = "角色")
@RestController
@RequestMapping("/role")
@AllArgsConstructor
@Slf4j
public class RoleController {

    IRoleService roleService;

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "添加", notes="添加")
    public RetResult<String> save(@RequestBody Map map) {
        log.info("save  start......:{}",map);
        Role role = JsonUtil.mapToEntity(map, Role.class);
        RetResult<String> result = roleService.save(role);
        log.info("save end......response:{}",result);
        return result;
    }
    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value = "更新", notes="更新")
    public RetResult<String> update(@RequestBody Map map) {
        log.info("update  start......:{}",map);
        Role role = JsonUtil.mapToEntity(map, Role.class);
        RetResult<String> result = roleService.update(role);
        log.info("update end......response:{}",result);
        return result;
    }

    @PostMapping("/page")
    @ResponseBody
    @ApiOperation(value = "分页获取", notes="分页获取")
    public RetResult<Page> page(@RequestBody Query query) {
        log.info("page  start......:{}",query);
        RetResult<Page> result = roleService.page(query);
        log.info("page end......response:{}",result);
        return result;
    }

    @GetMapping("/getOne")
    @ResponseBody
    @ApiOperation(value = "获取", notes="获取")
    public RetResult<Map> getOne(Long id) {
        log.info("getUser  start......:{}",id);
        RetResult<Map> result = roleService.select(id);
        log.info("getUser end......response:{}",result);
        return result;
    }

    @GetMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除", notes="删除")
    public RetResult<String> delete(Long id) {
        log.info("delete  start......:{}",id);
        RetResult<String> result = roleService.delete(id);
        log.info("delete end......response:{}",result);
        return result;
    }
}
