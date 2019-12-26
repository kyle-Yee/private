package com.dcits.govsbu.sd.taxbankplatform.loanstatus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.loanstatus.mapper.LoanStatusMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanstatus.model.LoanStatusEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanstatus.service.LoanStatusService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;


/**
 * 
 * @author liwangxiong
 *
 */
@Service("loanStatusService")
public class LoanStatusServiceImpl extends AbstractService<LoanStatusEntity, String> implements LoanStatusService{
	@Autowired
	private LoanStatusMapper loanStatusMapper;
	//private BaseMapper<LoanStatusEntity, Object> loanStatusMapper;
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loanStatusMapper);
	}

	@Override
	public int insert(LoanStatusEntity t) {
		// TODO Auto-generated method stub
		String ls_id = IDGenerate.getZJID("XH");
		t.setLs_id(ls_id);
		return super.insert(t);
	}
	
}
