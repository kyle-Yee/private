package com.dcits.govsbu.sd.taxbankplatform.auth.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.dcits.govsbu.sd.taxbankplatform.auth.mapper.SfrzMapper;
import com.dcits.govsbu.sd.taxbankplatform.auth.model.AuthResponseEntity;
import com.dcits.govsbu.sd.taxbankplatform.auth.service.AuthService;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.mapper.AuthorizationrecordMapper;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.mapper.InterfaceLogMapper;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.AuthorizationrecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.InterfaceLogEntity;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.teplate.AuthorizeEnum;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper.EnterprisestatisticsMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.EnterpriseInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.AuthTimeManger;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private EnterprisestatisticsMapper enterprisestatisticsMapper;
	@Autowired
	private AuthorizationrecordMapper authorizationrecordMapper;
	@Autowired
	private InterfaceLogMapper interfaceLogMapper;
	
	@Autowired
	private SfrzMapper sfrzMapper; // 身份认证Mapper

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public AuthResponseEntity auth(HashMap<String, Object> params) {
		
		if(null ==	params.get("fp_id")) {
			return new AuthResponseEntity(AuthorizeEnum.DM08.getStatusCode(),
					AuthorizeEnum.DM08.getStatusMsg() + "_产品id为空", null);
		}
		
		if(null ==	params.get("at_dkqx")) {
			return new AuthResponseEntity(AuthorizeEnum.DM08.getStatusCode(),
					AuthorizeEnum.DM08.getStatusMsg() + "贷款期限为空", null);
		}
		
		logger.info("====================新授权开始=====================");
		logger.info("====================银行传递过来的参数是" + params.toString());
		// 1.先查询授权记录表 如果存在记录 判断过期时间
		// 如果过期了走2.的逻辑重新进行授权 如果没过期 直接取授权码返回
		// 2.如果不存在记录则五要素验证 并插入授权记录表
		if (null == params.get("nsrsbh")||null== params.get("at_sqdid")) {// 如果请求参数中没有纳税人编号 || null == params.get("bh")
			logger.error("纳税人识别号或银行流水订单为空");
			return new AuthResponseEntity(AuthorizeEnum.DM08.getStatusCode(), AuthorizeEnum.DM08.getStatusMsg(), null);
		}
		// 查询授权记录表 
		Map<String, Object> sqparameter=new HashMap<String, Object>();
		sqparameter.put("at_nsrsbh", params.get("nsrsbh"));
		sqparameter.put("at_sqdid", params.get("at_sqdid"));
		List<AuthorizationrecordEntity> lists = authorizationrecordMapper.querySqByPage(sqparameter);
		// 存在授权记录 判断授权记录是否过期
		if (lists.size() > 0) { 
			String authTime = lists.get(0).getAt_sqsyxq(); 
			if (null!=authTime&&AuthTimeManger.AuthCanUse(authTime)&&lists.get(0).getGrantCode()!=null) { // 授权并没有过期 记录可用
				logger.info(params.get("nsrsbh") + "授权记录可用,授权码为" + lists.get(0).getGrantCode());
				return new AuthResponseEntity(AuthorizeEnum.DM00.getStatusCode(), AuthorizeEnum.DM00.getStatusMsg(),
						lists.get(0).getGrantCode());
			}
		}
		
			// 程序走到这里 说明 查询记录过期 或者 没有授权记录 
			//插入一条授权记录 授权状态为sqjg000
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Map<String, Object> insertMap = new HashMap<String,Object>();
				logger.info(params.get("nsrsbh") + "插入申请记录表开始 ==================");
				String at_id = IDGenerate.getZJID("XH"); // 主键(at_id)
				if (at_id.length() > 0 && null != at_id) {
					insertMap.put("at_id", at_id);//
				}
				//请求参数名 与数据库字段(mapper)不统一 要进行修正
				if(null!=params.get("sqsj")) {
					insertMap.put("at_sqsj",params.get("sqsj") );
				}
				insertMap.put("at_sqdid",params.get("at_sqdid"));//
				insertMap.put("at_qymc",params.get("qymc"));//
				insertMap.put("at_nsrsbh",params.get("nsrsbh"));//
				insertMap.put("at_sqzt", 1);//
				insertMap.put("sqzt", "sqjg000");//
				insertMap.put("createtime",df.format(new Date()));//
				insertMap.put("updatetime",df.format(new Date()));//
				insertMap.put("at_frxm", params.get("frxm"));//
				insertMap.put("at_sjmc", params.get("at_sjmc"));//
				insertMap.put("authtype", "01");//
				insertMap.put("sqjg", "授权初始化");//
				if(null!=params.get("fp_id")) {
					insertMap.put("fp_id", params.get("fp_id"));
				}
				if(null!=params.get("at_dkqx")) {
					insertMap.put("at_dkqx", params.get("at_dkqx"));
				}
				if(null!=params.get("at_sqsyxq")) {
					insertMap.put("at_sqsyxq", params.get("at_sqsyxq"));
				}
				if(null!=params.get("at_sqkssj")) {
					insertMap.put("at_sqkssj", params.get("at_sqkssj"));
				}
				if(null!=params.get("at_sqjssj")) {
					insertMap.put("at_sqjssj", params.get("at_sqjssj"));
				}
				
				// 将记录插入授权记录表
				
				try {
					String[] keys = new String[insertMap.size()];
					Set<String> sset = insertMap.keySet();
					int i = 0;
					for (String os : sset) {
						keys[i++] = os;
					}
					String[] keys2 = new String[insertMap.size()];
					Set<String> sset2 = insertMap.keySet();
					i = 0;
					for (String os : sset2) {
						keys2[i++] = os;
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("tablename", "tb_authorization_record");
					map.put("keys", keys);
					map.put("params", insertMap);
					authorizationrecordMapper.insert(map);
					logger.info("===============插入授权记录表成功======================");
				}catch (Exception e) {
					e.printStackTrace();
					logger.info("===============插入授权记录表失败======================");
					InterfaceLogEntity entity=new InterfaceLogEntity();
					entity.setIl_id(IDGenerate.getZJID("interface"));
					entity.setBh(insertMap.get("at_sqdid")+"");
					entity.setNsrsbh(insertMap.get("nsrsbh")+"");
					entity.setInterfaceName("saveRzSqjgGx");
					entity.setErrorLog("&插入授权记录表失败&"+e.getStackTrace()[0].getMethodName()+"方法第"+e.getStackTrace()[0].getLineNumber()+e.getStackTrace()[0].getClassName());
					entity.setContent(insertMap.toString());
				    interfaceLogMapper.insert(entity);
				    return new AuthResponseEntity(AuthorizeEnum.DM09.getStatusCode(),
							AuthorizeEnum.DM09.getStatusMsg(),null);
				}
				
				// --插入认证记录表  (认证记录表重复 意味着 流水单at_sqdid重复)
				Map<String, Object> rzparameter=new HashMap<String, Object>();
				rzparameter.put("cr_nsrsbh", params.get("nsrsbh"));
				rzparameter.put("cr_sqdid", params.get("at_sqdid"));
				List<EnterpriseInfoEntity> rzList=enterprisestatisticsMapper.queryRzByPage(rzparameter);
				//存在认证记录
				if(rzList.size()>0){
					logger.error(params.get("nsrsbh") + "已经存在认证记录==================");
					return new AuthResponseEntity(AuthorizeEnum.DM11.getStatusCode(),
							AuthorizeEnum.DM11.getStatusMsg()+"请生成新的流水单号"  , null);
				}else {
					Map<String, Object> insertMap2 = new HashMap<String,Object>();
					logger.info(params.get("nsrsbh") + "插入认证记录表开始 =================");
					//插入认证记录
					String cr_id = IDGenerate.getZJID("RZ001");
					insertMap2.put("cr_id",cr_id);
					//请求参数名 与数据库字段不统一 要进行修正
					if(null!=params.get("sqsj")) {
						insertMap2.put("cr_sqsj",params.get("sqsj"));
					}
					insertMap2.put("cr_qymc",params.get("qymc"));
					insertMap2.put("cr_nsrsbh",params.get("nsrsbh"));
					insertMap2.put("cr_frsjh",params.get("frsjh"));
					insertMap2.put("cr_zjlx","0");
					insertMap2.put("cr_zjhm",params.get("frsfz"));
					insertMap2.put("cr_rzsj",df.format(new Date()));
					insertMap2.put("cr_sqdid",params.get("at_sqdid"));
					insertMap2.put("cr_dbqk","认证初始化");
					insertMap2.put("cr_shjg","rzjg000");
					//TODO 创建人？
					insertMap2.put("createtime",df.format(new Date()));
					insertMap2.put("updatetime", df.format(new Date()));
					//插入认证表
					try {
						String[] keys = new String[insertMap2.size()];
						Set<String> sset = insertMap2.keySet();
						int i = 0;
						for (String os : sset) {
							keys[i++] = os;
						}
						String[] keys2 = new String[insertMap2.size()];
						Set<String> sset2 = insertMap2.keySet();
						i = 0;
						for (String os : sset2) {
							keys2[i++] = os;
						}
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("tablename", "tb_check_record");
						map.put("keys", keys);
						map.put("params", insertMap2);
						authorizationrecordMapper.insert(map);
						logger.info("===============插入认证记录表成功======================");
					} catch (Exception e) {
						e.printStackTrace();
						logger.info("===============插入认证记录表失败======================");
						InterfaceLogEntity entity=new InterfaceLogEntity();
						entity.setIl_id(IDGenerate.getZJID("interface"));
						entity.setBh(insertMap2.get("at_sqdid")+"");
						entity.setNsrsbh(insertMap2.get("nsrsbh")+"");
						entity.setInterfaceName("saveRzSqjgGxtwo");
						entity.setErrorLog("&插入认证记录表失败&");
						entity.setContent(insertMap2.toString());
					    interfaceLogMapper.insert(entity);
					    return new AuthResponseEntity(AuthorizeEnum.DM09.getStatusCode(),
								AuthorizeEnum.DM09.getStatusMsg(),null);
					}	
				}
				
	
			
		
		
		
		//插入一条记录授权状态为sqjg000 五要素验证成功之后改成sqjg001 如果没成功则为sqjg002
		//	认证记录状态 改为 rzjg001 认证成功
		//那直接进行五要素验证
		logger.info(params.get("nsrsbh") + "进行五要素验证");
		if (null == params.get("frxm")) {
			logger.error(params.get("frxm") + "进行五要素验证时法人姓名为空");
			Map<String, Object> updateMap = new HashMap<>();
			updateMap.put("at_sqdid", params.get("at_sqdid"));
			updateMap.put("sqzt", "sqjq002");
			updateMap.put("sqjg", "授权失败");
			updateMap.put("cr_shjg", "rzjg002");
			updateMap.put("cr_dbqk", "认证失败");
			authorizationrecordMapper.updateSQxx(updateMap);
			enterprisestatisticsMapper.updateRZxx(updateMap);
			return new AuthResponseEntity(AuthorizeEnum.DM08.getStatusCode(),
					AuthorizeEnum.DM08.getStatusMsg() + "_法人姓名为空", null);
		}
		if (null == params.get("frsfz")) {
			logger.error(params.get("nsrsbh") + "进行五要素验证时法人身份证为空");
			Map<String, Object> updateMap = new HashMap<>();
			updateMap.put("at_sqdid", params.get("at_sqdid"));
			updateMap.put("sqzt", "sqjq002");
			updateMap.put("sqjg", "授权失败");
			updateMap.put("cr_shjg", "rzjg002");
			updateMap.put("cr_dbqk", "认证失败");
			authorizationrecordMapper.updateSQxx(updateMap);
			enterprisestatisticsMapper.updateRZxx(updateMap);
			return new AuthResponseEntity(AuthorizeEnum.DM08.getStatusCode(),
					AuthorizeEnum.DM08.getStatusMsg() + "_法人身份证为空", null);
		}
		if (null == params.get("frsjh")) {
			logger.error(params.get("nsrsbh") + "进行五要素验证时法人移动电话为空");
			Map<String, Object> updateMap = new HashMap<>();
			updateMap.put("at_sqdid", params.get("at_sqdid"));
			updateMap.put("sqzt", "sqjq002");
			updateMap.put("sqjg", "授权失败");
			updateMap.put("cr_shjg", "rzjg002");
			updateMap.put("cr_dbqk", "认证失败");
			authorizationrecordMapper.updateSQxx(updateMap);
			enterprisestatisticsMapper.updateRZxx(updateMap);
			return new AuthResponseEntity(AuthorizeEnum.DM08.getStatusCode(),
					AuthorizeEnum.DM08.getStatusMsg() + "_法人移动电话为空", null);
		}
		if (null == params.get("qymc")) {
			logger.error(params.get("nsrsbh") + "进行五要素验证时企业名称为空");
			Map<String, Object> updateMap = new HashMap<>();
			updateMap.put("at_sqdid", params.get("at_sqdid"));
			updateMap.put("sqzt", "sqjq002");
			updateMap.put("sqjg", "授权失败");
			updateMap.put("cr_shjg", "rzjg002");
			updateMap.put("cr_dbqk", "认证失败");
			authorizationrecordMapper.updateSQxx(updateMap);
			enterprisestatisticsMapper.updateRZxx(updateMap);
			return new AuthResponseEntity(AuthorizeEnum.DM08.getStatusCode(),
					AuthorizeEnum.DM08.getStatusMsg() + "_企业名称为空", null);
		}

		// 到这里进行五要素验证
//		Integer authResult = sfrzMapper.queryAuthInfo(params); 
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setAddress("http://localhost:8081/wbjh-tax/services/QyxxQuery");
		factoryBean.setServiceClass(QyxxQueryService.class);//通过接口指定请求方法名称/返回类型/参数
		QyxxQueryService ex =(QyxxQueryService)factoryBean.create();
		Boolean  qyxxQueryResult = ex.QyxxQuery(params);
		

		if (!qyxxQueryResult) { // 五要素验证失败
			Map<String, Object> updateMap = new HashMap<>();
			updateMap.put("at_sqdid", params.get("at_sqdid"));
			updateMap.put("sqzt", "sqjq002");
			updateMap.put("sqjg", "授权失败");
			updateMap.put("cr_shjg", "rzjg002");
			updateMap.put("cr_dbqk", "认证失败");
			authorizationrecordMapper.updateSQxx(updateMap);
			enterprisestatisticsMapper.updateRZxx(updateMap);
			return new AuthResponseEntity(AuthorizeEnum.DM10.getStatusCode(),"身份验证失败", null);
		} else {
			//五要素验证成功
			 //验证成功修改授权状态为sqjq001
			Map<String, Object> updateMap = new HashMap<>();
			updateMap.put("at_sqdid", params.get("at_sqdid"));
			updateMap.put("sqzt", "sqjq001");
			updateMap.put("sqjg", "授权成功");
			updateMap.put("cr_shjg", "rzjg001");
			updateMap.put("cr_dbqk", "认证成功");
			String grandCode = IDGenerate.getZJID("SQS"); // -- 生成授权码  
			if (grandCode.length() > 0 && null != grandCode) {
				updateMap.put("grantCode", grandCode);
			}
			logger.info(params.get("nsrsbh") + "修改授权状态");
			//修改授权记录表状态为成功
			authorizationrecordMapper.updateSQxx(updateMap); 
			enterprisestatisticsMapper.updateRZxx(updateMap);
			return new AuthResponseEntity(AuthorizeEnum.DM00.getStatusCode(), AuthorizeEnum.DM00.getStatusMsg(),
					grandCode);
			
		}
	}



}
