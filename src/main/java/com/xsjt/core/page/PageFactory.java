package com.xsjt.core.page;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baomidou.mybatisplus.plugins.Page;
import com.xsjt.core.exception.ServiceException;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;

/**
 * ClassName: PageFactory
 * 分页参数.可在此创建多个分页方式.接收不同前台发送的分页参数
 * date: 2021年10月18日 上午9:51:57
 * @author Harriss
 * @version @param <T>
 */
public class PageFactory<T> {

    public Page<T> defaultPage(Integer current,Integer size,String key,String dec){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> params = ServletUtil.getParamMap(request);

        if (current == null || size == null){
            throw new ServiceException("缺少分页参数");
        }
        // 排序字段
        String sort = key;
        // asc或desc,升序或降序
        String order = dec;

        Order.ASC.getDes();
        if (StrUtil.isBlank(sort)){
            Page<T> page = new Page<>(current,size);
            page.setOpenSort(false);
            return page;
        }else {
            Page<T> page = new Page<>(current,size,sort);
            boolean asc = Order.ASC.getDes().equals(order);
            page.setAsc(asc);
            return page;
        }
    }
}
