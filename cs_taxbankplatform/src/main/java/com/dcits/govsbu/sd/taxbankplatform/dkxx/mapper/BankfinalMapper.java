package com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Bankfinal;

@Repository 
public interface BankfinalMapper extends BaseMapper<Bankfinal, String> {

 
	int insertBankfinal(Bankfinal bankfinal);

	@Override
	public List<Bankfinal> queryListAll(Map<String, Object> parameter);
}