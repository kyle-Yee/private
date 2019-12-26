/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.message.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.messageType.model.MessageType;
/**
 * @author 胡宝龙2016-8-22 上午11:20:32
 *
 */
public class MessageEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String m_id;
	private String title; //标题
	private String subtitle; //副标题
	private String content; //内容
	private Long userId; //用户ID(接收人)
	private MessageType messageType; //消息类型
	private String enabled;
	private String unread; //是否未读
	
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getUnread() {
		return unread;
	}
	public void setUnread(String unread) {
		this.unread = unread;
	}
	
}
