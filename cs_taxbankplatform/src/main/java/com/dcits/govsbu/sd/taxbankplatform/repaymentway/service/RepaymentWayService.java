package com.dcits.govsbu.sd.taxbankplatform.repaymentway.service;

import java.util.List;
import java.util.Map;
import com.dcits.govsbu.sd.taxbankplatform.repaymentway.model.RepaymentWayEntity;

/**
 * 
 * @author liwangxiong
 *
 */
public interface RepaymentWayService {
	public List<RepaymentWayEntity> queryListByPage(Map<String, Object> parameter);
	
	public int insert(RepaymentWayEntity repaymentWayEntity);
	
	public RepaymentWayEntity findById(String id);

	public int update(RepaymentWayEntity repaymentWayEntity);
    
    public int deleteBatchById(List<String> wayids);
}
