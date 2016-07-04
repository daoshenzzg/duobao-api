package com.aibinong.api.util.ons;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.api.util.NutzUtil;
import com.aibinong.api.web.MainModule;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;

@IocBean
public class MQProducter {
	private final static Logger LOG = LoggerFactory.getLogger(MQProducter.class);

	private String accessKey;
	private String secretKey;
	private String[] producterIds;
	
	private static Map<String, Producer> producterMap = new HashMap<String, Producer>();

	public MQProducter() {
	}
	
	public void init() {
		try {
			for (String producterId : producterIds) {
				Properties properties = new Properties();
		        properties.put(PropertyKeyConst.ProducerId, producterId);
		        properties.put(PropertyKeyConst.AccessKey, this.accessKey);
		        properties.put(PropertyKeyConst.SecretKey, this.secretKey);
		        Producer producer = ONSFactory.createProducer(properties);
		        // 在发送消息前，必须调用 start 方法来启动 Producer，只需调用一次即可。
		        producer.start();
		        producterMap.put(producterId, producer);
		        LOG.info("Producter '{}' start success!", producterId);
			}
		} catch (Exception e) {
			LOG.error("Producters start error", producterIds, e);
			throw new RuntimeException(e);
		}
	}

	public Producer getProducter(String productId) {
		if (!producterMap.containsKey(productId)) {
			throw new IllegalArgumentException("Producer [" + productId + "] is not exists!");
		}
		return producterMap.get(productId);
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String[] getProducterIds() {
		return producterIds;
	}

	public void setProducterIds(String[] producterIds) {
		this.producterIds = producterIds;
	}
	
	public static void main(String[] args) {
		NutzUtil.init(MainModule.class);
		MQProducter mqProducter = Mvcs.getIoc().get(MQProducter.class, "mqProducter");

		Producer producer = mqProducter.getProducter("PID_com_aibinong_topic_duobao-d");

//		Message message = new Message("com_aibinong_topic_duobao-d", "", "{title:'hello world!'}".getBytes());
//		SendResult result = producer.send(message);
//		System.out.println(result.getMessageId());
	}
}
