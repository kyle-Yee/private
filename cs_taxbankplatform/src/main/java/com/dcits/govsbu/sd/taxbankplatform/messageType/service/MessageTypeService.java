/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.messageType.service;

import java.util.List;
import java.util.Map;
import com.dcits.govsbu.sd.taxbankplatform.messageType.model.MessageType;

/**
 * @author 胡宝龙2016-8-22 下午12:01:19
 *
 */
public interface MessageTypeService {
	List<MessageType> queryListByPage(Map<String, Object> parameters);

	public MessageType findById(String id); 

	public int insert(MessageType MessageType);

	public MessageType findByName(String name);
}
