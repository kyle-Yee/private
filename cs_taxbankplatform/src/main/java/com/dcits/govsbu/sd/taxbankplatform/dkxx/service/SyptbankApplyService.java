package com.dcits.govsbu.sd.taxbankplatform.dkxx.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Syptbankapply;

public interface SyptbankApplyService {
	public int insert(Syptbankapply syptbankapply);
	public List<Syptbankapply> findBylaid(String lfId,String channelid);
	public List<Syptbankapply> findByfpid(String bankfpId,String channelid);
	public int changetemp(Syptbankapply syptbankapply);
	public List<Syptbankapply> findAll(Map<String, Object> parameter);
	   
}
