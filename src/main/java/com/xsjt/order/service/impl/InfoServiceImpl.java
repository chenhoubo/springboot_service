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
import com.xsjt.order.entity.Info;
import com.xsjt.order.mapper.one.InfoMapper;
import com.xsjt.order.service.IInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author chenhb27
 * @Date 2021/12/27 14:17
 * @Des 项目信息表 服务实现类
 */
@Slf4j
@AllArgsConstructor
@Service
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements IInfoService {


    @Override
    public RetResult<String> save(Info info) throws ServiceException {
        try {
            info.setCreateTime(DateUtil.getTime());
            info.setUpdateTime(DateUtil.getTime());
            if (baseMapper.insert(info) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> update(Info info) throws ServiceException {
        try {
            info.setUpdateTime(DateUtil.getTime());
            if (baseMapper.updateById(info) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> delete(Long id) throws ServiceException {
        try {
            Wrapper<Info> wrapper = new EntityWrapper<Info>().eq("id", id);
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
    public RetResult<Map> select(Long id) throws ServiceException {
        try {
            Info info = baseMapper.selectById(id);
            if (Func.isNotEmpty(info) && info.getIsDeleted() == 0) {
                Map map = JsonUtil.entityToMap(info);
                return new RetResult<Map>().setCode(RetCode.SUCCESS).setData(map);
            } else {
                return new RetResult<Map>().setCode(RetCode.FAIL).setData(null);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<Page> page(Query query) throws ServiceException {
        try {
            Page<Info> page = new PageFactory<Info>().defaultPage(query.getCurrent(), query.getSize(), null, null);
            Wrapper<Info> wrapper = new EntityWrapper<Info>().eq("is_deleted", 0);
            if(Func.isNotEmpty(query.getStatus())){
                wrapper.eq("status",Func.toInt(query.getStatus(), 0));
            }
            if(Func.isNotEmpty(query.getId())){
                wrapper.eq("id",query.getId());
            }
            Page selectPage = selectPage(page,wrapper);
            Page<Map> mapPage = JsonUtil.entitysToMaps(selectPage);
            RetResult<Page> msg = new RetResult<Page>().setCode(RetCode.SUCCESS).setData(mapPage);
            return msg;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<List> available() throws ServiceException {
        try {
            Wrapper<Info> wrapper = new EntityWrapper<Info>().eq("is_deleted", 0);
            wrapper.eq("status",0);
            List<Info> list = selectList(wrapper);
            List<Map> maps = JsonUtil.entitysToMaps(list);
            RetResult<List> msg = new RetResult<List>().setCode(RetCode.SUCCESS).setData(maps);
            return msg;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
