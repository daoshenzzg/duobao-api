package com.aibinong.api.web;

import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import com.aibinong.api.web.filter.ValidationFilter;

@Modules(scanPackage = true)
@IocBy(type = ComboIocProvider.class, 
	args = { 
		"*org.nutz.ioc.loader.json.JsonLoader", 
		"ioc/", "*org.nutz.ioc.loader.annotation.AnnotationIocLoader",
		"com.aibinong.api", 
		"*org.nutz.aop.interceptor.ioc.TransIocLoader" 
	},
	init = { "dao", "redisDao", "mqProducter" }
)
@SetupBy(value = MainSetup.class)
@Encoding(input = "UTF-8", output = "UTF-8")
@Filters(@By(type = ValidationFilter.class))
@Fail("forward:/api/exception")
public class MainModule {

}
