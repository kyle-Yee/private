package com.dcits.govsbu.sd.taxbankplatform.dkxx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper.BankfinalMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Bankfinal;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.BankfinalService;

@Service("bankfinalService")
public class BankfinalServiceImpl extends AbstractService<Bankfinal, String> implements BankfinalService{
	
	@Autowired
	private BankfinalMapper bankfinalMapper;
	

	public BankfinalMapper getBankfinalMapper() {
		return bankfinalMapper;
	}

	public void setBankfinalMapper(BankfinalMapper bankfinalMapper) {
		this.bankfinalMapper = bankfinalMapper;
	}

	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(bankfinalMapper);
	}

	@Override
	public int insertBankfinal(Bankfinal bankfinal) {
		// TODO Auto-generated method stub
		if(bankfinalMapper.insertBankfinal(bankfinal)>0){
			System.out.println("server"+1);
			return 1;
		}else {
			return 0;
		}
		
	}

	@Override
	public List<Bankfinal> queryListAll(Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return bankfinalMapper.queryListAll(parameter);
	}


}
