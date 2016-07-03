package com.aibinong.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明在Module的方法上，用来表示该方法访问是否需要验证签名
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年7月3日 下午6:02:12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface Allow {
	 /**
	  * 是否放行
	  */
	boolean value() default true;
}