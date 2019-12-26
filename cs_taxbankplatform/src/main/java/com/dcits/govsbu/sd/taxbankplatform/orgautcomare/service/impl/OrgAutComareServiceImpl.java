package com.dcits.govsbu.sd.taxbankplatform.orgautcomare.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.orgautcomare.mapper.OrgAutComareMapper;
import com.dcits.govsbu.sd.taxbankplatform.orgautcomare.model.OrgAutComareEntity;
import com.dcits.govsbu.sd.taxbankplatform.orgautcomare.service.OrgAutComareService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

/**
 * 
 * @author Administrator
 *
 */
@Service("orgautcomareservice")
public class OrgAutComareServiceImpl extends AbstractService<OrgAutComareEntity, String> implements OrgAutComareService {
	@Autowired
	private OrgAutComareMapper orgAutComareMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(orgAutComareMapper);
	}
	
	@Override
	public int insert(OrgAutComareEntity orgAutComareEntity){
		String id = IDGenerate.getZJID("XH");
		orgAutComareEntity.setCpId(id);
		return orgAutComareMapper.insert(orgAutComareEntity);
	}
	@Override
	public OrgAutComareEntity queryAuthorityByOrgId(Map<String, Object> parameter){
		return orgAutComareMapper.queryAuthorityByOrgId(parameter);
	};
	@Override
	public int deleteDataByOrgIdAndTaxId (Map<String, Object> parameter){
		return orgAutComareMapper.deleteDataByOrgIdAndTaxId(parameter);
	};
}
