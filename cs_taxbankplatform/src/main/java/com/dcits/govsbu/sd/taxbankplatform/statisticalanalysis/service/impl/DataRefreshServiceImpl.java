package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.mapper.DataRefreshMapper;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.DataRefreshEntity;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.DataRefreshService;

@Service("dataRefreshService")
public class DataRefreshServiceImpl  extends AbstractService<DataRefreshEntity, String> implements DataRefreshService {

	@Autowired
	private DataRefreshMapper  dataRefreshMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(dataRefreshMapper);		
	}

	@Override
	public DataRefreshEntity getDataRefresh(Map<String, Object> map) {
		return dataRefreshMapper.getDataRefresh(map);
	}

	@Override
	public int insertDataRefresh(DataRefreshEntity dataRefreshEntity) {
		try {
			return dataRefreshMapper.insertDataRefresh(dataRefreshEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateDataRefresh(DataRefreshEntity dataRefreshEntity) {
		try {
			return dataRefreshMapper.updateDataRefresh(dataRefreshEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> getCoreBank() {
		return dataRefreshMapper.getCoreBank();
	}

	@Override
	public List<Map<String, Object>> getBankAllUser(String org_id) {
		return dataRefreshMapper.getBankAllUser(org_id);
	}


	

}
