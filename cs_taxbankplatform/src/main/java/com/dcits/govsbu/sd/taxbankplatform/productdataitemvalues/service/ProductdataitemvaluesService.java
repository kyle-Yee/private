package com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitems;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitemvalues;

public interface ProductdataitemvaluesService {
	 public int insert(Productdataitemvalues productdataitemvalues);
	 /**
      * 根据PdiID查询数据项值 
      */
     List<Productdataitemvalues> selectPdivByPdiId(Map<String, Object> parameter);
     /**
      * 根据PdiID查询数据项序号
      */
     List<Productdataitemvalues> selectPdivSeqByPdiId(Map<String, Object> parameter);
     

	public List<Productdataitems> searchPdivNameList(String id);
}
