package com.dcits.govsbu.sd.taxbankplatform.deadline.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.deadline.model.DeadlineEntity;

public interface DeadlineService {

	List<DeadlineEntity> queryListByPage(Map<String, Object> parameters); //add by baolong.hu
	
	public DeadlineEntity findById(String deadlineEntityId); //add by baolong.hu

	public int insert(DeadlineEntity deadlineEntity);

	public DeadlineEntity findByName(String deadlineName);

	public int delDeadline(String id);

}
