package com.xsjt.core.convert;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.StringValueResolver;

/**
 * 类型 转换 服务，添加了 IEnum 转换
 *
 * @author Harriss
 */
public class BladeConversionService extends ApplicationConversionService {
	@Nullable
	private static volatile BladeConversionService SHARED_INSTANCE;

	public BladeConversionService() {
		this(null);
	}

	public BladeConversionService(@Nullable StringValueResolver embeddedValueResolver) {
		super(embeddedValueResolver);
		super.addConverter(new EnumToStringConverter());
		super.addConverter(new StringToEnumConverter());
	}

	public static GenericConversionService getInstance() {
		BladeConversionService sharedInstance = BladeConversionService.SHARED_INSTANCE;
		if (sharedInstance == null) {
			synchronized (BladeConversionService.class) {
				sharedInstance = BladeConversionService.SHARED_INSTANCE;
				if (sharedInstance == null) {
					sharedInstance = new BladeConversionService();
					BladeConversionService.SHARED_INSTANCE = sharedInstance;
				}
			}
		}
		return sharedInstance;
	}

}
