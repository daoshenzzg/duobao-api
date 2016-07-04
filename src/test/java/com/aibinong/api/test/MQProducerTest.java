package com.aibinong.api.test;

import org.nutz.mvc.Mvcs;

import com.aibinong.api.util.NutzUtil;
import com.aibinong.api.util.ons.MQProducer;
import com.aibinong.api.web.Constants;
import com.aibinong.api.web.MainModule;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;

/**
 * 测试阿里云ONS MQ Producer
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年7月4日 下午7:21:29
 */
public class MQProducerTest {
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
