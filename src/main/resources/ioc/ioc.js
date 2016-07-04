/**
 * 数据库连接
 */
var ioc = {
    conf: {
        type: "org.nutz.ioc.impl.PropertiesProxy",
        fields: {
            paths : ["conf/config.properties"]
        }
    },
//    dataSource: {
//        type: "com.alibaba.druid.pool.DruidDataSource",
//        events: {
//            create: "init",
//            depose: "close"
//        },
//        fields: {
//            url: {java:"$conf.get('db.url')"},
//            username: {java:"$conf.get('db.username')"},
//            password: {java:"$conf.get('db.password')"},
//            minIdle: {java:"$conf.get('db.minIdle')"},
//            initialSize: {java:"$conf.get('db.initialSize')"}, 
//            maxActive: {java:"$conf.get('db.maxActive')"}, 
//            testWhileIdle: true,
//            validationQuery: {java:"$conf.get('db.validationQuery')"},
//            filters: "wall"
//        }
//    },
//
//    dao: {
//        type: "org.nutz.dao.impl.NutDao",
//        args: [{refer:"dataSource"}]
//    },
//    
//    redisDao: {
//    	type: "com.aibinong.api.dao.RedisDao",
//    	events: {
//            create: "init"
//        },
//	    fields: {
//	        host: {java:"$conf.get('redis.host')"},
//	        port: {java:"$conf.get('redis.port')"},
//	        password: {java:"$conf.get('redis.password')"},
//	        maxIdel: {java:"$conf.get('redis.maxIdel')"},
//	        maxTotal: {java:"$conf.get('redis.maxTotal')"},
//	        timeout: {java:"$conf.get('redis.timeout')"}
//	    }
//    },
    
    mqProducter: {
    	type: "com.aibinong.api.util.ons.MQProducter",
    	events: {
            create: "init"
        },
	    fields: {
	    	accessKey: {java:"$conf.get('aliyun.access.key')"},
	    	secretKey: {java:"$conf.get('aliyun.secret.key')"},
	    	producterIds: [
	    	    {java:"$conf.get('pay.producer.id')"}, 
	    	    {java:"$conf.get('duobao.producer.id')"}
	    	]
	    }
    }
};