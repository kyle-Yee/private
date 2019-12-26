package com.dcits.govsbu.sd.taxbankplatform.amount.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.amount.mapper.AmountMapper;
import com.dcits.govsbu.sd.taxbankplatform.amount.model.AmountEntity;
import com.dcits.govsbu.sd.taxbankplatform.amount.service.AmountService;

@Service("amountService")
public class AmountServiceImpl extends AbstractService<AmountEntity, String> implements AmountService{
	
	@Autowired
	private AmountMapper amountMapper;

	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(amountMapper);
	}

	@Override
	public int delAmount(String id) throws AjaxException {
		// TODO Auto-generated method stub
		try
		{
			int result = amountMapper.delAmount(id);
			
			return result;
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}
}
