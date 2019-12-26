package com.dcits.govsbu.sd.taxbankplatform.dkxx.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Dksqxx;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Syptbankapply;

public interface LoanApplyDataSaveService {
	public List<Dksqxx> queryListAll(Map<String, Object> parameter);
	public List<Dksqxx> findAllbylfId();
	public int insert(Syptbankapply syptbankapply);
	public List<Syptbankapply> findBylaid(String ifId,String channelid);
	public int changesqdkxx(String lfId);
	public int changefinal(String lfId);
	public int changeend(String lfId);
	   
}
