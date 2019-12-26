/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanForms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.loanForms.mapper.LoanFormsMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanForms.model.LoanForms;
import com.dcits.govsbu.sd.taxbankplatform.loanForms.service.LoanFormsService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

/**
 * @author 胡宝龙2016-8-15 上午10:06:49
 *
 */
@Service("loanFormsService")
public class LoanFormsServiceImpl extends AbstractService<LoanForms, String> implements LoanFormsService{

	@Override
	public int insert(LoanForms t) {

		return super.insert(t);
	}
	@Autowired
	private LoanFormsMapper loanFormsMapper;
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为loanFormsMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loanFormsMapper);
	}
	@Override
	public LoanForms findByFpId(String id) {
		return loanFormsMapper.findByFpId(id);
	}
	@Override
	public LoanForms findById(String id) {
		return loanFormsMapper.findById(id);
	}
}
