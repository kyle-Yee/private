package com.dcits.govsbu.sd.taxbankplatform.repaymentway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.repaymentway.mapper.RepaymentWayMapper;
import com.dcits.govsbu.sd.taxbankplatform.repaymentway.model.RepaymentWayEntity;
import com.dcits.govsbu.sd.taxbankplatform.repaymentway.service.RepaymentWayService;


/**
 * 
 * @author liwangxiong
 *
 */
@Service("repaymentWayService")
public class RepaymentWayServiceImpl extends AbstractService<RepaymentWayEntity, String> implements RepaymentWayService{
	@Autowired
	private RepaymentWayMapper repaymentWayMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(repaymentWayMapper);
	}
}
