var ioc = {
    methodAop: {
        type:'com.aibinong.api.web.interceptor.TimeoutInterceptor'
    },
    $aop: {
	    type: 'org.nutz.ioc.aop.config.impl.ComboAopConfigration',
	    fields: {
            aopConfigrations: [
	            {       
	            	type: 'org.nutz.ioc.aop.config.impl.JsonAopConfigration',
	                fields: {
	                    itemList: [
	                        ['com\\.aibinong\\.api\\.web\\.module\\..+','.+','ioc:methodAop']
	                    ]
	                }
	            },
	            { type : 'org.nutz.ioc.aop.config.impl.AnnotationAopConfigration' }
            ]
	    }
	}
};