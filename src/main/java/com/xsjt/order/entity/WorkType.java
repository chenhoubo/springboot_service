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
 * @author Harriss
 * @Date 2022/2/15 16:50
 * @Des 工单类型
 */
@Data
@NoArgsConstructor
@TableName("work_type")
@EqualsAndHashCode(callSuper = false)
@ApiModel("工单类型")
public class WorkType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键")
//    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    /**
     * json
     */
    private String json;

}
