package com.dcits.govsbu.sd.taxbankplatform.provincecities.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.mapper.ProvinceCitiesMapper;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;


/**
 * 
 * @author liwangxiong
 *
 */
@Service("provinceCitiesService")
public class ProvinceCitiesServiceImpl extends AbstractService<ProvinceCitiesEntity, String> implements ProvinceCitiesService{
	@Autowired
	private ProvinceCitiesMapper provinceCitiesMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(provinceCitiesMapper);
	}

	@Override
	public ProvinceCitiesEntity findByPccode(String pccode) {
		return provinceCitiesMapper.findByPccode(pccode);
	}

	@Override
	public ProvinceCitiesEntity findPcID(String regionid) {
		return provinceCitiesMapper.findPcID(regionid);
	}

	@Override
	public int selectPccodeById(Map<String, Object> map) {
		/*通过pccode前两位判断传入的pcid是否属于指定的省市区*/
		return provinceCitiesMapper.selectPccodeById(map);
	}

	@Override
	public List<ProvinceCitiesEntity> findAll(String id) {
		// TODO Auto-generated method stub
		return provinceCitiesMapper.findAll(id);
	}
}
