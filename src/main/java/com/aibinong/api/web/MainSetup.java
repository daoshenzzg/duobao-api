package com.aibinong.api.web;

import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.api.util.TemplateUtil;

public class MainSetup implements Setup {
	private final static Logger LOG = LoggerFactory.getLogger(MainSetup.class);

	@Override
	public void init(NutConfig conf) {
		LOG.debug("MainSetup init start...");
		// 初始化模板到内存
		TemplateUtil.initTemplate();
	}

	@Override
	public void destroy(NutConfig nutConfig) {

	}
}
