package com.aibinong.api.util.ons;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.api.util.NutzUtil;
import com.aibinong.api.web.Constants;
import com.aibinong.api.web.MainModule;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;

/**
 * 阿里云ONS MQ 生产者工具类
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年7月4日 下午6:01:53
 */
@IocBean
public class MQProducer {
	private final static Logger LOG = LoggerFactory.getLogger(MQProducer.class);

	private String accessKey;
	private String secretKey;
	private String[] producerIds;

	private static Map<String, Producer> producerMap = new HashMap<String, Producer>();

	public MQProducer() {
	}

	public void init() {
		try {
			for (String producerId : producerIds) {
				Properties properties = new Properties();
				properties.put(PropertyKeyConst.ProducerId, producerId);
				properties.put(PropertyKeyConst.AccessKey, this.accessKey);
				properties.put(PropertyKeyConst.SecretKey, this.secretKey);
				Producer producer = ONSFactory.createProducer(properties);
				// 在发送消息前，必须调用 start 方法来启动 Producer，只需调用一次即可。
				producer.start();
				producerMap.put(producerId, producer);
				LOG.info("Producter '{}' start success!", producerId);
			}
		} catch (Exception e) {
			LOG.error("Producers '{}' start error", producerIds, e);
			throw new RuntimeException(e);
		}
	}

	public void close() {
		for (String producerId : producerMap.keySet()) {
			Producer producer = producerMap.get(producerId);
			if (producer != null) {
				producer.shutdown();
			}
		}
	}

	public Producer getProducer(String producerId) {
		if (!producerMap.containsKey(producerId)) {
			throw new IllegalArgumentException("Producer [" + producerId + "] is not exists!");
		}
		return producerMap.get(producerId);
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

	public String[] getProducerIds() {
		return producerIds;
	}

	public void setProducerIds(String[] producerIds) {
		this.producerIds = producerIds;
	}

	public static void main(String[] args) {
		NutzUtil.init(MainModule.class);
		MQProducer mqQroducer = Mvcs.getIoc().get(MQProducer.class, "mqProducer");
		Producer producer = mqQroducer.getProducer(Constants.PAY_PRODUCER_ID);

		Message message = new Message(Constants.PAY_TOPIC, "", "{'title': 'hello aibinong!'}".getBytes());
		message.setKey(String.valueOf(1));

		//发送消息，只要不抛异常就是成功
		try {
			SendResult sendResult = producer.send(message);
			System.out.println("支付消息发送成功! messageId=" + sendResult.getMessageId());
		} catch (Exception ex) {
			throw ex;
		}
		mqQroducer.close();
	}
}
