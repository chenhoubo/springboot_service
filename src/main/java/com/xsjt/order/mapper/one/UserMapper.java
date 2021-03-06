package com.xsjt.order.mapper.one;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xsjt.order.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author harriss
 * @since 2021-09-07
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    List<User> selectByUserName(String username);

    Long selectTotalVisits();

    List<User> selectByRoleId(Long id);

}
