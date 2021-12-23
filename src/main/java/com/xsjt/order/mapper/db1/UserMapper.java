package com.xsjt.order.mapper.db1;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xsjt.order.entity.User;
import org.springframework.stereotype.Repository;

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

    User selectByUserName(String username);
}
