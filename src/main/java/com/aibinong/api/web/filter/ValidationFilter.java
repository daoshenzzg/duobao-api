package com.aibinong.api.web.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.lang.Lang;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ForwardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.api.web.Constants;

/**
 * 签名校验
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年6月29日 下午4:48:00
 */
public class ValidationFilter implements ActionFilter {
	private final static Logger LOG = LoggerFactory.getLogger(ValidationFilter.class);

	private final static Set<String> ALLOW_URI = new HashSet<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("/api/reload_template");
			add("/api/invalid_sign");
			add("/api/invalid_token");
			add("/api/invalid_exception");
		}
	};

	@Override
	public View match(ActionContext actionContext) {
		if ("release".equals(Constants.ENVIRONMENT)) {
			HttpServletRequest request = actionContext.getRequest();
			String uri = request.getRequestURI();
			for (String allowUri : ALLOW_URI) {
				if (uri.indexOf(allowUri) > 0) {
					return null;
				}
			}

			String sign = request.getParameter(Constants.SIGN_KEY);
			if (StringUtils.isBlank(sign)) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("invalid sign");
				}
				return new ForwardView("/api/invalid_sign");
			}
			String token = request.getParameter(Constants.TOKEN_KEY);
			if (StringUtils.isBlank(token)) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("invalid token");
				}
				return new ForwardView("/api/invalid_token");
			}

			@SuppressWarnings("unchecked")
			Map<String, String[]> paramMap = request.getParameterMap();
			String[] keyVals = new String[paramMap.size() - 1];
			int i = 0;
			for (String key : paramMap.keySet()) {
				if (Constants.SIGN_KEY.equals(key)) {
					continue;
				}
				String val = paramMap.get(key)[0];
				keyVals[i++] = key + val;
			}
			// 参数按字典排序
			Arrays.sort(keyVals);

			// 连接参数名与参数值,并在末尾尾加上secret
			StringBuilder sb = new StringBuilder();
			for (String keyVal : keyVals) {
				sb.append(keyVal);
			}
			sb.append(Constants.SECRET);

			// sign校验
			String md5 = Lang.md5(sb.toString());

			if (LOG.isDebugEnabled()) {
				LOG.debug("md5='{}', sign='{}'", new Object[] { md5, sign });
			}
			if (!md5.equalsIgnoreCase(sign)) {
				return new ForwardView("/api/invalid_sign");
			}
		}
		return null;
	}

}
