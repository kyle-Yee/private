package com.dcits.govsbu.sd.taxbankplatform.pdf.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqGsSwqdEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfSsyhzcEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfWfwzEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqSwupjxxEntity;

/**
 * 
 * @author Administrator
 *
 */
public interface CqPdfService {
	//更具纳税人识别号 获取最新的申请序号
	public String findSqxhByNsrsbh(String nsrsbh);
	//根据申请序号 找到企业的基础信息 包括 工商登记信息 税务登记信息
	public CqPdfEntity findCqQyjcxxBySqxh(String sqxh);
	//纳税清单
	public CqPdfEntity findCqNsqd(Map<String, Object> parameters);
	//税务评级信息
	public List<CqSwupjxxEntity> findCqSwpjxx(Map<String, Object> parameters);
	//税收优惠政策
	public List<CqPdfSsyhzcEntity> findCqSsyhzc(Map<String, Object> parameters);
	//违法违章明细
	public List<CqPdfWfwzEntity> findCqWfwzmx(Map<String, Object> parameters);
	//累计欠税额
	public String findCqLjqsxx(String sqxh);
	//根据申请id获取nsrsbh
	public String findNsrsbhById(String id);
	/**
	 * 根据申请id获取产品名称和组织id
	 * @Author Zhongyj
	 * @date 2017-4-21 上午9:39:29
	 * @param id
	 * @return
	 */
	public Map<String , Object> findFpnameById(String id);
	
	/**
	 * 获取重庆处理过的纳税清单数据
	 * @Author Zhongyj
	 * @date 2017-5-27 上午10:36:06
	 * @param map
	 * @return
	 */
	public List<CqGsSwqdEntity> findNsqdDataByNsrsbh (Map<String , Object> map);
	
	/**
	 * 根据纳税人识别号判断是否需要去取税务数据
	 * @Author Zhongyj
	 * @date 2017-5-27 下午5:27:59
	 * @param map
	 * @return
	 */
	public int findStatusByNsrsbh(Map<String , Object> map);
}
