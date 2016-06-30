package com.aibinong.api.pojo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by ouwa on 16/6/28.
 */
public class BaseDO {
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
