package com.xsjt.order.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xsjt.order.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author chenhb27
 * @Date 2021/12/23 11:29
 * @Des 用户表
 */
@Data
@NoArgsConstructor
@TableName("user")
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户表")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键")
//    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * json
     */
    private String json;

}
