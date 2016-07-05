package com.aibinong.api.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.lang.Lang;
import org.nutz.mvc.Mvcs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.api.web.Constants;

/**
 * 接口超时拦截器
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年6月30日 下午4:27:10
 */
public class TimeoutInterceptor implements MethodInterceptor {
	private final static Logger LOG = LoggerFactory.getLogger("TIMEOUT");

	@Override
	public void filter(InterceptorChain chain) throws Throwable {
		long start = System.currentTimeMillis();
		chain.doChain(); // 继续执行其他拦截器
		long end = System.currentTimeMillis();
		long time = end - start;

		if (time > Constants.API_TIMEOUT) {
			HttpServletRequest request = Mvcs.getReq();
			String ip = Lang.getIP(request);
			String uri = request.getRequestURI();
			String queryString = request.getQueryString();
			// 时间|IP|URL|耗时(毫秒)|
			StringBuilder sb = new StringBuilder();
			sb.append(ip).append("|");
			sb.append(uri).append("|");
			sb.append(queryString).append("|");
			sb.append(time).append("|");
			LOG.info(sb.toString());
		}
	}
}
