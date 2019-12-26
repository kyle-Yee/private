package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.BgdjswbEntity;

@Repository
public interface BgdjswbMapper extends BaseMapper<BgdjswbEntity, String>{

	//添加判断
	public String ckByRegionId(BgdjswbEntity bgdjswbEntity);

	public String searchFilePath(String id);

}
