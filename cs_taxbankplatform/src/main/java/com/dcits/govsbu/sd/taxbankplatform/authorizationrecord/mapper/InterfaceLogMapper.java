package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.InterfaceLogEntity;
import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;

@Repository
public interface InterfaceLogMapper extends BaseMapper<InterfaceLogEntity, String > {

	 public int insert(InterfaceLogEntity entity);

}
