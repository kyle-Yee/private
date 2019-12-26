package com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.model.AcceptDeadLineEntity;
/**
 * 
 * @author Administrator
 *
 */
public interface AcceptDeadLineService {
	
	public List<AcceptDeadLineEntity> queryListAll(Map<String, Object> parameter);
	
	int insert(AcceptDeadLineEntity acceptDeadLineEntity);

	public AcceptDeadLineEntity findById(String id);

	public List<AcceptDeadLineEntity> queryListByPage(Map<String, Object> parameters);

	public int update(AcceptDeadLineEntity acceptDeadLineEntity);

	public int deleteBatchById(List<String> tlids);
	
	/**
	 * 某银行贷款受理期限个数
	 * @param orgid
	 * @return
	 */
	public Integer deadLineCount(String orgid);
}
