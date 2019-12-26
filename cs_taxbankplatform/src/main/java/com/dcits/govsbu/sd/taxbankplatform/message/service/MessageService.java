/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.message.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.message.model.MessageEntity;


/**
 * @author 胡宝龙2016-8-22 上午11:53:08
 *
 */
public interface MessageService {
	List<MessageEntity> queryListByPage(Map<String, Object> parameters);

	public MessageEntity findById(String id); 

	public int insert(MessageEntity MessageEntity);

	public MessageEntity findByName(String name);
	
	public int update(MessageEntity MessageEntity);

}
