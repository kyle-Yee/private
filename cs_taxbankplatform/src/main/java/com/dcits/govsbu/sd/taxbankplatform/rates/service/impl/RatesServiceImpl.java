package com.dcits.govsbu.sd.taxbankplatform.rates.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.rates.mapper.RatesMapper;
import com.dcits.govsbu.sd.taxbankplatform.rates.model.RatesEntity;
import com.dcits.govsbu.sd.taxbankplatform.rates.service.RatesService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

@Service("ratesService")
public class RatesServiceImpl extends AbstractService<RatesEntity, String> implements RatesService {

	@Autowired
	private RatesMapper ratesMapper;
	
	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
		@Autowired
		public void setBaseMapper() {
			super.setBaseMapper(ratesMapper);
		}

		@Override
		public int delRates(String id) throws AjaxException {
			// TODO Auto-generated method stub
			try
			{
				int result = ratesMapper.delRates(id);
				
				return result;
			}catch(Exception e)
			{
				throw new ServiceException(e);
			}
		}

		@Override
		public int insert(RatesEntity t) {
			String lr_id = IDGenerate.getZJID("LF");
			t.setLr_id(lr_id);
			return super.insert(t);
		}
		
}
