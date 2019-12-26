package com.dcits.govsbu.sd.taxbankplatform.pdf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.pdf.mapper.CqPdfMapper;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqGsSwqdEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfSsyhzcEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfWfwzEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqSwupjxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.service.CqPdfService;

@Service("cqPdfService")
public class CqPdfServiceImpl  extends AbstractService<CqPdfEntity, String> implements CqPdfService {
	@Autowired
	private CqPdfMapper cqPdfMapper;
	
	public CqPdfMapper getCqPdfMapper() {
		return cqPdfMapper;
	}
	public void setCqPdfMapper(CqPdfMapper cqPdfMapper) {
		this.cqPdfMapper = cqPdfMapper;
	}
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(cqPdfMapper);
	}
	//更具纳税人识别号 获取最新的申请序号
	@Override
	public String findSqxhByNsrsbh(String nsrsbh){
		return cqPdfMapper.findSqxhByNsrsbh(nsrsbh);
	}
	//根据申请序号 找到企业的基础信息 包括 工商登记信息 税务登记信息
	@Override
	public CqPdfEntity findCqQyjcxxBySqxh(String sqxh){
		return cqPdfMapper.findCqQyjcxxBySqxh(sqxh);
	}
	
	//纳税清单
	@Override
	public CqPdfEntity findCqNsqd(Map<String, Object> parameters){
		return cqPdfMapper.findCqNsqd(parameters);
	}
	//税务评级信息
	@Override
	public List<CqSwupjxxEntity> findCqSwpjxx(Map<String, Object> parameters){
		return cqPdfMapper.findCqSwpjxx(parameters);
	}
	//税收优惠政策
	@Override
	public List<CqPdfSsyhzcEntity> findCqSsyhzc(Map<String, Object> parameters){
		return cqPdfMapper.findCqSsyhzc(parameters);
	}
	//违法违章明细
	@Override
	public List<CqPdfWfwzEntity> findCqWfwzmx(Map<String, Object> parameters){
		return cqPdfMapper.findCqWfwzmx(parameters);
	}
	//累计欠税额
	@Override
	public String findCqLjqsxx(String sqxh){
		return cqPdfMapper.findCqLjqsxx(sqxh);
	}
	//根据申请id获取nsrsbh
	@Override
	public String findNsrsbhById(String id){
		return cqPdfMapper.findNsrsbhById(id);
	}
	/**
	 * 根据申请id获取产品名称和组织id
	 * @Author Zhongyj
	 * @date 2017-4-21 上午9:39:29
	 * @param id
	 * @return
	 */
	@Override
	public Map<String , Object> findFpnameById(String id){
		return cqPdfMapper.findFpnameById(id);
	}
	
	/**
	 * 获取重庆处理过的纳税清单数据
	 * @Author Zhongyj
	 * @date 2017-5-27 上午10:36:06
	 * @param map
	 * @return
	 */
	@Override
	public List<CqGsSwqdEntity> findNsqdDataByNsrsbh (Map<String , Object> map){
		return cqPdfMapper.findNsqdDataByNsrsbh(map);
	}
	
	/**
	 * 根据纳税人识别号判断是否需要去取税务数据
	 * 返回值大于1不需要去调取接口
	 * @Author Zhongyj
	 * @date 2017-5-27 下午5:27:59
	 * @param map
	 * @return
	 */
	@Override
	public int findStatusByNsrsbh(Map<String , Object> map) {
		return cqPdfMapper.findStatusByNsrsbh(map);
	}
}
