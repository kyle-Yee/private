package com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Dksqxx;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Syptbankapply;
@Repository 
public interface LoanApplyDataSaveMapper extends BaseMapper<Dksqxx, String> {
	@Override
	public List<Dksqxx> queryListAll(Map<String, Object> parameter);
	public List<Dksqxx> findAllbylfId();
	public int insert(Syptbankapply syptbankapply);
	public List<Syptbankapply> findBylaid(String ifId,String channelid);
	public int changesqdkxx(String lfId);
	public int changefinal(String lfId);
	public int changeend(String lfId);
	   
}
