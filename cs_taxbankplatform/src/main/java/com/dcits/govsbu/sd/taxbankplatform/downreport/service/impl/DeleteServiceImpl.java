package com.dcits.govsbu.sd.taxbankplatform.downreport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.ajxx.mapper.TaxAjxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.bmyxcx.mapper.TaxBmyxcxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.downreport.mapper.DownloadsReportMapper;
import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportEntity;
import com.dcits.govsbu.sd.taxbankplatform.downreport.model.PdfEntity;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.DeleteService;
import com.dcits.govsbu.sd.taxbankplatform.fpkjxx.mapper.TaxFpKjxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.fprzxx.mapper.TaxFpRzxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbqykjzd.mapper.TaxLrbQykjzdEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykj.mapper.TaxLrbXqykjEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykjzd.mapper.TaxLrbXqykjzdEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbybqykj.mapper.TaxLrbYbqykjEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.nsrxydj.mapper.TaxNsrxydjEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.nsrzgxxjgb.mapper.TaxNsrzgxxjgbEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qsclxx.mapper.TaxQsclxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qybgxx.mapper.TaxQybgxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qyfzjg.mapper.TaxQyfzjgEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qypzjg.mapper.TaxQypzjgEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qytzf.mapper.TaxQytzfEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qyzcfzbxqykjzd.mapper.TaxQyzcfzbXqykjzdEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qyzfjgxx.mapper.TaxQyzfjgxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.sbxx.mapper.TaxSbxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.sswfxwdj.mapper.TaxSswfxwdjEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.xgmnsrsbmx.mapper.TaxXgmnsrsbmxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.ybnsrsbmxzb.mapper.TaxYbnsrsbmxzbEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.yjsf.mapper.TaxYjsfEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.zcfzbqykjzd.mapper.TaxZcfzbQykjzdEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.zcfzbybqykj.mapper.TaxZcfzbYbqykjEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.zlbscjb.mapper.TaxZlbscjbEntityMapper;
@Service("deleteService")
public class DeleteServiceImpl extends AbstractService<DownloadsReportEntity, String> implements DeleteService{
	@Autowired
	private TaxAjxxEntityMapper taxAjxxEntityMapper;
	@Autowired
	private TaxBmyxcxEntityMapper taxBmyxcxEntityMapper;
	@Autowired
	private TaxFpKjxxEntityMapper taxFpKjxxEntityMapper;
	@Autowired
	private TaxFpRzxxEntityMapper taxFpRzxxEntityMapper;
	@Autowired
	private TaxLrbQykjzdEntityMapper taxLrbQykjzdEntityMapper;
	@Autowired
	private TaxLrbXqykjEntityMapper taxLrbXqykjEntityMapper;
	@Autowired
	private TaxLrbXqykjzdEntityMapper taxLrbXqykjzdEntityMapper;
	@Autowired
	private TaxLrbYbqykjEntityMapper taxLrbYbqykjEntityMapper;
	@Autowired
	private TaxNsrxydjEntityMapper taxNsrxydjEntityMapper;
	@Autowired
	private TaxNsrzgxxjgbEntityMapper taxNsrzgxxjgbEntityMapper;
	@Autowired
	private TaxQsclxxEntityMapper taxQsclxxEntityMapper;
	@Autowired
	private TaxQybgxxEntityMapper taxQybgxxEntityMapper;
	@Autowired
	private TaxQyfzjgEntityMapper taxQyfzjgEntityMapper;
	@Autowired
	private TaxQypzjgEntityMapper taxQypzjgEntityMapper;
	@Autowired
	private TaxQytzfEntityMapper taxQytzfEntityMapper;
	@Autowired
	private TaxQyzcfzbXqykjzdEntityMapper taxQyzcfzbXqykjzdEntityMapper;
	@Autowired
	private TaxQyzfjgxxEntityMapper taxQyzfjgxxEntityMapper;
	@Autowired
	private TaxSbxxEntityMapper taxSbxxEntityMapper;
	@Autowired
	private TaxSswfxwdjEntityMapper taxSswfxwdjEntityMapper;
	@Autowired
	private TaxXgmnsrsbmxEntityMapper taxXgmnsrsbmxEntityMapper;
	@Autowired
	private TaxYbnsrsbmxzbEntityMapper taxYbnsrsbmxzbEntityMapper;
	@Autowired
	private TaxYjsfEntityMapper taxYjsfEntityMapper;
	@Autowired
	private TaxZcfzbQykjzdEntityMapper taxZcfzbQykjzdEntityMapper;
	@Autowired
	private TaxZcfzbYbqykjEntityMapper taxZcfzbYbqykjEntityMapper;
	@Autowired
	private TaxZlbscjbEntityMapper taxZlbscjbEntityMapper;
	@Autowired
	private DownloadsReportMapper downloadsReportMapper;
	
