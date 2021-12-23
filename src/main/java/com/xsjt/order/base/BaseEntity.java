/**
 * Copyright (c) 2018-2028,  Harriss   (chenhoubo123@163.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xsjt.order.base;


import com.baomidou.mybatisplus.annotations.TableLogic;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础实体类
 * @author Harriss
 */
@Data
public class BaseEntity implements Serializable {

	/**
	 * 创建人
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "创建人")
	private String createBy;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Long createTime;

	/**
	 * 更新人
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "更新人")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "关闭时间")
	private Long endTime;

	/**
	 * 状态[1:正常]
	 */
	@ApiModelProperty(value = "业务状态")
	private Integer status;

	/**
	 * 状态[0:未删除,1:删除]
	 */
	@TableLogic
	@ApiModelProperty(value = "是否已删除")
	private Integer isDeleted;
}
