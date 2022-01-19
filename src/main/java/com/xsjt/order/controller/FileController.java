package com.xsjt.order.controller;

import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.entity.SysFile;
import com.xsjt.order.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(value = "文件服务", description = "文件服务")
@RestController
@RequestMapping("/file")
@AllArgsConstructor
@Slf4j
public class FileController {

    IFileService iFileService;

    @RequestMapping("/upload")
    @ApiOperation(value = "上传文件", notes="上传文件")
    public RetResult upload(@RequestParam("uploadFile") MultipartFile uploadFile,String folder, String desc) {
        log.info("upload  start......folder:{},desc:{}",folder,desc);
        RetResult<String> upload = iFileService.upload(uploadFile,folder,desc);
        log.info("upload end......response:{}",upload);
        return upload;
    }

    @GetMapping("/remove")
    @ApiOperation(value = "删除目录/文件", notes="删除目录/文件")
    public RetResult remove(String folder,String filename) {
        log.info("remove  start......folder:{},filename:{}",folder,filename);
        RetResult result = iFileService.deleteFile(folder,filename);
        log.info("remove end......response:{}",result);
        return result;
    }

    @GetMapping("/download")
    @ApiOperation(value = "下载文件", notes="下载文件")
    public void download(String folder,String filename, HttpServletResponse response) {
        log.info("download  start......folder:{},filename:{}",folder,filename);
        iFileService.download(folder,filename,response);
        log.info("download end......");
    }

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "插入文件信息", notes="插入文件信息")
    public RetResult save(@RequestBody Map map) {
        log.info("save  start......:{}",map);
        RetResult result = iFileService.saveSysFile(map);
        log.info("save end......response:{}",result);
        return result;
    }
    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value = "更新文件信息", notes="更新文件信息")
    public RetResult update(@RequestBody Map map) {
        log.info("update  start......:{}",map);
        SysFile sysFile = JsonUtil.mapToEntity(map, SysFile.class);
        RetResult result = iFileService.updateSysFile(sysFile);
        log.info("update end......response:{}",result);
        return result;
    }

    @PostMapping("/page")
    @ResponseBody
    @ApiOperation(value = "获取文件信息", notes="分页获取文件信息")
    public RetResult page(@RequestBody Query query) {
        log.info("page  start......:{}",query);
        RetResult result = iFileService.pageSysFile(query);
        log.info("page end......response:{}",result);
        return result;
    }

    @GetMapping("/getOne")
    @ApiOperation(value = "获取文件信息", notes="获取文件信息")
    public RetResult getOne(Long id) {
        log.info("getUser  start......:{}",id);
        RetResult result = iFileService.selectSysFile(id);
        log.info("getUser end......response:{}",result);
        return result;
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除文件信息", notes="删除文件信息")
    public RetResult delete(Long id) {
        log.info("delete  start......:{}",id);
        RetResult result = iFileService.deleteSysFile(id);
        log.info("delete end......response:{}",result);
        return result;
    }
}
