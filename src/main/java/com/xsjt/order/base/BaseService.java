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

import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 基础业务接口
 *
 * @param <T>
 * @author Harriss
 */
public interface BaseService<T> extends IService<T> {

	/**
	 * 逻辑删除
	 *
	 * @param ids id集合(逗号分隔)
	 * @return boolean
	 */
	boolean deleteLogic(List<Long> ids);

}
