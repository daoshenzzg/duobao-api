package com.aibinong.api.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接口超时拦截器
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年6月30日 下午4:27:10
 */
@IocBean
public class TimeoutInterceptor implements MethodInterceptor {
	private final static Logger LOG = LoggerFactory.getLogger(TimeoutInterceptor.class);

	@Override
	public void filter(InterceptorChain chain) throws Throwable {
		long start = System.currentTimeMillis();
		chain.doChain(); // 继续执行其他拦截器
		long end = System.currentTimeMillis();
		long time = end - start;

//		if (time > Constants.API_TIMEOUT) {
			// TODO
			if (LOG.isDebugEnabled()) {
				HttpServletRequest request = Mvcs.getReq();
				LOG.debug("'{}' response time {} ms.", new Object[] { request.getRequestURI(), time });
			}
//		}
	}
}
