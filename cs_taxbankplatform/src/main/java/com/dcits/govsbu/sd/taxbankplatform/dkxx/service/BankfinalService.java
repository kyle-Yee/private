package com.dcits.govsbu.sd.taxbankplatform.dkxx.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Bankfinal;

public interface BankfinalService {

	public int insertBankfinal(Bankfinal bankfinal);
	public List<Bankfinal> queryListAll(Map<String, Object> parameter);


}
