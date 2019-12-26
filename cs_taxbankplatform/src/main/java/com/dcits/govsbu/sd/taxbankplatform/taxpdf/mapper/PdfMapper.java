package com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.PdfMapEntity;
@Repository
public interface PdfMapper extends BaseMapper<PdfMapEntity, String> {
	//查询生成PDF表格的数据
    public List<PdfMapEntity> findPdfMap();
    
    //根据regionname获取regioncode
    public String getRegionCodeByName(String regionName);
}
