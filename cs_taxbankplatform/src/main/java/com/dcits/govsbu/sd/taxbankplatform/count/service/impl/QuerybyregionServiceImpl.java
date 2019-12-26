package com.dcits.govsbu.sd.taxbankplatform.count.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.count.model.QuerybyregionEntity;
import com.dcits.govsbu.sd.taxbankplatform.count.service.QuerybyregionService;

@Service("querybyregionService")
public class QuerybyregionServiceImpl  extends AbstractService<QuerybyregionEntity, String> implements QuerybyregionService {
	@Autowired
	private BaseMapper<QuerybyregionEntity, String> querybyregionMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(querybyregionMapper);		
	}
	
	@Override
	public List<QuerybyregionEntity> queryListByPage(Map<String, Object> parameters) {
		List<QuerybyregionEntity> queryListByPage = querybyregionMapper.queryListByPage(parameters);
		for (QuerybyregionEntity qre : queryListByPage){
			if (qre.getBanklist() == null){
				qre.setBanklist("");
			}
			if (qre.getCreditline() == null){
				qre.setCreditline("");
			}
			if (qre.getCreditnumber() == null){
				qre.setCreditnumber("");
			}
			if (qre.getBgxzcs() == null){
				qre.setBgxzcs("0");
			}
			if (qre.getDqxzcs() == null){
				qre.setDqxzcs("0");
			}
			if (qre.getDhxzcs() == null){
				qre.setDhxzcs("0");
			}
		}
		return queryListByPage;
	}
}
