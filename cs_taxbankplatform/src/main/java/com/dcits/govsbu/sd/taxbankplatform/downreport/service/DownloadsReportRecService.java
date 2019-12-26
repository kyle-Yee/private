package com.dcits.govsbu.sd.taxbankplatform.downreport.service;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportRecEntity;

/**
 * 
 * @author Administrator
 *
 */
public interface DownloadsReportRecService {
	 String findBylaid(String laId);
	 DownloadsReportRecEntity findBylaid2(String laId);
	 //获取跟踪列表中的下载报告信息
	 public List<DownloadsReportRecEntity> findDownloadsReportRecById(String id);
}
