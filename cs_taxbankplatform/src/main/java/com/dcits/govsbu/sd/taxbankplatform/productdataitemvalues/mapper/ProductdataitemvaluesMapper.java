package com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.mapper;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitems;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitemvalues;

public interface ProductdataitemvaluesMapper  extends BaseMapper<Productdataitemvalues, String> {
    int deleteByPrimaryKey(Integer pdivId);

	int insertValues(Productdataitemvalues record);
    
	int insertItems(Productdataitemvalues productdataitemvalues);
	int insertSelective(Productdataitemvalues record);

	Productdataitemvalues selectByPrimaryKey(Integer pdivId);

	int updateByPrimaryKeySelective(Productdataitemvalues record);

	int updateByPrimaryKey(Productdataitemvalues record);
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