	@Override
	public int deleteByDjxh(String djxh){
		boolean open = PdfEntity.isOpen();
		if(open == true){
			boolean ajxx = PdfEntity.isAjxx();
			if(ajxx == true){
				taxAjxxEntityMapper.deleteByDjxh(djxh);
			}
			boolean bmyxcx = PdfEntity.isBmyxcx();
			if(bmyxcx == true){
				taxAjxxEntityMapper.deleteByDjxh(djxh);
			}
			boolean cgdkmxfkxx = PdfEntity.isCgdkmxfkxx();
			if(cgdkmxfkxx == true){
				taxAjxxEntityMapper.deleteByDjxh(djxh);
			}
			/*boolean fpKjxx = PdfEntity.isFpKjxx();
			if(fpKjxx == true){
				taxFpKjxxEntityMapper.deleteByDjxh(djxh);
			}
			boolean fpRzxx = PdfEntity.isFpRzxx();
			if(fpRzxx == true){
				taxFpRzxxEntityMapper.deleteByDjxh(djxh);
			}*/
			boolean lrbQykjzd = PdfEntity.isLrbQykjzd();
			if(lrbQykjzd == true){
				taxLrbQykjzdEntityMapper.deleteByDjxh(djxh);
			}
			boolean lrbXqykj = PdfEntity.isLrbXqykj();
			if(lrbXqykj == true){
				taxLrbXqykjEntityMapper.deleteByDjxh(djxh);
			}
			boolean lrbXqykjzd = PdfEntity.isLrbXqykjzd();
			if(lrbXqykjzd == true){
				taxLrbXqykjzdEntityMapper.deleteByDjxh(djxh);
			}
			boolean lrbYbqykj = PdfEntity.isLrbYbqykj();
			if(lrbYbqykj == true){
				taxLrbYbqykjEntityMapper.deleteByDjxh(djxh);
			}
			boolean nsrxydj = PdfEntity.isNsrxydj();
			if(nsrxydj == true){
				taxAjxxEntityMapper.deleteByDjxh(djxh);
			}
			boolean nsrzgxxjgb = PdfEntity.isNsrzgxxjgb();
			if(nsrzgxxjgb == true){
				taxNsrxydjEntityMapper.deleteByDjxh(djxh);
			}
			boolean qsclxx = PdfEntity.isQsclxx();
			if(qsclxx == true){
				taxQsclxxEntityMapper.deleteByDjxh(djxh);
			}
			boolean qybgxx = PdfEntity.isQybgxx();
			if(qybgxx == true){
				taxQybgxxEntityMapper.deleteByDjxh(djxh);
			}
			boolean qyfzjg = PdfEntity.isQyfzjg();
			if(qyfzjg == true){
				taxQyfzjgEntityMapper.deleteByDjxh(djxh);
			}
			boolean qyjcxx = PdfEntity.isQyjcxx();
			if(qyjcxx == true){
				downloadsReportMapper.deleteQyjcxxByDjxh(djxh);
			}
			boolean qyjcxxKz = PdfEntity.isQyjcxxKz();
			if(qyjcxxKz == true){
				downloadsReportMapper.deleteQyjcxxKzByDjxh(djxh);
			}
			boolean qypzjg = PdfEntity.isQypzjg();
			if(qypzjg == true){
					taxQypzjgEntityMapper.deleteByDjxh(djxh);
			}
			boolean qytzf = PdfEntity.isQytzf();
			if(qytzf == true){
				taxQytzfEntityMapper.deleteByDjxh(djxh);
			}
			boolean qyzcfzbXqykjzd = PdfEntity.isQyzcfzbXqykjzd();
			if(qyzcfzbXqykjzd == true){
				taxQyzcfzbXqykjzdEntityMapper.deleteByDjxh(djxh);
			}
			boolean qyzfjgxx = PdfEntity.isQyzfjgxx();
			if(qyzfjgxx == true){
				taxQyzfjgxxEntityMapper.deleteByDjxh(djxh);
			}
			boolean sbxx = PdfEntity.isSbxx();
			if(sbxx == true){
				taxSbxxEntityMapper.deleteByDjxh(djxh);
			}
			boolean sswfxwdj = PdfEntity.isSswfxwdj();
			if(sswfxwdj == true){
				taxSswfxwdjEntityMapper.deleteByDjxh(djxh);
			}
			boolean xgmnsrsbmx = PdfEntity.isXgmnsrsbmx();
			if(xgmnsrsbmx == true){
				taxXgmnsrsbmxEntityMapper.deleteByDjxh(djxh);
			}
			boolean ybnsrsbmxzb = PdfEntity.isYbnsrsbmxzb();
			if(ybnsrsbmxzb == true){
				taxYbnsrsbmxzbEntityMapper.deleteByDjxh(djxh);
			}
			boolean yjsf = PdfEntity.isYjsf();
			if(yjsf == true){
				taxYjsfEntityMapper.deleteByDjxh(djxh);
			}
			/*boolean ysxmxfkxx = PdfEntity.isYsxmxfkxx();
			if(ajxx == true){
				taxAjxxEntityMapper.deleteByDjxh(djxh);
			}
			boolean zcfzbQykjzd = PdfEntity.isZcfzbQykjzd();
			if(zcfzbQykjzd == true){
				taxZcfzbQykjzdEntityMapper.deleteByDjxh(djxh);
			}*/
			boolean zcfzbYbqykj = PdfEntity.isZcfzbYbqykj();
			if(zcfzbYbqykj == true){
				taxZcfzbYbqykjEntityMapper.deleteByDjxh(djxh);
			}
			boolean zlbscjb = PdfEntity.isZlbscjb();
			if(zlbscjb == true){
				taxZlbscjbEntityMapper.deleteByDjxh(djxh);
			}
		}
		
		return 0;
	};
}
