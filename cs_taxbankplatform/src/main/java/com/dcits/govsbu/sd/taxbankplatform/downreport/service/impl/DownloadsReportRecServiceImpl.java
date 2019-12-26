package com.dcits.govsbu.sd.taxbankplatform.downreport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.downreport.mapper.DownloadsReportRecMapper;
import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.DownloadsReportRecService;

@Service("downloadsReportRecService")
public class DownloadsReportRecServiceImpl extends AbstractService<DownloadsReportRecEntity, String> implements DownloadsReportRecService {
	@Autowired
	private DownloadsReportRecMapper downloadsReportRecMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(downloadsReportRecMapper);
	}

	@Override
	public String findBylaid(String laId) {
		return downloadsReportRecMapper.findBylaid(laId);
	}

	@Override
	public DownloadsReportRecEntity findBylaid2(String laId) {
		return downloadsReportRecMapper.findBylaid2(laId);
	}
	@Override
	//获取跟踪列表中的下载报告信息
	public List<DownloadsReportRecEntity> findDownloadsReportRecById(String id){
		return downloadsReportRecMapper.findDownloadsReportRecById(id);
	}
}
