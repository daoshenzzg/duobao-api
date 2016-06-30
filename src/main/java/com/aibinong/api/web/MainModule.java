package com.aibinong.api.web;

import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import com.aibinong.api.web.filter.ValidationFilter;

@IocBy(type = ComboIocProvider.class,
	args = { "*org.nutz.ioc.loader.json.JsonLoader",
	         "ioc/ioc.js", 
	         "*org.nutz.ioc.loader.annotation.AnnotationIocLoader",
	         "com.aibinong.api", 
	         "*org.nutz.aop.interceptor.ioc.TransIocLoader" 
	}
)
@SetupBy(value = MainSetup.class)
@Modules(scanPackage = true)
@Filters(@By(type = ValidationFilter.class))
@Fail("redirect:/api/exception")
public class MainModule {

}
