package com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.mapper.HtmlTagMapper;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.HtmlTag;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.service.HtmlTagService;
@Service("HtmlTagService")
public class HtmlTagServiceImpl   extends AbstractService<HtmlTag, String> implements HtmlTagService {
   @Autowired
   private HtmlTagMapper htmlTagMapper;
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		
		super.setBaseMapper(htmlTagMapper);
		
	}
	@Override
	public List<HtmlTag> select() {
		return htmlTagMapper.select();
	}

}
