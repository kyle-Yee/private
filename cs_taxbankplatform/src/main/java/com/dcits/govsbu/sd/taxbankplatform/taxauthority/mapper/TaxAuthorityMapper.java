package com.dcits.govsbu.sd.taxbankplatform.taxauthority.mapper;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.model.TaxAuthorityEntity;
/**
 * 
 * @author Administrator
 *
 */
@Repository
public interface TaxAuthorityMapper extends BaseMapper<TaxAuthorityEntity, String> {
    
    @Override
	public List<TaxAuthorityEntity> queryListAll(Map<String, Object> parameter);

}
