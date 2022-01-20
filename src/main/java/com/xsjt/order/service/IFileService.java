package com.xsjt.order.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetResult;
import com.xsjt.order.entity.SysFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author chenhb27
 * @Date 2022/1/17 11:25
 * @Des 文件服务接口
 */
public interface IFileService {

    //    基本增删改查接口
    RetResult<String> saveSysFile(Map map) throws ServiceException;

    RetResult<String> updateSysFile(SysFile user) throws ServiceException;

    RetResult<String> deleteSysFile(Long id) throws ServiceException;

    RetResult<Map> selectSysFile(Long id) throws ServiceException;

    RetResult<Page> pageSysFile(Query query) throws ServiceException;

//    业务逻辑接口
    RetResult<String> upload(MultipartFile file, String folder, String desc) throws ServiceException;
    void download(String folder, String filename, HttpServletResponse response) throws ServiceException;
    RetResult<String> deleteFile(String folder,String filename) throws ServiceException;

}
