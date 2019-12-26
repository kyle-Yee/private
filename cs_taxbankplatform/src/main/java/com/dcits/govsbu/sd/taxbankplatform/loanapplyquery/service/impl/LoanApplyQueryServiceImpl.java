package com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.mapper.LoanApplyQueryMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyTailafterEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.service.LoanApplyQueryService;


/**
 * LoanApplyQueryServiceImpl.java
 * @author 严添麟
 * @date 2016年8月10日
 * @caption 贷款申请查询
 */
@Service("loanApplyQueryService")
public class LoanApplyQueryServiceImpl extends AbstractService<LoanApplyQueryEntity, String> implements LoanApplyQueryService{

	@Autowired
	private LoanApplyQueryMapper loanApplyQueryMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loanApplyQueryMapper);
	}

	
	/**
	 * 功能:
	 * 获取贷款申请数据项信息
	 */
	@Override
	public LoanApplyQueryEntity queryListAttach(Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		LoanApplyQueryEntity loanApplyQueryEntity = new LoanApplyQueryEntity();
		try {
			loanApplyQueryEntity.setLoanApplyAttachList(loanApplyQueryMapper.queryListAttach(parameter));
		} catch (ServiceException e) {
			// TODO: handle exception
			throw new AjaxException(e);
		}
		return loanApplyQueryEntity;
	}
	@Override
	public LoanApplyTailafterEntity findDataById(String id){
		return loanApplyQueryMapper.findDataById(id);
	}
}
