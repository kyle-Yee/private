package com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.mapper.AcceptDeadLineMapper;
import com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.model.AcceptDeadLineEntity;
import com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.service.AcceptDeadLineService;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

@Service("acceptDeadLineService")
public class AcceptDeadLineServiceImpl extends AbstractService<AcceptDeadLineEntity, String> implements AcceptDeadLineService {
	@Autowired
	private AcceptDeadLineMapper acceptDeadLineMapper;
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(acceptDeadLineMapper);
	}
	@Override
	public List<AcceptDeadLineEntity> queryListAll(Map<String, Object> parameter){
		return acceptDeadLineMapper.queryListAll(null);
	}
	@Override
	public Integer deadLineCount(String orgid) {
		return acceptDeadLineMapper.deadLineCount(orgid);
	}
	@Override
	public int insert(AcceptDeadLineEntity t) {
		// TODO Auto-generated method stub
		String tl_id = IDGenerate.getZJID("SLSJ");
		t.setTl_id(tl_id);
		return super.insert(t);
	}
}
