package com.dcits.govsbu.sd.taxbankplatform.provincecities.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;

/**
 * 
 * @author liwangxiong
 *
 */
public interface ProvinceCitiesService {
	public List<ProvinceCitiesEntity> queryListByPage(Map<String, Object> parameter);
	
	public int insert(ProvinceCitiesEntity provinceCitiesEntity);
	
	public ProvinceCitiesEntity findById(String id);

	public int update(ProvinceCitiesEntity provinceCitiesEntity);
    
    public int deleteBatchById(List<String> citiesids);
    
    public List<ProvinceCitiesEntity> queryListAll(Map<String, Object> parameter);
    
    public ProvinceCitiesEntity findByPccode(String pccode);
    public ProvinceCitiesEntity findPcID(String regionid);
    
//	 通过pccode前两位判断传入的pcid是否属于指定的省市区
	 public int selectPccodeById(Map<String,Object> map);
	 
	 public List<ProvinceCitiesEntity> findAll(String id);

}
