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
import com.xsjt.order.entity.Default;
import com.xsjt.order.mapper.one.ProfitMapper;
import com.xsjt.order.service.IProfitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * @author chenhb27
 * @Date 2021/12/27 14:17
 * @Des 收益汇总 服务实现类
 */
@Slf4j
@AllArgsConstructor
@Service
public class ProfitServiceImpl extends ServiceImpl<ProfitMapper, Default> implements IProfitService {


    @Override
    public RetResult<String> save(Default profit) throws ServiceException {
        try {
            profit.setCreateTime(DateUtil.getTime());
            profit.setUpdateTime(DateUtil.getTime());
            if (baseMapper.insert(profit) > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> update(Default profit) throws ServiceException {
        try {
            profit.setUpdateTime(DateUtil.getTime());
            if (baseMapper.updateById(profit) > 0) {
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
            Wrapper<Default> wrapper = new EntityWrapper<Default>().eq("id", id);
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
            Default profit = baseMapper.selectById(id);
            if (Func.isNotEmpty(profit) && profit.getIsDeleted() == 0) {
                Map map = JsonUtil.entityToMap(profit);
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
            Page<Default> page = new PageFactory<Default>().defaultPage(query.getCurrent(), query.getSize(), null, null);
            Wrapper<Default> wrapper = new EntityWrapper<Default>().eq("is_deleted", 0);
            Page selectPage = selectPage(page,wrapper);
            Page<Map> mapPage = JsonUtil.entitysToMaps(selectPage);
            RetResult<Page> msg = new RetResult<Page>().setCode(RetCode.SUCCESS).setData(mapPage);
            return msg;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
