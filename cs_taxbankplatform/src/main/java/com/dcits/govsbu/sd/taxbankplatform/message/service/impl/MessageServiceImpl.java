/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.message.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.message.mapper.MessageMapper;
import com.dcits.govsbu.sd.taxbankplatform.message.model.MessageEntity;
import com.dcits.govsbu.sd.taxbankplatform.message.service.MessageService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

/**
 * @author 胡宝龙2016-8-22 上午11:56:02
 *
 */
@Service("messageService")
public class MessageServiceImpl  extends AbstractService<MessageEntity, String> implements MessageService {

	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(messageMapper);
	}

	@Override
	public int insert(MessageEntity t) {
		// TODO Auto-generated method stub
		String m_id = IDGenerate.getZJID("XH");
		t.setM_id(m_id);
		return super.insert(t);
	}
	
}
