package com.dq.easy.cloud.model.mq.amqp.handler;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.model.mq.amqp.base.vo.DqAmqpBaseVo;

/**
 * 
 * @ClassName : DqAmqpTemplateHandler 
 * @Description : amqp模板助手类
 * @author daiqi
 * @date 2017年12月20日 下午4:19:35 
 *
 */
@Component
public class DqAmqpTemplateHandler {
	private static AmqpTemplate rabbitTemplate;

	@Resource
	public void setAmqpTemplate(AmqpTemplate rabbitTemplate) {
		DqAmqpTemplateHandler.rabbitTemplate = rabbitTemplate;
	}

	public static void send(DqAmqpBaseVo baseVo){
		if(DqBaseUtils.isNull(baseVo)){
			return ;
		}
		if(DqStringUtils.isEmpty(baseVo.getExchange())){
			rabbitTemplate.convertAndSend(baseVo.getQueueName(), baseVo.getMsg());
		}else{
			rabbitTemplate.convertAndSend(baseVo.getExchange(), baseVo.getQueueName(), baseVo.getMsg());
		}
	}
}
