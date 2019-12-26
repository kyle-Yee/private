package com.dcits.govsbu.sd.taxbankplatform.orgautcomare.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;
import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.orgautcomare.model.OrgAutComareEntity;
/**
 * 
 * @author Administrator
 *
 */
@Repository
public interface OrgAutComareMapper extends BaseMapper<OrgAutComareEntity, String > {
	//查询表中是否存在已经插入的数据
	public OrgAutComareEntity queryAuthorityByOrgId (Map<String, Object> parameter);
	//删除不需要的数据
	public int deleteDataByOrgIdAndTaxId (Map<String, Object> parameter);
}
