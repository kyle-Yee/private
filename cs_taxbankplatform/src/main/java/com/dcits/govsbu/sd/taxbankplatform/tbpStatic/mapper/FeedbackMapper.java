package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.DialogEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.FeedbackEntity;

@Repository
public interface FeedbackMapper extends BaseMapper<FeedbackEntity, String>{
	//插入反馈建议对话
	public int insertDialog(DialogEntity dialogEntity);
	
	//根据反馈id 获取对话列表
	public List<DialogEntity> dialogList(String fid);
 
	public String getNameByAdminId(String intValue);

	public String getNameByUserId(String intValue);

}
