package com.dcits.govsbu.sd.taxbankplatform.downreport.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportEntity;

@Repository 
public interface DownloadsReportMapper extends BaseMapper<DownloadsReportEntity, String> {
    int deleteByPrimaryKey(String drId);

    @Override
	int insert(DownloadsReportEntity record);

    DownloadsReportEntity selectByPrimaryKey(Integer drId);

    int updateByPrimaryKey(DownloadsReportEntity record);
    //根据laid查询数据
    List<DownloadsReportEntity> findByLaId (Map<String, Object> parameter);
    //根据申请id 获取djxh
    public String findDjxhById(String id);
    //删除企业基础信息
    public int deleteQyjcxxByDjxh(String djxh);
    //删除企业基础信息扩展
    public int deleteQyjcxxKzByDjxh(String djxh);
}