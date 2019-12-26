package com.dcits.govsbu.sd.taxbankplatform.downreport.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.downreport.mapper.DownloadsReportMapper;
import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportEntity;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.DownloadsReportService;

@Service("downloadsReportService")
public class DownloadsReportServiceImpl extends AbstractService<DownloadsReportEntity, String> implements DownloadsReportService {
	@Autowired
	private DownloadsReportMapper downloadsReportMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(downloadsReportMapper);
	}
	@Override
	public List<DownloadsReportEntity> findByLaId (Map<String, Object> parameter){
		return downloadsReportMapper.findByLaId(parameter);
	}
	//根据申请id 获取djxh
    @Override
	public String findDjxhById(String id){
    	return downloadsReportMapper.findDjxhById(id);
    }
    //删除企业基础信息
    @Override
	public int deleteQyjcxxByDjxh(String djxh){
    	return downloadsReportMapper.deleteQyjcxxByDjxh(djxh);
    }
    //删除企业基础信息扩展
    @Override
	public int deleteQyjcxxKzByDjxh(String djxh){
    	return downloadsReportMapper.deleteQyjcxxKzByDjxh(djxh);
    }
}
