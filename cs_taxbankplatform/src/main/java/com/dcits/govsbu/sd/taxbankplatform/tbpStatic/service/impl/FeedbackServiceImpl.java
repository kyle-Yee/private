package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper.FeedbackMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.DialogEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.FeedbackEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.FeedbackService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

@Service
public class FeedbackServiceImpl extends AbstractService<FeedbackEntity, String> implements FeedbackService {

	@Autowired
	private FeedbackMapper feedbackMapper;

	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(feedbackMapper);
	}

	@Override
	public int insertDialog(DialogEntity dialogEntity) {
		return feedbackMapper.insertDialog(dialogEntity);
	}

	@Override
	public List<DialogEntity> dialogList(String fid) {
		return feedbackMapper.dialogList(fid);
	}

	@Override
	public int replyFeedback(FeedbackEntity feedbackEntity) throws Exception{
		int i = feedbackMapper.update(feedbackEntity);//修改反馈
		
		DialogEntity dialogEntity = new DialogEntity();
		dialogEntity.setCreatetime(new Date());
		dialogEntity.setCreatorid(feedbackEntity.getUpdatorid());
		dialogEntity.setF_content(feedbackEntity.getFeedbackContent());
		dialogEntity.setType(1);//1，回复	2，追问
		dialogEntity.setF_id(feedbackEntity.getId());//添加对话记录
		dialogEntity.setId(IDGenerate.getZJID("XH"));
		int j = feedbackMapper.insertDialog(dialogEntity);
		
		return i*j;
	}

	@Override
	public String getNameByAdminId(String intValue) {
		return feedbackMapper.getNameByAdminId(intValue);
	}

	@Override
	public String getNameByUserId(String intValue) {
		return feedbackMapper.getNameByUserId(intValue);
	}


}
