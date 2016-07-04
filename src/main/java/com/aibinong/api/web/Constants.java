package com.aibinong.api.web;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.mvc.Mvcs;

/**
 * 常量类
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年6月29日 下午4:54:16
 */
public class Constants {
	public final static String SIGN_KEY = "sign";
	public final static String TOKEN_KEY = "access_token";

	public final static PropertiesProxy CONFIG = Mvcs.getIoc().get(PropertiesProxy.class, "conf");
	public final static String SECRET = CONFIG.get("static.secret");
	public final static String ENVIRONMENT = CONFIG.get("static.environment");
	public final static long API_TIMEOUT = CONFIG.getLong("static.apiTimeout");

	// 阿里云ONS 支付 MQ
	public final static String PAY_TOPIC = CONFIG.get("pay.topic");
	public final static String PAY_PRODUCER_ID = CONFIG.get("pay.producer.id");
}
