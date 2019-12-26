package com.dcits.govsbu.sd.taxbankplatform.dataExchange.service.impl;

import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.dataExchange.common.Commons;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.common.ServiceCommon;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.common.request.RequestConfig;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.AjxxService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.BgdjmxService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.FzjgxxService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.LpbmcxService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.NsrjcxxService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.NsrxxkzService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.NsrzgxxjgbService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.PzjgxxService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.QsclxxService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.QylrbService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.QyzcfzbService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.SbxxService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.SswfxwdjService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.TzfxxService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.XgmnsrService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.XqylrbService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.XqylrbnbService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.XqyzcfzbService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.XydjService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.YbqylrbService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.YbqyzcfzbService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.YjsfService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.YnbsrService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.ZlbscjbService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice.ZzjgxxService;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.TirIpPackage;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.service.RequestService;

/**
 * 功能:
 * 数据交换请求接口服务
 * @author Administrator
 */
@Service("requestService")
public class RequestServiceImpl implements RequestService {

	@Override
	public TirIpPackage requestInfo(RequestConfig requestConfig) {
//		// TODO Auto-generated method stub
//		TirIpPackage tirIpPackage = Commons.parseFromXml(Commons.achieveMessage(requestConfig));
//		// 创建WebService客户端代理工厂
//        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//        // 注册WebService接口
//        factory.setServiceClass(BusinessService.class);
//        // 设置WebService地址
//        factory.setAddress("http://localhost:8080/tbp-datacx-service/datacxservice/Transaction");
//        BusinessService businessService = (BusinessService) factory.create();
//        // 调用纳税人认证接口
//        String v_message = businessService.BusinessInfo(tirIpPackage);
		String v_message = null;
		
		switch (requestConfig.getServiceId()) {
		case ServiceCommon.SERVICE_ID_NSRJCXX:
			v_message = NsrjcxxService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_NSRXXKZ:
			v_message = NsrxxkzService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_PZJGXX:
			v_message = PzjgxxService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_BGDJMX:
			v_message = BgdjmxService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_TZFXX:
			v_message = TzfxxService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_FZJGXX:
			v_message = FzjgxxService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_ZZJGXX:
			v_message = ZzjgxxService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_SSWFXWDJ:
			v_message = SswfxwdjService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_AJXX:
			v_message = AjxxService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_SBXX:
			v_message = SbxxService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_YJSF:
			v_message = YjsfService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_XQYZCFZB:
			v_message = XqyzcfzbService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_XQYLRB:
			v_message = XqylrbService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_YNBSR:
			v_message = YnbsrService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_XGMNSR:
			v_message = XgmnsrService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_NSRZGXXJGB:
			v_message = NsrzgxxjgbService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_QSCLXX:
			v_message = QsclxxService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_ZLBSCJB:
			v_message = ZlbscjbService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_QYZCFZB:
			v_message = QyzcfzbService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_QYLRB:
			v_message = QylrbService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_YBQYZCFZB:
			v_message = YbqyzcfzbService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_YBQYLRB:
			v_message = YbqylrbService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_XQYLRBNB:
			v_message = XqylrbnbService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_LPBMCX:
			v_message = LpbmcxService.getTirIpPackage();
			break;
		case ServiceCommon.SERVICE_ID_XYDJ:
			v_message = XydjService.getTirIpPackage();
			break;
		default:
			break;
		}
        return Commons.parseFromXml(v_message);
	}
}