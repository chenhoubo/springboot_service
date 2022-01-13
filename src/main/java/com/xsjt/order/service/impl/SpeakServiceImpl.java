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
import com.xsjt.order.entity.Speak;
import com.xsjt.order.mapper.one.SpeakMapper;
import com.xsjt.order.service.ISpeakService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author chenhb27
 * @Date 2021/12/27 14:17
 * @Des 评论 服务实现类
 */
@Slf4j
@AllArgsConstructor
@Service
public class SpeakServiceImpl extends ServiceImpl<SpeakMapper, Speak> implements ISpeakService {


    @Override
    public RetResult<String> save(Speak speak) throws ServiceException {
        try {
            speak.setCreateTime(DateUtil.getTime());
            speak.setUpdateTime(DateUtil.getTime());
            if (baseMapper.insert(speak) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> update(Speak speak) throws ServiceException {
        try {
            speak.setUpdateTime(DateUtil.getTime());
            if (baseMapper.updateById(speak) > 0) {
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
            Wrapper<Speak> wrapper = new EntityWrapper<Speak>().eq("id", id);
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
            Speak speak = baseMapper.selectById(id);
            if (Func.isNotEmpty(speak) && speak.getIsDeleted() == 0) {
                Map map = JsonUtil.entityToMap(speak);
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
            Page<Speak> page = new PageFactory<Speak>().defaultPage(query.getCurrent(), query.getSize(), null, null);
            Wrapper<Speak> wrapper = new EntityWrapper<Speak>().eq("is_deleted", 0);
            Page selectPage = selectPage(page,wrapper);
            Page<Map> mapPage = JsonUtil.entitysToMaps(selectPage);
            return new RetResult<Page>().setCode(RetCode.SUCCESS).setData(mapPage);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<List> all() throws ServiceException {
        try {
            Wrapper<Speak> wrapper = new EntityWrapper<Speak>().eq("is_deleted", 0);
            wrapper.orderBy("create_time",false);
            List<Speak> list = selectList(wrapper);
            List<Map> maps = JsonUtil.entitysToMaps(list);
            return new RetResult<List>().setCode(RetCode.SUCCESS).setData(maps);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
