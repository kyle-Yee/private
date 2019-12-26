/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.messageType.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.messageType.mapper.MessageTypeMapper;
import com.dcits.govsbu.sd.taxbankplatform.messageType.model.MessageType;
import com.dcits.govsbu.sd.taxbankplatform.messageType.service.MessageTypeService;

/**
 * @author 胡宝龙2016-8-22 下午12:02:59
 *
 */
@Service("messageTypeService")
public class MessageTypeServiceImpl  extends AbstractService<MessageType, String> implements MessageTypeService {

	@Autowired
	private MessageTypeMapper messageTypeMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(messageTypeMapper);
	}
}
