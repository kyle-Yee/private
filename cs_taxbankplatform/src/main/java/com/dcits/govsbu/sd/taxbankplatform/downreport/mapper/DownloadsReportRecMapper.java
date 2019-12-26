package com.dcits.govsbu.sd.taxbankplatform.downreport.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportRecEntity;

@Repository 
public interface DownloadsReportRecMapper extends BaseMapper<DownloadsReportRecEntity, String>{
    int deleteByPrimaryKey(String drrId);

    @Override
	int insert(DownloadsReportRecEntity record);

    DownloadsReportRecEntity selectByPrimaryKey(String drrId);

    int updateByPrimaryKey(DownloadsReportRecEntity record);
    
    String findBylaid(String laId);
    
    DownloadsReportRecEntity findBylaid2(String laId);
    
    public List<DownloadsReportRecEntity> findDownloadsReportRecById(String id);
}