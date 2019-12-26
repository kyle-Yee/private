package com.dcits.govsbu.sd.taxbankplatform.dkxx.service;

import java.util.List;
import java.util.Map;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Bankfinalend;

public interface BankfinalendService{

	public int insertBankfinalend(Bankfinalend bankfinalend);

	public List<Bankfinalend> queryListAll(Map<String, Object> parameter);

}
