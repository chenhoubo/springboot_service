package com.xsjt.core.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Bean属性
 *
 * @author Harriss
 */
@Getter
@AllArgsConstructor
public class BeanProperty {
	private final String name;
	private final Class<?> type;
}
