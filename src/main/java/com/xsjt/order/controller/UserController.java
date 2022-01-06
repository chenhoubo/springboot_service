package com.xsjt.order.controller;

import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetCode;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.entity.User;
import com.xsjt.order.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "用户服务", description = "用户服务")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    IUserService userService;

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登录", notes="登录接口")
    public RetResult login(@RequestBody Map<String,String> user) {
        log.info("login  start......:{}",user);
        RetResult result = userService.login(user);
        log.info("login end......response:{}",result);
        return result;
    }

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "插入用户", notes="插入用户")
    public RetResult save(@RequestBody Map map) {
        log.info("save  start......:{}",map);
        RetResult result = userService.saveUser(map);
        log.info("save end......response:{}",result);
        return result;
    }
    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value = "更新用户", notes="分页获取用户")
    public RetResult update(@RequestBody Map map) {
        log.info("update  start......:{}",map);
        User user = JsonUtil.mapToEntity(map, User.class);
        RetResult result = userService.updateUser(user);
        log.info("update end......response:{}",result);
        return result;
    }

    @PostMapping("/page")
    @ResponseBody
    @ApiOperation(value = "获取用户", notes="分页获取用户")
    public RetResult page(@RequestBody Query query) {
        log.info("page  start......:{}",query);
        RetResult result = userService.pageUser(query);
        log.info("page end......response:{}",result);
        return result;
    }

    @GetMapping("/getOne")
    @ApiOperation(value = "获取用户", notes="获取用户")
    public RetResult getOne(Long id) {
        log.info("getUser  start......:{}",id);
        RetResult result = userService.selectUser(id);
        log.info("getUser end......response:{}",result);
        return result;
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除用户", notes="删除用户")
    public RetResult delete(Long id) {
        log.info("delete  start......:{}",id);
        RetResult result = userService.deleteUser(id);
        log.info("delete end......response:{}",result);
        return result;
    }

    @GetMapping("/getInfo")
    @ApiOperation(value = "获取当前登录信息", notes="获取当前登录信息")
    public RetResult getInfo(User user) {
        log.info("getInfo  start......:{}",user);
        RetResult<Map> info = userService.getInfo(user);
        log.info("getInfo end......response:{}",info);
        return info;
    }

    @GetMapping("/resetPas")
    @ApiOperation(value = "获取当前登录信息", notes="获取当前登录信息")
    public RetResult resetPas(Long id) {
        log.info("resetPas  start......:{}",id);
        RetResult<String> info = userService.resetPas(id);
        log.info("resetPas end......response:{}",info);
        return info;
    }
}
