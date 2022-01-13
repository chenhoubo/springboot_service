/**
 * Copyright (c) 2018-2028,  Harriss   (chenhoubo123@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xsjt.core.redis;

/**
 * 缓存名
 *
 * @author Harriss
 */
public interface CacheNames {

	String NOTICE_ONE = "springboot:notice:one:";

	String DICT_VALUE = "springboot:dict:value";
	String DICT_LIST = "springboot:dict:list";

	String CAPTCHA_KEY = "springboot:user:auth:value";

	String OFFICIAL_ACCESS_TOKEN_KEY = "springboot:official:accesstoken:value";

	String APPLET_ACCESS_TOKEN_KEY = "springboot:applet:accesstoken:value";

	String KEY_ACCESS_TOKEN="springboot:qiwei:accesstoken:value";


	/** 缓存更新频率，单位秒 */
	int TIME_OUT_TWO_HOUR=  7200;

}
