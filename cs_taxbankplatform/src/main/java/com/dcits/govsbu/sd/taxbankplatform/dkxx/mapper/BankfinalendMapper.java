package com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Bankfinalend;

@Repository 
public interface BankfinalendMapper extends BaseMapper<Bankfinalend, String> {


    @Override
	int insert(Bankfinalend bankfinalend);
     
    @Override
	public List<Bankfinalend> queryListAll(Map<String, Object> parameter);
 
}