package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper.BgdjswbMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.BgdjswbEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.BgdjswbService;

@Service
public class BgdjswbServiceImpl extends AbstractService<BgdjswbEntity, String> implements BgdjswbService {

	@Autowired
	private BgdjswbMapper bgdjswbMapper;

	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(bgdjswbMapper);
	}

	/**
	 * 添加判断
	 */
	@Override
	public String ckByRegionId(BgdjswbEntity bgdjswbEntity) {
		return bgdjswbMapper.ckByRegionId(bgdjswbEntity);
	}

	@Override
	public String searchFilePath(String id) {
		return bgdjswbMapper.searchFilePath(id);
	}
}
