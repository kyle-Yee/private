package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper.PdfMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.PdfMapEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.PdfService;
@Service("pdfService")
public class PdfServiceImpl extends AbstractService<PdfMapEntity, String> implements PdfService {
	@Autowired
	private PdfMapper pdfMapper;
	
	public PdfMapper getPdfMapper() {
		return pdfMapper;
	}
	public void setPdfMapper(PdfMapper pdfMapper) {
		this.pdfMapper = pdfMapper;
	}
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(pdfMapper);
	}
	//查询生成PDF表格的数据
    @Override
	public List<PdfMapEntity> findPdfMap(){
    	return pdfMapper.findPdfMap();
    };
}
