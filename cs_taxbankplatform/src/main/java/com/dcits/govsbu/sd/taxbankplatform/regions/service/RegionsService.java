package com.dcits.govsbu.sd.taxbankplatform.regions.service;

import java.util.List;
import java.util.Map;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;

/**
 * 
 * @author liwangxiong
 *
 */
public interface RegionsService {
	public List<RegionsEntity> queryListByPage(Map<String, Object> parameter);
	
	public int insert(RegionsEntity regionsEntity);
	
	public RegionsEntity findById(String id);
	
	public RegionsEntity findByName(String regionname);

	public int update(RegionsEntity regionsEntity);
    
    public int deleteBatchById(List<String> regionids);
    
    public List<RegionsEntity> queryListAll(Map<String, Object> parameter); 
    
    public List<RegionsEntity> findByRegioncode(String regioncodes);
}
