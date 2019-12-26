package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.BgdjswbEntity;

public interface BgdjswbService {

	public int insert(BgdjswbEntity bgdjswbEntity);

	public List<BgdjswbEntity> queryListByPage(Map<String, Object> parameters);

	public BgdjswbEntity findById(String id);

	public int update(BgdjswbEntity bgdjswbEntity);

	public String ckByRegionId(BgdjswbEntity bgdjswbEntity);

	public String searchFilePath(String id);

}
