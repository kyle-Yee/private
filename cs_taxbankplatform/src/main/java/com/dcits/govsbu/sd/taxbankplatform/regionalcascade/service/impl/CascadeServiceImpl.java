package com.dcits.govsbu.sd.taxbankplatform.regionalcascade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.count.model.PandectEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.regionalcascade.mapper.CascadeMapper;
import com.dcits.govsbu.sd.taxbankplatform.regionalcascade.model.CascadeEntity;
import com.dcits.govsbu.sd.taxbankplatform.regionalcascade.service.CascadeService;

@Service("cascadeService")
public class CascadeServiceImpl extends AbstractService<PandectEntity, String> implements CascadeService {
	@Autowired
	private CascadeMapper cascadeMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(cascadeMapper);		
	}
	
	@Override
	public JSONArray querycityListAll(String regionId) {
		List<ProvinceCitiesEntity> querycityList = cascadeMapper.querycityListAll(regionId);
		JSONArray city = new JSONArray();
		for(ProvinceCitiesEntity ct:querycityList){
			JSONObject ctdetails = new JSONObject();
			ctdetails.put("id", ct.getId());
			ctdetails.put("topid", ct.getPcpid());
			ctdetails.put("name", ct.getPcname());
			ctdetails.put("pccode", ct.getPccode());
			city.add(ctdetails);
		}
		return city;
	}

	@Override
	public JSONArray queryareaListAll(String regionId) {
		List<ProvinceCitiesEntity> queryareaList = cascadeMapper.queryareaListAll(regionId);
		JSONArray area = new JSONArray();	
		for(ProvinceCitiesEntity ar:queryareaList){
			JSONObject ardetails = new JSONObject();
			ardetails.put("id", ar.getId());
			ardetails.put("topid", ar.getPcpid());
			ardetails.put("name", ar.getPcname());
			ardetails.put("pccode", ar.getPccode());
			area.add(ardetails);
		}
		return area;
	}

	@Override
	public List<CascadeEntity> queryUserArea(String userId) {
		return cascadeMapper.queryUserArea(userId);
	}

	@Override
	public JSONArray queryareaBycity(String pcId) {
		List<ProvinceCitiesEntity> queryareaBycity = cascadeMapper.queryareaBycity(pcId);
//		for (ProvinceCitiesEntity pc : queryareaBycity) {
//				if ("市辖区".equals(pc.getPcname())){
//				pc.setPcname("请选择");
//				pc.setPcpid(0l);
//			}
//		}
		JSONArray area = new JSONArray();	
		for(ProvinceCitiesEntity ar:queryareaBycity){
			JSONObject ardetails = new JSONObject();
			ardetails.put("id", ar.getId());
			ardetails.put("topid", ar.getPcpid());
			ardetails.put("name", ar.getPcname());
			ardetails.put("pccode", ar.getPccode());
			area.add(ardetails);
		}
		return area;
	}

}
