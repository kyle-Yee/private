/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：ClientServiceImpl.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2017-7-25下午下午4:47:502:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.gxgsService.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.gxgsService.mapper.ClientMapper;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.model.ClientEntity;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.service.ClientService;


/**
 * @versions:1.0 
 * @filename：ClientServiceImpl.java
 * @Company:dfwyBank
 * @Created: 2017-7-25下午下午4:47:502:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName ClientServiceImpl
 */
@Service("clientService")
public class ClientServiceImpl extends AbstractService<ClientEntity, String> implements ClientService {
	
	@Autowired
	private ClientMapper clientMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(clientMapper);
	}
	@Override
	public List<Map<String, Object>> findDataByTime(String sql) {
		return clientMapper.findDataByTime(sql);
	}
	@Override
	public Map<String, Object> findPrimaryKey(String sql) {
		return clientMapper.findPrimaryKey(sql);
	}
	@Override
	public Map<String, Object> findPrimaryKeyByTableName(String sql) {
		return clientMapper.findPrimaryKeyByTableName(sql);
	}
	@Override
	public int insertYhi(Map<String, Object> map) {
		return clientMapper.insertYhi(map);
	}
	@Override
	public int updateYhi(Map<String, Object> map) {
		return clientMapper.updateYhi(map);
	}
	@Override
	public int insertYhu(Map<String, Object> map) {
		return clientMapper.insertYhu(map);
	}
	@Override
	public int updateYhu(Map<String, Object> map) {
		return clientMapper.updateYhu(map);
	}
	@Override
	public List<Map<String, Object>> findDatai59BySynstarAndSynend(String sql)
			throws Exception {
		return clientMapper.findDatai59BySynstarAndSynend(sql);
	}
	@Override
	public List<Map<String, Object>> findDatau59BySynstarAndSynend(String sql)
			throws Exception {
		return clientMapper.findDatau59BySynstarAndSynend(sql);
	}
	/*@Override
	public int insertNsryh(Map<String, Object> map) throws Exception {
		return clientMapper.insertNsryh(map);
	}*/
}
