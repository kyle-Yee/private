package com.dcits.govsbu.sd.taxbankplatform.downreport.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportEntity;

/**
 * 
 * @author Administrator
 *
 */
public interface DownloadsReportService {
	public List<DownloadsReportEntity> findByLaId (Map<String, Object> parameter);
	//根据申请id 获取djxh
    public String findDjxhById(String id);
    //删除企业基础信息
    public int deleteQyjcxxByDjxh(String djxh);
    //删除企业基础信息扩展
    public int deleteQyjcxxKzByDjxh(String djxh);
}
