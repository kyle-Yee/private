package com.dcits.govsbu.sd.taxbankplatform.dkxx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper.BankfinalendMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Bankfinalend;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.BankfinalendService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

@Service("bankfinalendService")
public class BankfinalendServiceImpl extends AbstractService<Bankfinalend, String> implements BankfinalendService{
	
	@Autowired
	private BankfinalendMapper bankfinalendMapper;
	


	public BankfinalendMapper getBankfinalendMapper() {
		return bankfinalendMapper;
	}



	public void setBankfinalendMapper(BankfinalendMapper bankfinalendMapper) {
		this.bankfinalendMapper = bankfinalendMapper;
	}



	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(bankfinalendMapper);
	}



	@Override
	public int insertBankfinalend(Bankfinalend bankfinalend) {
		String id = IDGenerate.getZJID("XH");
		bankfinalend.setId(id);
		return bankfinalendMapper.insert(bankfinalend);
	}

	

	@Override
	public List<Bankfinalend> queryListAll(Map<String, Object> parameter){
		return bankfinalendMapper.queryListAll(parameter);
	}

}
