package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service;

import java.util.List;
import java.util.Map;

public interface NsxyqjyyqkService {
	/**
	 * 
	 * @param 实际发放贷款情况
	 * @return
	 */
	public List<Map<String,String>> sjtsyhs (Map<String, Object> parameters);
	public List<Map<String,String>> qzysxhs (Map<String, Object> parameters);
	
	public List<String> findXlHyDm(String dl_hydm);
	public List<String> findZlHyDm(String dl_hydm);
	
	public List<Map<String,String>> findHy();
}
