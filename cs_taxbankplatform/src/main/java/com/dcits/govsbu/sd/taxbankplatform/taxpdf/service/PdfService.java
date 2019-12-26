package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.PdfMapEntity;

public interface PdfService {
	//查询生成PDF表格的数据
    public List<PdfMapEntity> findPdfMap();
}
