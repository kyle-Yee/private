package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.DialogEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.FeedbackEntity;

public interface FeedbackService{

	public List<FeedbackEntity> queryListByPage(Map<String, Object> parameters);

	public int update(FeedbackEntity feedbackEntity);

	public int insert(FeedbackEntity feedbackEntity);
	
	//插入反馈建议对话
	public int insertDialog(DialogEntity dialogEntity);
	
	//根据反馈id 获取对话列表
	public List<DialogEntity> dialogList(String fid);
	
	//回复反馈
	public int replyFeedback(FeedbackEntity feedbackEntity) throws Exception;

	//获取管理员名称
	public String getNameByAdminId(String intValue);

	//获取用户名称
	public String getNameByUserId(String intValue);

	public FeedbackEntity findById(String id);

	

}
