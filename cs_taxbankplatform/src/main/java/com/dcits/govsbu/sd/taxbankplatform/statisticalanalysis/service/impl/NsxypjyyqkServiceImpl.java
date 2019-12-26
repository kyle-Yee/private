package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.mapper.NsxypjyyqkMapper;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.NsxypjyyqkEntity;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.NsxyqjyyqkService;

@Service("nsxypjyyqkService")
public class NsxypjyyqkServiceImpl  extends AbstractService<NsxypjyyqkEntity, String> implements NsxyqjyyqkService {

	@Autowired
	private NsxypjyyqkMapper  nsxypjyyqkMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(nsxypjyyqkMapper);		
	}

	@Override
	public List<Map<String, String>> sjtsyhs(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return nsxypjyyqkMapper.sjtsyhs(parameters);
	}

	@Override
	public List<Map<String, String>> qzysxhs(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return nsxypjyyqkMapper.qzysxhs(parameters);
	}

	@Override
	public List<String> findXlHyDm(String dl_hydm) {
		// TODO Auto-generated method stub
		return nsxypjyyqkMapper.findXlHyDm(dl_hydm);
	}

	@Override
	public List<String> findZlHyDm(String dl_hydm) {
		// TODO Auto-generated method stub
		return nsxypjyyqkMapper.findZlHyDm(dl_hydm);
	}

	@Override
	public List<Map<String, String>> findHy() {
		// TODO Auto-generated method stub
		return nsxypjyyqkMapper.findHy();
	}

	 

	 
 

}
