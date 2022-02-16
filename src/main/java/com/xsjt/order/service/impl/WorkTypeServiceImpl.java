package com.xsjt.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xsjt.core.config.UserInfoProperties;
import com.xsjt.core.distributed.annotation.DataSource;
import com.xsjt.core.distributed.enums.DataSourceEnum;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.core.page.PageFactory;
import com.xsjt.core.page.Query;
import com.xsjt.core.ret.RetCode;
import com.xsjt.core.ret.RetResult;
import com.xsjt.core.util.Func;
import com.xsjt.core.util.RedisUtil;
import com.xsjt.core.util.tool.DateUtil;
import com.xsjt.order.entity.WorkType;
import com.xsjt.order.mapper.one.ProductMapper;
import com.xsjt.order.mapper.one.WorkTypeMapper;
import com.xsjt.order.service.IRoleService;
import com.xsjt.order.service.IWorkTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Harriss
 * @Date 2022/2/15 17:02
 * @Des
 */
@Slf4j
@AllArgsConstructor
@Service
public class WorkTypeServiceImpl extends ServiceImpl<WorkTypeMapper, WorkType> implements IWorkTypeService {

    IRoleService roleService;
    ProductMapper productMapper;

    private UserInfoProperties userInfoProperties;
    private RedisUtil redisUtil;

    @Override
    @DataSource(DataSourceEnum.DB1)
    public RetResult<String> saveWorkType(Map map) throws ServiceException {
        try {
            WorkType workType = JsonUtil.mapToEntity(map, WorkType.class);
            workType.setCreateTime(DateUtil.getTime());
            workType.setUpdateTime(DateUtil.getTime());
            if (baseMapper.insert(workType) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> updateWorkType(WorkType workType) throws ServiceException {
        try {
            workType.setUpdateTime(DateUtil.getTime());
            if (baseMapper.updateById(workType) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> deleteWorkType(Long id) throws ServiceException {
        try {
            Wrapper<WorkType> wrapper = new EntityWrapper<WorkType>().eq("id", id);
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
    public RetResult<Map> selectWorkType(Long id) throws ServiceException {
        try {
            WorkType workType = baseMapper.selectById(id);
            if (Func.isNotEmpty(workType)) {
                if(workType.getIsDeleted() == 1){
                    return new RetResult<Map>().setCode(RetCode.UNAUTHZ).setMsg("已被删除");
                }else{
                    Map map = JsonUtil.entityToMap(workType);
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
    public RetResult<Page> pageWorkType(Query query) throws ServiceException {
        try {
            Page<WorkType> page = new PageFactory<WorkType>().defaultPage(query.getCurrent(), query.getSize(), null, null);
            Wrapper<WorkType> wrapper = new EntityWrapper<WorkType>()
                    .eq("is_deleted", 0);
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
}
