/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanformsattach.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.loanformsattach.mapper.LoanFormsAttachMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanformsattach.model.LoanFormsAttach;
import com.dcits.govsbu.sd.taxbankplatform.loanformsattach.service.LoanFormsAttachService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

/**
 * @author 胡宝龙2016-9-9 上午10:15:37
 *
 */
@Service("loanFormsAttachService")
public class LoanFormsAttachServiceImpl extends AbstractService<LoanFormsAttach, String> implements LoanFormsAttachService{
	@Autowired
	private LoanFormsAttachMapper loanFormsAttachMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loanFormsAttachMapper);
	}

	@Override
	public List<LoanFormsAttach> queryListByLfId(Map<String, Object> parameter) {
		return loanFormsAttachMapper.queryListByLfId(parameter);
	}

	@Override
	public LoanFormsAttach queryExist(Map<String, Object> parameter) {
		return loanFormsAttachMapper.queryExist(parameter);
	}

	@Override
	public int insert(LoanFormsAttach t) {
		// TODO Auto-generated method stub
		return super.insert(t);
	}


	
}
