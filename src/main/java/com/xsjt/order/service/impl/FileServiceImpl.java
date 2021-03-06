package com.xsjt.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.core.page.PageFactory;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetCode;
import com.xsjt.core.ret.RetResult;
import com.xsjt.core.util.Func;
import com.xsjt.core.util.tool.DateUtil;
import com.xsjt.core.util.tool.FileUtil;
import com.xsjt.order.entity.SysFile;
import com.xsjt.order.mapper.one.SysFileMapper;
import com.xsjt.order.service.IFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class FileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements IFileService {

    @Override
    public RetResult<String> saveSysFile(Map map) throws ServiceException {
        try {
            SysFile sysFile = JsonUtil.mapToEntity(map, SysFile.class);
            sysFile.setCreateTime(DateUtil.getTime());
            sysFile.setUpdateTime(DateUtil.getTime());
            if (baseMapper.insert(sysFile) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> updateSysFile(SysFile sysFile) throws ServiceException {
        try {
            sysFile.setUpdateTime(DateUtil.getTime());
            if (baseMapper.updateById(sysFile) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> deleteSysFile(Long id) throws ServiceException {
        try {
            Wrapper<SysFile> wrapper = new EntityWrapper<SysFile>().eq("id", id);
            if (baseMapper.updateForSet("is_deleted=1",wrapper) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Map> selectSysFile(Long id) throws ServiceException {
        try {
            SysFile sysFile = baseMapper.selectById(id);
            if (Func.isNotEmpty(sysFile)) {
                if(sysFile.getIsDeleted() == 1){
                    return new RetResult<Map>().setCode(RetCode.UNAUTHZ).setMsg("??????????????????");
                }else{
                    Map map = JsonUtil.entityToMap(sysFile);
                    return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(map);
                }
            } else {
                return new RetResult<Map>().setCode(RetCode.FAIL).setData(null);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Page> pageSysFile(Query query) throws ServiceException {
        try {
            Page<SysFile> page = new PageFactory<SysFile>().defaultPage(query.getCurrent(), query.getSize(), null, null);
            Wrapper<SysFile> wrapper = new EntityWrapper<SysFile>()
                    .eq("is_deleted", 0);
            wrapper.orderBy("create_time",false);
            if(Func.isNotEmpty(query.getStatus())){
                wrapper.eq("status",Func.toInt(query.getStatus(), 0));
            }
            if(Func.isNotEmpty(query.getId())){
                wrapper.eq("id",query.getId());
            }
            Page selectPage = selectPage(page,wrapper);
            Page<Map> mapPage = JsonUtil.entitysToMaps(selectPage);
            RetResult msg = new RetResult<Page>().setCode(RetCode.SUCCESS).setData(mapPage);
            return msg;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> upload(MultipartFile file, String folder,String desc) throws ServiceException {
//        ?????????????????????????????????
        String filePath = System.getProperty("user.dir") + File.separator +"fileData" + File.separator + folder;
        FileUtil.createDir(filePath);
        String filename = file.getOriginalFilename();
        String uploadPath = filePath + File.separator +filename;
        log.info("------>>>>>>????????????:{} <<<<<<------", uploadPath);
        File targetFile = new File(uploadPath);
        FileUtil.toFile(file, targetFile);
        FileOutputStream fileOutputStream = null;
        try {
            //????????????
            fileOutputStream = new FileOutputStream(targetFile);
            int copy = IOUtils.copy(file.getInputStream(), fileOutputStream);
            if(copy != -1){
                HashMap<String, String> map = new HashMap<>();
                map.put("filePath",uploadPath);
                map.put("fileFolder",folder);
                map.put("name",filename);
                map.put("desc",desc);
                long size = file.getSize() / 1024; //kb
                map.put("size",size+"kb");
                String suffix = filename.substring(filename.lastIndexOf(".") + 1);
                map.put("fileType",suffix);
                SysFile sysFile = JsonUtil.mapToEntity(map, SysFile.class);
                sysFile.setCreateTime(DateUtil.getTime());
                sysFile.setUpdateTime(DateUtil.getTime());
                if(baseMapper.insert(sysFile) > 0){
                    log.info("------>>>>>>??????????????????!<<<<<<------");
                }else{
                    log.error("------>>>>>>??????????????????!<<<<<<------");
                }
            }else{
                log.error("------>>>>>>??????????????????!<<<<<<------");
            }
        } catch (IOException e) {
            throw new ServiceException("??????????????????");
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                log.error("", e);
            }
        }
        return new RetResult<String>().setCode(RetCode.SUCCESS).setData("?????????????????????");
    }

    @Override
    public void download(String folder, String filename, HttpServletResponse response) throws ServiceException {
        String filePath = System.getProperty("user.dir") + File.separator +"fileData" + File.separator + folder + File.separator + filename;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new ServiceException("???????????????");
        }
        response.setContentType("application/octet-stream;");
        response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
        response.addHeader("Content-Length", "" + file.length());
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (IOException e) {
            throw new ServiceException("??????????????????");
        }
    }

    @Override
    public RetResult<String> deleteFile(String folder, String filename) throws ServiceException {
        try {
            boolean flag = false;
            if(Func.isEmpty(filename)){
                String filePath = System.getProperty("user.dir") + File.separator +"fileData" + File.separator + folder;
                flag = FileUtil.deleteDir(new File(filePath));
            }else{
                String filePath = System.getProperty("user.dir") + File.separator +"fileData" + File.separator + folder + File.separator + filename;
                flag = FileUtil.deleteFile(filePath);
            }
            if (flag) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
