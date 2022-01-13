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
import com.xsjt.order.entity.Menu;
import com.xsjt.order.mapper.one.MenuMapper;
import com.xsjt.order.service.IMenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author chenhb27
 * @Date 2021/12/27 14:17
 * @Des 菜单表 服务实现类
 */
@Slf4j
@AllArgsConstructor
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Override
    public RetResult<String> save(Menu menu) throws ServiceException {
        try {
            menu.setCreateTime(DateUtil.getTime());
            menu.setUpdateTime(DateUtil.getTime());
            Integer insert = baseMapper.insert(menu);
            if (insert > 0) {
                return new RetResult<String>().setCode(RetCode.SUCCESS);
            } else {
                return new RetResult<String>().setCode(RetCode.FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<String> update(Menu menu) throws ServiceException {
        try {
            menu.setUpdateTime(DateUtil.getTime());
            if (baseMapper.updateById(menu) > 0) {
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
            Wrapper<Menu> wrapper = new EntityWrapper<Menu>().eq("id", id);
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
            Menu menu = baseMapper.selectById(id);
            if (Func.isNotEmpty(menu) && menu.getIsDeleted() == 0) {
                Map map = JsonUtil.entityToMap(menu);
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
            Page<Menu> page = new PageFactory<Menu>().defaultPage(query.getCurrent(), query.getSize(), null, null);
            Wrapper<Menu> wrapper = new EntityWrapper<Menu>().eq("is_deleted", 0);
            Page selectPage = selectPage(page,wrapper);
            Page<Map> mapPage = JsonUtil.entitysToMaps(selectPage);
            RetResult<Page> msg = new RetResult<Page>().setCode(RetCode.SUCCESS).setData(mapPage);
            return msg;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RetResult<List> getAll() throws ServiceException {
        try{
            Wrapper<Menu> wrapper = new EntityWrapper<Menu>().eq("is_deleted", 0);
            List<Menu> menuList = selectList(wrapper);
            List<Map> maps = JsonUtil.entitysToMaps(menuList);
            for (int i = 0; i < maps.size(); i++) {
                Map menu = maps.get(i);
                menu.put("updateTime",DateUtil.formatDateTime(new Date((Long)menu.get("updateTime"))));
                menu.put("pid",menu.get("pid")+"");
            }
            RetResult<List> msg = new RetResult<List>().setCode(RetCode.SUCCESS).setData(maps);
            return msg;
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }
}
