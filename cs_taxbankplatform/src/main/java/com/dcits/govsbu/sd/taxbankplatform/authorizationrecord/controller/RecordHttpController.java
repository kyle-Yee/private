package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.mapper.InterfaceLogMapper;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.AuthorizationrecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.InterfaceLogEntity;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.service.AuthorizationrecordService;
import com.dcits.govsbu.sd.taxbankplatform.loanagreement.service.LoanAgreementService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.EnterpriseInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.EnterprisestatisticsService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.ParametersService;
import com.dcits.govsbu.sd.taxbankplatform.util.DateFormatter;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
/**
 * 接口
 * @author yaofang
 * @time 2017年9月23日14:21:38
 */
@Controller
@Scope("prototype")
public class RecordHttpController {
	/**
	 * 日志
	 */
	public Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private AuthorizationrecordService authorizationrecordService;
	@Autowired
	private EnterprisestatisticsService enterprisestatisticsService;
	@Autowired
	private LoanAgreementService loanAgreementService; //贷款协议
	@Autowired
	private ParametersService parametersService;
	@Autowired
	private InterfaceLogMapper interfaceLogMapper;
	
	//供第三方调取授权记录显示
		@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
		@RequestMapping(value = "/querySqxxGx", method = RequestMethod.POST)
		protected void querySqxxGx(HttpServletRequest request,HttpServletResponse response){			
			logger.info("/querySqxxGx*******************************begin^*^");			
			BufferedReader reader = null;
			StringBuilder sb = new StringBuilder();
			Map<String, String> respMap = new HashMap<String, String>();
			//请求的内容
			String content = "";
			String bh = "";
			String respString = "";			
			String nsrsbh ="";
			try {
				reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {  
					sb.append(line);   
				}  
				content = sb.toString();
				//如果请求json不为空
				if (!content.equals("")) {
					Map<String, Object> requestcontentJsonmap = JSON.parseObject(content);
					if(requestcontentJsonmap.get("nsrsbh")!=null){
						nsrsbh=requestcontentJsonmap.get("nsrsbh").toString();	
					}
					if (requestcontentJsonmap.get("bh")!=null) {
						bh = requestcontentJsonmap.get("bh").toString();
					}
					Map<String, Object> parameters=new HashMap<String, Object>();					
					parameters.put("at_nsrsbh", nsrsbh);					
					parameters.put("at_sqdid",bh);					
					List<AuthorizationrecordEntity> lists = authorizationrecordService.querySqByPage(parameters);
					if(lists.size()>0){
						System.out.println("====中转数据库存在数据====");
						//存在授权数据 直接取
						respMap.put("frmc", lists.get(0).getAt_frxm());
						respMap.put("nsrsbh",lists.get(0).getAt_nsrsbh());
						respMap.put("qymc", lists.get(0).getAt_qymc());
						respMap.put("swjgmc", lists.get(0).getAt_sjmc());
						respMap.put("sqqx", lists.get(0).getAt_sqsj());
						respMap.put("zjhm",lists.get(0).getAt_frsfz());
						respMap.put("swcxz",lists.get(0).getAt_sqjssj());
						respMap.put("swcxq",lists.get(0).getAt_sqkssj());
						respMap.put("swjgmc","国家税务总局广西壮族自治区税务局");
						respMap.put("bh",bh);
						respMap.put("sqsyxq", lists.get(0).getAt_sqsyxq());
						respMap.put("cpmc","纳税信用相关信贷业务");
						respMap.put("yhmc",lists.get(0).getOrganizationEntity().getOrgname());
						respMap.put("dkqx",lists.get(0).getAt_dkqx());
						respMap.put("resultcode", "00");
						respMap.put("resultmsg", "成功");
						respString= JSON.toJSONString(respMap);
					}else{
						//调用接口
						SendHttpPostRequest sp = new  SendHttpPostRequest();
						//String urls = "http://127.0.0.1:8100/tbp/querySqxxGxtwo";
						String urls = parametersService.QueryValueByCode("querySqxxGxtwo");
						String requestJson= sp.sendPost(urls,content);
						Map<String, Object> requestJsonmap = JSON.parseObject(requestJson);
						System.out.println("querySqxxGxtwo返回="+requestJson);
						//返回数据处理
						String resultcode="";
						if(requestJsonmap.get("resultcode")!=null){
							resultcode=(String) requestJsonmap.get("resultcode");
						}
						if(("00").equals(resultcode)){//返回成功
							//返回成功且有数据
							//调用入库接口
							//String rkurl = "http://127.0.0.1:8100/tbp/saveRzSqjgGxtwo";
							String rkurl = parametersService.QueryValueByCode("saveRzSqjgGxtwo");
							//传入返回json字符串
							String requestrkJson= sp.sendPost(rkurl,requestJson);
							//解析入库结果
							Map<String, Object> requestrkJsonmap = JSON.parseObject(requestrkJson);
							//返回数据处理
							String resultrkcode="";
							if(requestJsonmap.get("resultcode")!=null){
								resultrkcode=(String) requestJsonmap.get("resultcode");
							}
							if(("00").equals(resultrkcode)){//入库成功
							    //查询记录并返回预览
								Map<String, Object> parameter=new HashMap<String, Object>();
								parameter.put("at_nsrsbh", nsrsbh);
								parameter.put("at_sqdid",bh);
								List<AuthorizationrecordEntity> list = authorizationrecordService.querySqByPage(parameter);
								if(list.size()>0){
									respMap.put("frmc", list.get(0).getAt_frxm());
									respMap.put("nsrsbh",list.get(0).getAt_nsrsbh());
									respMap.put("qymc", list.get(0).getAt_qymc());
									respMap.put("swjgmc", list.get(0).getAt_sjmc());
									respMap.put("sqqx", list.get(0).getAt_sqsj());
									respMap.put("zjhm",list.get(0).getAt_frsfz());
									respMap.put("swcxz",list.get(0).getAt_sqjssj());
									respMap.put("swcxq",list.get(0).getAt_sqkssj());
									respMap.put("swjgmc","国家税务总局广西壮族自治区税务局");
									respMap.put("bh",bh);
									respMap.put("sqsyxq", list.get(0).getAt_sqsyxq());
									respMap.put("cpmc","纳税信用相关信贷业务");
									respMap.put("yhmc",list.get(0).getOrganizationEntity().getOrgname());
									respMap.put("dkqx",list.get(0).getAt_dkqx());
									respMap.put("resultcode", "00");
									respMap.put("resultmsg", "成功");
									respString= JSON.toJSONString(respMap);
								}else{
									respMap.put("frmc", "");
									respMap.put("nsrsbh","");
									respMap.put("qymc","");
									respMap.put("swjgmc","");
									respMap.put("sqqx","");
									respMap.put("zjhm","");
									respMap.put("swcxz","");
									respMap.put("swcxq","");
									respMap.put("swjgmc","");
									respMap.put("bh",bh);
									respMap.put("sqsyxq","");
									respMap.put("cpmc","");
									respMap.put("yhmc","");
									respMap.put("dkqx","");
									respMap.put("resultcode", "02");
									respMap.put("resultmsg", "查询无记录");
									respString= JSON.toJSONString(respMap);
								}	
							}else{
								respMap.put("resultcode", "09");
								respMap.put("resultmsg", "授权认证数据入库失败");
								respString= JSON.toJSONString(respMap);
							}	
						}else{
							respMap.put("resultcode", "09");
							respMap.put("resultmsg", "页面预览二层接口调用失败");
							respString= JSON.toJSONString(respMap);
						}					
					}
					
				}else{
				}
			} catch (Exception e) {
	            e.printStackTrace();
				logger.info("&返回页面预览接口失败&");
				InterfaceLogEntity entity=new InterfaceLogEntity();
				entity.setIl_id(IDGenerate.getZJID("interface"));
				entity.setBh(bh);
				entity.setNsrsbh(nsrsbh);
				entity.setInterfaceName("querySqxxGx");
				entity.setErrorLog("&返回页面预览接口失败&");
				entity.setContent(content);
				interfaceLogMapper.insert(entity);
				if (bh.equals("")) {
					respMap.put("bh","");
					respMap.put("resultcode", "09");
					respMap.put("resultmsg", "页面预览一层接口调用失败");
					respString= JSON.toJSONString(respMap);
				}else {
					respMap.put("bh", bh);
					respMap.put("resultcode", "09");
					respMap.put("resultmsg", "系统错误");
					respString= JSON.toJSONString(respMap);
				}
				e.printStackTrace();
			}finally{
				try
				{
					if (null != reader){
						reader.close();
					}
				} catch (Exception e){

					e.printStackTrace();

				}
			}
			response.setContentType("text/xml");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				System.out.println(respString);
				out.print(respString);

			} catch (IOException e) {
				logger.info("======================返回预览结果报错=====================");
				e.printStackTrace();
			}finally{
				try
				{
					if (null != out)
					{
						out.close();
					}
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			logger.info("/querySqxxGx*******************************end^*^");
		}
		

		//调取完整授权记录，认证记录
			@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
			@RequestMapping(value = "/querySqxxGxtwo", method = RequestMethod.POST)
			protected void querySqxxGxtwo(HttpServletRequest request,HttpServletResponse response){
				logger.info("/querySqxxGxtwo*******************************begin^*^");
				BufferedReader reader = null;
				StringBuilder sb = new StringBuilder();
				Map<String, String> respMap = new HashMap<String, String>();
				//请求的内容
				String content = "";
				String bh = "";
				String respString = "";
				String nsrsbh ="";
				try {
					reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
					String line = null;
					while ((line = reader.readLine()) != null) {   
						sb.append(line);   
					}  
					content = sb.toString();
					//如果请求json不为空
					if (!content.equals("")) {
						System.out.println("=========一层向二层请求预览请求参数========="+content);
						Map<String, Object> requestJsonmap = JSON.parseObject(content);					
						if(requestJsonmap.get("nsrsbh")!=null){
							nsrsbh=requestJsonmap.get("nsrsbh").toString();	
						}
						if (requestJsonmap.get("bh")!=null) {
							bh = requestJsonmap.get("bh").toString();
						}
						Map<String, Object> sqparameter=new HashMap<String, Object>();
						sqparameter.put("at_nsrsbh", nsrsbh);
						sqparameter.put("at_sqdid", bh);
						Map<String, Object> rzparameter=new HashMap<String, Object>();
						rzparameter.put("cr_nsrsbh", nsrsbh);
						rzparameter.put("cr_sqdid", bh);
						logger.info("查询预授权信息at_nsrsbh:"+nsrsbh+" 与 cr_sqdid："+ bh);
						List<AuthorizationrecordEntity> Sqlist = authorizationrecordService.querySqByPage(sqparameter);
						List<EnterpriseInfoEntity> rzList=enterprisestatisticsService.queryRzByPage(rzparameter);
						logger.info("查询预授权信息Sqlist:"+Sqlist.size()+" 与 rzList："+ rzList.size());
						Map<String, String> sqmap = new HashMap<String, String>();
						String at_id ="";
						if(Sqlist.get(0).getAt_id()!=null&&!("").equals(Sqlist.get(0).getAt_id())){
							at_id=Sqlist.get(0).getAt_id();
							sqmap.put("at_id", at_id);
						}
						String at_sqsj ="";
						
						if(Sqlist.get(0).getAt_sqsj()!=null&&!("").equals(Sqlist.get(0).getAt_sqsj())){

							at_sqsj=Sqlist.get(0).getAt_sqsj();

							sqmap.put("at_sqsj", at_sqsj);

						}
						String at_qymc ="";

						if(Sqlist.get(0).getAt_qymc()!=null&&!("").equals(Sqlist.get(0).getAt_qymc())){

							at_qymc=Sqlist.get(0).getAt_qymc();
							
							sqmap.put("at_qymc", at_qymc);

						}
						String at_nsrsbh ="";

						if(Sqlist.get(0).getAt_nsrsbh()!=null&&!("").equals(Sqlist.get(0).getAt_nsrsbh())){

							at_nsrsbh=Sqlist.get(0).getAt_nsrsbh();
							
							sqmap.put("at_nsrsbh", at_nsrsbh);

						}
						String at_sqkssj ="";

						if(Sqlist.get(0).getAt_sqkssj()!=null&&!("").equals(Sqlist.get(0).getAt_sqkssj())){

							at_sqkssj=Sqlist.get(0).getAt_sqkssj();
							
							sqmap.put("at_sqkssj", at_sqkssj);

						}
						String at_sqjssj ="";

						if(Sqlist.get(0).getAt_sqjssj()!=null&&!("").equals(Sqlist.get(0).getAt_sqjssj())){

							at_sqjssj=Sqlist.get(0).getAt_sqjssj();
							
							sqmap.put("at_sqjssj", at_sqjssj);

						}
						String at_sqsjqx ="";

						if(Sqlist.get(0).getAt_sqsjqx()!=null&&!("").equals(Sqlist.get(0).getAt_sqsjqx())){

							at_sqsjqx=Sqlist.get(0).getAt_sqsjqx();
							
							sqmap.put("at_sqsjqx", at_sqsjqx);

						}
						String at_sqzt ="";

						if(Sqlist.get(0).getAt_sqzt()!=null&&!("").equals(Sqlist.get(0).getAt_sqzt())){

							at_sqzt=Sqlist.get(0).getAt_sqzt().toString();
							
							sqmap.put("at_sqzt", at_sqzt);

						}
						String at_sqyy ="";

						if(Sqlist.get(0).getAt_sqyy()!=null&&!("").equals(Sqlist.get(0).getAt_sqyy())){

							at_sqyy=Sqlist.get(0).getAt_sqyy();
							
							sqmap.put("at_sqyy", at_sqyy);

						}
						String sqzt ="";

						if(Sqlist.get(0).getSqzt()!=null&&!("").equals(Sqlist.get(0).getSqzt())){

							sqzt=Sqlist.get(0).getSqzt().toString();
							
							sqmap.put("sqzt", sqzt);

						}
						String sqjg ="";

						if(Sqlist.get(0).getSqjg()!=null&&!("").equals(Sqlist.get(0).getSqjg())){

							sqjg=Sqlist.get(0).getSqjg();
							
							sqmap.put("sqjg", sqjg);

						}
						String at_frxm ="";

						if(Sqlist.get(0).getAt_frxm()!=null&&!("").equals(Sqlist.get(0).getAt_frxm())){

							at_frxm=Sqlist.get(0).getAt_frxm();
							
							sqmap.put("at_frxm", at_frxm);

						}
						String at_frsfz ="";

						if(Sqlist.get(0).getAt_frsfz()!=null&&!("").equals(Sqlist.get(0).getAt_frsfz())){

							at_frsfz=Sqlist.get(0).getAt_frsfz();
							
							sqmap.put("at_frsfz", at_frsfz);

						}
						String at_sjmc ="";

						if(Sqlist.get(0).getAt_sjmc()!=null&&!("").equals(Sqlist.get(0).getAt_sjmc())){

							at_sjmc=Sqlist.get(0).getAt_sjmc();
							
							sqmap.put("at_sjmc", at_sjmc);

						}
						String org_id ="";

						if(Sqlist.get(0).getOrg_id()!=null&&!("").equals(Sqlist.get(0).getOrg_id())){

							org_id=Sqlist.get(0).getOrg_id();
							
							sqmap.put("org_id", org_id);

						}
						String fp_id ="";

						if(Sqlist.get(0).getFp_id()!=null&&!("").equals(Sqlist.get(0).getFp_id())){

							fp_id=Sqlist.get(0).getFp_id();
							
							sqmap.put("fp_id", fp_id);

						}
						String ag_id ="";

						if(Sqlist.get(0).getAg_id()!=null&&!("").equals(Sqlist.get(0).getAg_id())){

							ag_id=Sqlist.get(0).getAg_id();
							
							sqmap.put("ag_id", ag_id);

						}
						String at_sqsyxq ="";

						if(Sqlist.get(0).getAt_sqsyxq()!=null&&!("").equals(Sqlist.get(0).getAt_sqsyxq())){

							at_sqsyxq=Sqlist.get(0).getAt_sqsyxq();
							
							sqmap.put("at_sqsyxq", at_sqsyxq);

						}
						String at_sqdid ="";

						if(Sqlist.get(0).getAt_sqdid()!=null&&!("").equals(Sqlist.get(0).getAt_sqdid())){

							at_sqdid=Sqlist.get(0).getAt_sqdid();
							
							sqmap.put("at_sqdid", at_sqdid);

						}
						
						String at_dkqx ="";

						if(Sqlist.get(0).getAt_dkqx()!=null&&!("").equals(Sqlist.get(0).getAt_dkqx())){

							at_dkqx=Sqlist.get(0).getAt_dkqx();
							
							sqmap.put("at_dkqx", at_dkqx);

						}
						
						String la_id ="";

						if(Sqlist.get(0).getLa_id()!=null&&!("").equals(Sqlist.get(0).getLa_id())){

							la_id=Sqlist.get(0).getLa_id().toString();
							
							sqmap.put("la_id", la_id);

						}
						String createtime ="";

						if(Sqlist.get(0).getCreatetime()!=null&&!("").equals(Sqlist.get(0).getCreatetime())){

							  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
							  
								createtime=dateFormat.format(Sqlist.get(0).getCreatetime());
								
								sqmap.put("createtime", createtime);

						}
						Map<String, String> rzmap = new HashMap<String, String>();
						
						String cr_id ="";

						if(rzList.get(0).getCr_id()!=null&&!("").equals(rzList.get(0).getCr_id())){

							cr_id=rzList.get(0).getCr_id();
							
							rzmap.put("cr_id", cr_id);

						}
						String cr_qymc ="";

						if(rzList.get(0).getCr_qymc()!=null&&!("").equals(rzList.get(0).getCr_qymc())){

							cr_qymc=rzList.get(0).getCr_qymc();
							
							rzmap.put("cr_qymc", cr_qymc);

						}
						String cr_nsrsbh ="";

						if(rzList.get(0).getCr_nsrsbh()!=null&&!("").equals(rzList.get(0).getCr_nsrsbh())){

							cr_nsrsbh=rzList.get(0).getCr_nsrsbh();
							
							rzmap.put("cr_nsrsbh", cr_nsrsbh);

						}
						String cr_frsjh ="";

						if(rzList.get(0).getCr_frsjh()!=null&&!("").equals(rzList.get(0).getCr_frsjh())){

							cr_frsjh=rzList.get(0).getCr_frsjh();
							
							rzmap.put("cr_frsjh", cr_frsjh);

						}
						String cr_zjlx ="";

						if(rzList.get(0).getCr_zjlx()!=null&&!("").equals(rzList.get(0).getCr_zjlx())){

							cr_zjlx=rzList.get(0).getCr_zjlx();
							
							rzmap.put("cr_zjlx", cr_zjlx);

						}
						String cr_zjhm ="";

						if(rzList.get(0).getCr_zjhm()!=null&&!("").equals(rzList.get(0).getCr_zjhm())){

							cr_zjhm=rzList.get(0).getCr_zjhm();
							
							rzmap.put("cr_zjhm", cr_zjhm);

						}
						String cr_sqsj ="";

						if(rzList.get(0).getCr_sqsj()!=null&&!("").equals(rzList.get(0).getCr_sqsj())){

							cr_sqsj=rzList.get(0).getCr_sqsj();
							
							rzmap.put("cr_sqsj", cr_sqsj);

						}
						String cr_rzsj ="";

						if(rzList.get(0).getCr_rzsj()!=null&&!("").equals(rzList.get(0).getCr_rzsj())){

							cr_rzsj=rzList.get(0).getCr_rzsj();
							
							rzmap.put("cr_rzsj", cr_rzsj);

						}
						String cr_shjg ="";

						if(rzList.get(0).getCr_shjg()!=null&&!("").equals(rzList.get(0).getCr_shjg())){

							cr_shjg=rzList.get(0).getCr_shjg();
							
							rzmap.put("cr_shjg", cr_shjg);

						}
						String cr_dbqk ="";

						if(rzList.get(0).getCr_dbqk()!=null&&!("").equals(rzList.get(0).getCr_dbqk())){

							cr_dbqk=rzList.get(0).getCr_dbqk();
							
							rzmap.put("cr_dbqk", cr_dbqk);

						}
						String creatorid ="";

						if(rzList.get(0).getCreatorid()!=null&&!("").equals(rzList.get(0).getCreatorid())){

							creatorid=rzList.get(0).getCreatorid();
							
							rzmap.put("creatorid", creatorid);

						}
						String cr_sqdid ="";

						if(rzList.get(0).getCr_sqdid()!=null&&!("").equals(rzList.get(0).getCr_sqdid())){

							cr_sqdid=rzList.get(0).getCr_sqdid();
									
							rzmap.put("cr_sqdid", cr_sqdid);

						}
						respMap.put("resultcode", "00");
						respMap.put("resultmsg", "成功！");
						respMap.put("nsrsbh", nsrsbh);
						respMap.put("bh",bh);
						respMap.putAll(rzmap);
						respMap.putAll(sqmap);
						respString= JSON.toJSONString(respMap);	
					}
				} catch (Exception e) {
		            e.printStackTrace();
					logger.info("&二层查询所有授权认证信息接口失败&");
					InterfaceLogEntity entity=new InterfaceLogEntity();
					entity.setIl_id(IDGenerate.getZJID("interface"));
					entity.setBh(bh);
					entity.setNsrsbh(nsrsbh);
					entity.setInterfaceName("querySqxxGxtwo");
					entity.setErrorLog("&二层查询所有授权认证信息接口失败&");
					entity.setContent(content);
				    interfaceLogMapper.insert(entity);
					if (bh.equals("")) {
						respMap.put("bh","");
						respMap.put("resultcode", "09");
						respMap.put("resultmsg", "接口调用失败");
						respString= JSON.toJSONString(respMap);
					}else {
						respMap.put("bh", bh);
						respMap.put("resultcode", "09");
						respMap.put("resultmsg", "系统错误");
						respString= JSON.toJSONString(respMap);
					}
					e.printStackTrace();
				}finally{
					try
					{
						if (null != reader){
							reader.close();
						}
					} catch (Exception e){
						e.printStackTrace();
					}
				}
				response.setContentType("text/xml");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = null;
				try {
					out = response.getWriter();
					out.print(respString);
				} catch (IOException e) {
					logger.info("======================二层返回预览结果报错=====================");
					e.printStackTrace();
				}finally{
					try
					{
						if (null != out)
						{
							out.close();
						}
					} catch (Exception e){
						e.printStackTrace();
					}
				}
				logger.info("/querySqxxGxtwo*******************************end^*^");
			}
		
		//根据认证授权结果修改认证记录授权记录状态
			@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
			@RequestMapping(value = "/resultRzjgGx", method = RequestMethod.POST)
			protected void resultRzjgGx(HttpServletRequest request,HttpServletResponse response){
				logger.info("/resultRzjgGx*******************************begin^*^");
				BufferedReader reader = null;

				StringBuilder sb = new StringBuilder();

				Map<String, String> respMap = new HashMap<String, String>();

				//请求的内容
				String content = "";

				String bh = "";

				String respString = "";
				
				String nsrsbh ="";
				

				try {

					reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));

					String line = null;

					while ((line = reader.readLine()) != null) {   
						
						sb.append(line);   
					}  

					content = sb.toString();
					System.out.println("++++++++++++++content++++++++++++++++++++="+content);
					//如果请求json不为空
					if (!content.equals("")) {

						System.out.println("=========resultRzjgGx请求参数========="+content);

						Map<String, Object> requestJsonmap = JSON.parseObject(content);
						
						if(requestJsonmap.get("nsrsbh")!=null){

							nsrsbh=requestJsonmap.get("nsrsbh").toString();	

						}
						

						if (requestJsonmap.get("bh")!=null) {
							
							bh = requestJsonmap.get("bh").toString();
						}
						String rzjg ="";

						if(requestJsonmap.get("rzjg")!=null){

							rzjg=requestJsonmap.get("rzjg").toString();

						}
						String rzMessage ="";

						if(requestJsonmap.get("rzMessage")!=null){

							rzMessage=requestJsonmap.get("rzMessage").toString();

						}
						String sqjg ="";

						if(requestJsonmap.get("sqjg")!=null){

							sqjg=requestJsonmap.get("sqjg").toString();

						}
						String sqMessage ="";

						if(requestJsonmap.get("sqMessage")!=null){

							sqMessage=requestJsonmap.get("sqMessage").toString();

						}
						String grantCode = "";
						if(requestJsonmap.get("grantCode")!=null){
							grantCode=requestJsonmap.get("grantCode").toString();
						}
						
						String authType = "";
						if(requestJsonmap.get("authType")!=null){
							authType=requestJsonmap.get("authType").toString();
						}
						
						Map<String, Object> parameter=new HashMap<String, Object>();					
						parameter.put("at_nsrsbh", nsrsbh);
						parameter.put("at_sqdid", bh);
						System.out.println("++++++++++++++nsrsbh++++++++++++++++++++="+nsrsbh);
						System.out.println("++++++++++++++bh++++++++++++++++++++="+bh);
						List<AuthorizationrecordEntity> list = authorizationrecordService.querySqByPage(parameter);
						System.out.println(".................list.........................."+list.size());
						if(list.size()>0){
							System.out.println(".................list............start.............");
							Map<String, Object> rzMap=new HashMap<String, Object>();
							if(!("").equals(rzjg)){
								if(("Y").equals(rzjg)){
									rzMap.put("cr_shjg","rzjg001");
								}else {
									rzMap.put("cr_shjg","rzjg002");
								}
							}
							if(!("").equals(rzMessage)){
								rzMap.put("cr_dbqk",rzMessage);
							}
							rzMap.put("cr_sqdid",bh);
							System.out.println(".................updateRZxx............start.............");
							int rzResult=enterprisestatisticsService.updateRZxx(rzMap);
							System.out.println(".................updateRZxx............end.......rzResult......"+rzResult);
							Map<String, Object> sqMap=new HashMap<String, Object>();
							if(!("").equals(sqjg)){
								if(("Y").equals(sqjg)){
									sqMap.put("sqzt","sqjg001");
								}else {
									sqMap.put("sqzt","sqjg002");
								}
							}
							if(!("").equals(sqMessage)){
								sqMap.put("sqjg",sqMessage);
							}
							sqMap.put("at_sqdid",bh);
							sqMap.put("grantCode",grantCode);
							sqMap.put("authType",authType);
							/*Date today = new Date();
							String at_sqkssj = DateFormatter.dateToStr(today, "yyyy-MM-dd");
							String at_sqjssj = DateFormatter.dateToStr(DateFormatter.timeAddDay(today,7), "yyyy-MM-dd");
							sqMap.put("atSqkssj",at_sqkssj);
							sqMap.put("atSqjssj",at_sqjssj);*/
							System.out.println(".................updateSQxx............start.............");
							int sqResult=authorizationrecordService.updateSQxx(sqMap);
							System.out.println(".................updateSQxx............end.......sqResult......"+sqResult);
							respMap.put("resultcode", "00");
							respMap.put("resultmsg","成功");
							SendHttpPostRequest sp = new  SendHttpPostRequest();
							//String urls = "http://127.0.0.1:8100/tbp/resultRzjgGxtwo";
							String urls = parametersService.QueryValueByCode("resultRzjgGxtwo");
							System.out.println(".................urls........................."+urls);
							String requestJson= sp.sendPost(urls,content);
							System.out.println(".................requestJson........................."+requestJson);
							System.out.println("tworequst====="+requestJson);
                            respString= JSON.toJSONString(respMap);
						}else{
							respMap.put("resultcode", "02");
							respMap.put("resultmsg","无数据");
							respString= JSON.toJSONString(respMap);
						}
						
						
					}
				} catch (Exception e) {

					logger.info("===================== 修改认证授权结果接口失败 =========================");

					if (bh.equals("")) {
						respMap.put("resultcode","09");
						respMap.put("resultmsg","接口调用失败");
						respString= JSON.toJSONString(respMap);
					}else {
						respMap.put("bh", bh);
						respMap.put("resultcode", "09");
						respMap.put("resultmsg", "系统错误");
						respString= JSON.toJSONString(respMap);
					}
					e.printStackTrace();
				}finally{
					try
					{
						if (null != reader){
							reader.close();
						}
					} catch (Exception e){
						e.printStackTrace();
					}
				}
				response.setContentType("text/xml");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = null;
				try {
					out = response.getWriter();
					out.print(respString);
				} catch (IOException e) {
					logger.info("======================修改认证授权结果接口失败 =====================");
					e.printStackTrace();
				}finally{
					try
					{
						if (null != out)
						{
							out.close();
						}
					} catch (Exception e){
						e.printStackTrace();
					}
				}
				logger.info("/resultRzjgGx*******************************end^*^");
			}
			
		//修改认证授权状态
			@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
			@RequestMapping(value = "/resultRzjgGxtwo", method = RequestMethod.POST)
			protected void resultRzjgGxtwo(HttpServletRequest request,HttpServletResponse response){
				
				System.out.println("*********税局外联网区开始更新 认证授权结果开始***********resultRzjgGxtwo**************");
				BufferedReader reader = null;

				StringBuilder sb = new StringBuilder();

				Map<String, String> respMap = new HashMap<String, String>();

				//请求的内容

				String bh = "";
				
				String nsrsbh ="";
				
				String content = "";

				String respString = "";

				try {

					reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));

					String line = null;

					while ((line = reader.readLine()) != null) {   
						sb.append(line);   
					}  

					content = sb.toString();

					//
					System.out.println("+++++++++resultRzjgGxtwo  +++++ Start ++++=");
					System.out.println("+++++++++content  +++++++++="+content);
					//如果请求json不为空
					if (!content.equals("")) {

						System.out.println("=========resultRzjgGxtwo请求参数========="+content);

						Map<String, Object> requestJsonmap = JSON.parseObject(content);

						if(requestJsonmap.get("nsrsbh")!=null){

							nsrsbh=requestJsonmap.get("nsrsbh").toString();	
							System.out.println("+++++++++resultRzjgGxtwo  +++++ nsrsbh ++++="+nsrsbh);

						}
						

						if (requestJsonmap.get("bh")!=null) {
							
							bh = requestJsonmap.get("bh").toString();
							System.out.println("+++++++++resultRzjgGxtwo  +++++ bh ++++="+bh);
						}
						
						String rzjg ="";

						if(requestJsonmap.get("rzjg")!=null){

							rzjg=requestJsonmap.get("rzjg").toString();

						}
						String rzMessage ="";

						if(requestJsonmap.get("rzMessage")!=null){

							rzMessage=requestJsonmap.get("rzMessage").toString();

						}
						String sqjg ="";

						if(requestJsonmap.get("sqjg")!=null){

							sqjg=requestJsonmap.get("sqjg").toString();
							System.out.println("+++++++++resultRzjgGxtwo  +++++ sqjg ++++="+sqjg);

						}
						String sqMessage ="";

						if(requestJsonmap.get("sqMessage")!=null){

							sqMessage=requestJsonmap.get("sqMessage").toString();
							System.out.println("+++++++++resultRzjgGxtwo  +++++ sqMessage ++++="+sqMessage);

						}
						
						String grantCode ="";
						if(requestJsonmap.get("grantCode")!=null){

							grantCode=requestJsonmap.get("grantCode").toString();
							System.out.println("+++++++++resultRzjgGxtwo  +++++ grantCode ++++="+grantCode);

						}
						
						String authType ="";
						if(requestJsonmap.get("authType")!=null){

							authType=requestJsonmap.get("authType").toString();
							System.out.println("+++++++++resultRzjgGxtwo  +++++ authType ++++="+authType);

						}
						
						
						Map<String, Object> parameter=new HashMap<String, Object>();
						parameter.put("at_nsrsbh", nsrsbh);
						parameter.put("at_sqdid", bh);
						List<AuthorizationrecordEntity> list = authorizationrecordService.querySqByPage(parameter);
						System.out.println("++++++++++++++nsrsbh++++++++++++++++++++="+nsrsbh);
						System.out.println("++++++++++++++bh++++++++++++++++++++="+bh);
						System.out.println("++++++++++++++list two++++++++++++++++++++="+list.size());
						if(list.size()>0){
							
							Map<String, Object> rzMap=new HashMap<String, Object>();
							if(!("").equals(rzjg)){
								if(("Y").equals(rzjg)){
									rzMap.put("cr_shjg","rzjg001");
								}else {
									rzMap.put("cr_shjg","rzjg002");
								}
							}
							if(!("").equals(rzMessage)){
								rzMap.put("cr_dbqk",rzMessage);
							}
							rzMap.put("cr_sqdid",bh);
							System.out.println("++++++++++++++updateRZxx two++++++++++++++++++++="+bh);
							int rzResult=enterprisestatisticsService.updateRZxx(rzMap);
							System.out.println("++++++++++++++updateRZxx two++++++++++++++end +++++rzResult+="+rzResult);
							Map<String, Object> sqMap=new HashMap<String, Object>();
							if(!("").equals(sqjg)){
								if(("Y").equals(sqjg)){
									sqMap.put("sqzt","sqjg001");
								}else {
									sqMap.put("sqzt","sqjg002");
								}
							}
							if(!("").equals(sqMessage)){
								sqMap.put("sqjg",sqMessage);
							}
							sqMap.put("at_sqdid",bh);
							
							if(!("").equals(grantCode)){
								sqMap.put("grantCode",grantCode);
							}
							
							if(!("").equals(authType)){
								sqMap.put("authType",authType);
							}
							/*Date today = new Date();
							String at_sqkssj = DateFormatter.dateToStr(today, "yyyy-MM-dd");
							String at_sqjssj = DateFormatter.dateToStr(DateFormatter.timeAddDay(today,7), "yyyy-MM-dd");
							sqMap.put("atSqkssj",at_sqkssj);
							sqMap.put("atSqjssj",at_sqjssj);*/
							System.out.println("++++++++++++++updateSQxx two++++++++++++++++++++="+bh);
							int sqResult=authorizationrecordService.updateSQxx(sqMap);
							System.out.println("++++++++++++++updateSQxx two++++++++++++++end +++++sqResult+="+sqResult);
							respMap.put("resultcode", "00");
							respMap.put("resultmsg","成功");
							respString= JSON.toJSONString(respMap);
						}else{
							respMap.put("resultcode", "02");
							respMap.put("resultmsg","无数据");
							respString= JSON.toJSONString(respMap);
						}
					}
				} catch (Exception e) {
					logger.info("===================== 二层修改认证授权结果失败 =========================");
					if (bh.equals("")) {
						respMap.put("resultcode","09");
						respMap.put("resultmsg","接口调用失败");
						respString= JSON.toJSONString(respMap);
					}else {
						respMap.put("bh", bh);
						respMap.put("resultcode", "09");
						respMap.put("resultmsg", "系统错误");
						respString= JSON.toJSONString(respMap);
					}
					e.printStackTrace();
				}finally{
					try
					{
						if (null != reader){
							reader.close();
						}
					} catch (Exception e){

						e.printStackTrace();

					}
				}
				response.setContentType("text/xml");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = null;
				try {
					out = response.getWriter();
					out.print(respString);
				} catch (IOException e) {
					logger.info("======================二层修改认证授权结果失败=====================");
					e.printStackTrace();

				}finally{
					try
					{
						if (null != out)
						{
							out.close();
						}
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}
			
		//插入认证授权记录
		@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
		@RequestMapping(value = "/saveRzSqjgGx", method = RequestMethod.POST)
		protected void saveRzSqjgGx(HttpServletRequest request,HttpServletResponse response){
			logger.info("-------------开始调用saveRzSqjgGx---------------");
				BufferedReader reader = null;

				StringBuilder sb = new StringBuilder();

				Map<String, String> respMap = new HashMap<String, String>();

				//请求的内容
				String bh = "";
				
				String nsrsbh ="";

				String content = "";

				String respString = "";

				try {

					reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));

					String line = null;

					while ((line = reader.readLine()) != null) {   
						sb.append(line);   
					}  

					content = sb.toString();
					//logger.info("-------------调用saveRzSqjgGx的参数---------------"+content);
					//如果请求json不为空
					if (!content.equals("")) {

						Map<String, Object> requestJsonmap = JSON.parseObject(content);
						
						if(requestJsonmap.get("nsrsbh")!=null){

							nsrsbh=requestJsonmap.get("nsrsbh").toString();	

						}
						
						if (requestJsonmap.get("bh")!=null) {
							
							bh = requestJsonmap.get("bh").toString();
						}
						
						Map<String, Object> sqparameter=new HashMap<String, Object>();
						sqparameter.put("at_nsrsbh", nsrsbh);
						sqparameter.put("at_sqdid", bh);
						Map<String, Object> rzparameter=new HashMap<String, Object>();
						rzparameter.put("cr_nsrsbh", nsrsbh);
						rzparameter.put("cr_sqdid", bh);
						List<AuthorizationrecordEntity> Sqlist = authorizationrecordService.querySqByPage(sqparameter);
						List<EnterpriseInfoEntity> rzList=enterprisestatisticsService.queryRzByPage(rzparameter);
						int rz=0;
						int sq=0;
						if(Sqlist.size()>0){
							sq=-1;
							respMap.put("resultsqmsg","授权记录已存在");
							respString= JSON.toJSONString(respMap);
						}else{
							//授权入库
							Map<String, Object> sqmap = new HashMap<String, Object>();
							String at_id  = IDGenerate.getZJID("XH");

							//if(requestJsonmap.get("at_id")!=null){

							//	at_id=requestJsonmap.get("at_id").toString();
								
								sqmap.put("at_id", at_id);

							//}
							
							String at_qymc ="";

							if(requestJsonmap.get("at_qymc")!=null){

								at_qymc=requestJsonmap.get("at_qymc").toString();
								
								sqmap.put("at_qymc", at_qymc);

							}
							String at_nsrsbh ="";

							if(requestJsonmap.get("at_nsrsbh")!=null){

								at_nsrsbh=requestJsonmap.get("at_nsrsbh").toString();
								
								sqmap.put("at_nsrsbh", at_nsrsbh);

							}
							Date today = new Date();
							String at_sqsj = DateFormatter.dateToStr(today, "yyyy-MM-dd");;
							sqmap.put("at_sqsj", at_sqsj);
							/*if(requestJsonmap.get("at_sqsj")!=null){

								at_sqsj=requestJsonmap.get("at_sqsj").toString();

								sqmap.put("at_sqsj", at_sqsj);

							}*/
							
							String at_sqkssj = DateFormatter.dateToStr(today, "yyyy-MM-dd");
							sqmap.put("at_sqkssj", at_sqkssj);
							/*if(requestJsonmap.get("at_sqkssj")!=null){

								at_sqkssj=requestJsonmap.get("at_sqkssj").toString();
								
								sqmap.put("at_sqkssj", at_sqkssj);

							}*/
							String at_sqjssj = DateFormatter.dateToStr(DateFormatter.timeAddDay(today,7), "yyyy-MM-dd");
							sqmap.put("at_sqjssj", at_sqjssj);

							/*if(requestJsonmap.get("at_sqjssj")!=null){

								at_sqjssj=requestJsonmap.get("at_sqjssj").toString();
								
								sqmap.put("at_sqjssj", at_sqjssj);

							}*/
							String at_sqsjqx ="";

							if(requestJsonmap.get("at_sqsjqx")!=null){

								at_sqsjqx=requestJsonmap.get("at_sqsjqx").toString();
								
								sqmap.put("at_sqsjqx", at_sqsjqx);

							}
							String at_sqzt ="";

							if(requestJsonmap.get("at_sqzt")!=null){

								at_sqzt=requestJsonmap.get("at_sqzt").toString();
								
								sqmap.put("at_sqzt", at_sqzt);

							}
							String at_sqyy ="";

							if(requestJsonmap.get("at_sqyy")!=null){

								at_sqyy=requestJsonmap.get("at_sqyy").toString();
								
								sqmap.put("at_sqyy", at_sqyy);

							}
							String sqzt ="";

							if(requestJsonmap.get("sqzt")!=null){

								sqzt=requestJsonmap.get("sqzt").toString();
								
								sqmap.put("sqzt", sqzt);

							}
							String sqjg ="";

							if(requestJsonmap.get("sqjg")!=null){

								sqjg=requestJsonmap.get("sqjg").toString();
								
								sqmap.put("sqjg", sqjg);

							}
							String at_frxm ="";

							if(requestJsonmap.get("at_frxm")!=null){

								at_frxm=requestJsonmap.get("at_frxm").toString();
								
								sqmap.put("at_frxm", at_frxm);

							}
							String at_frsfz ="";

							if(requestJsonmap.get("at_frsfz")!=null){

								at_frsfz=requestJsonmap.get("at_frsfz").toString();
								
								sqmap.put("at_frsfz", at_frsfz);

							}
							String at_sjmc = "";

							if(requestJsonmap.get("at_sjmc")!=null){

								at_sjmc=requestJsonmap.get("at_sjmc").toString();
								
								sqmap.put("at_sjmc", at_sjmc);

							}
							String org_id ="";

							if(requestJsonmap.get("org_id")!=null){

								org_id=requestJsonmap.get("org_id").toString();
								
								sqmap.put("org_id", org_id);

							}
							
							if(requestJsonmap.get("bankName")!=null){

								org_id=requestJsonmap.get("bankName").toString();
								
								sqmap.put("org_id", org_id);

							}
							
							String fp_id ="";

							if(requestJsonmap.get("fp_id")!=null){

								fp_id=requestJsonmap.get("fp_id").toString();
								
								sqmap.put("fp_id", fp_id);

							}
							
							if(requestJsonmap.get("cpName")!=null){

								fp_id=requestJsonmap.get("cpName").toString();
								
								sqmap.put("fp_id", fp_id);

							}
							
							String ag_id ="";

							if(requestJsonmap.get("ag_id")!=null){

								ag_id=requestJsonmap.get("ag_id").toString();
								
								sqmap.put("ag_id", ag_id);

							}
							String at_sqsyxq ="";

							if(requestJsonmap.get("at_sqsyxq")!=null){

								at_sqsyxq=requestJsonmap.get("at_sqsyxq").toString();
								
								sqmap.put("at_sqsyxq", at_sqsyxq);

							}
							String at_sqdid ="";

							if(requestJsonmap.get("at_sqdid")!=null){

								at_sqdid=requestJsonmap.get("at_sqdid").toString();
								
								sqmap.put("at_sqdid", at_sqdid);

							}
							String la_id ="";

							if(requestJsonmap.get("la_id")!=null){

								la_id=requestJsonmap.get("la_id").toString();
								
								sqmap.put("la_id", la_id);

							}
							
							String grantcode ="";

							if(requestJsonmap.get("grantcode")!=null){

								grantcode=requestJsonmap.get("grantcode").toString();
								
								sqmap.put("grantcode", grantcode);

							}
							
							String authtype ="";

							if(requestJsonmap.get("authtype")!=null){

								authtype=requestJsonmap.get("authtype").toString();
								
								sqmap.put("authtype", authtype);

							}
							
							String createtime ="";

							if(requestJsonmap.get("createtime")!=null){
                                
								createtime=requestJsonmap.get("createtime").toString();
								
								sqmap.put("createtime", createtime);

							}
							
							String at_dkqx ="";

							if(requestJsonmap.get("at_dkqx")!=null){

								at_dkqx=requestJsonmap.get("at_dkqx").toString();
								
								sqmap.put("at_dkqx", at_dkqx);

							}
			 
							
							//插入授权表
							try {
								authorizationrecordService.insert("tb_authorization_record", sqmap);
								sq=1;
								logger.info("===============插入授权记录表成功======================");
							} catch (Exception e) {
								e.printStackTrace();
								logger.info("===============插入授权记录表失败======================");
								InterfaceLogEntity entity=new InterfaceLogEntity();
								entity.setIl_id(IDGenerate.getZJID("interface"));
								entity.setBh(bh);
								entity.setNsrsbh(nsrsbh);
								entity.setInterfaceName("saveRzSqjgGx");
								entity.setErrorLog("&插入授权记录表失败&");
								entity.setContent(content);
							    interfaceLogMapper.insert(entity);
							}
							
						}
						if(rzList.size()>0){
							rz=-1;
							respMap.put("resultrzmsg","认证记录已存在");
							respString= JSON.toJSONString(respMap);
						}else{
							//认证入库
							Map<String, Object> rzmap = new HashMap<String, Object>();
							String cr_id =  IDGenerate.getZJID("RZ");

							//if(requestJsonmap.get("cr_id")!=null){

								//cr_id=requestJsonmap.get("cr_id").toString();
								
								rzmap.put("cr_id", cr_id);

							//}
							String cr_qymc ="";

							if(requestJsonmap.get("cr_qymc")!=null){

								cr_qymc=requestJsonmap.get("cr_qymc").toString();
								
								rzmap.put("cr_qymc", cr_qymc);

							}
							String cr_nsrsbh ="";

							if(requestJsonmap.get("cr_nsrsbh")!=null){

								cr_nsrsbh=requestJsonmap.get("cr_nsrsbh").toString();
								
								rzmap.put("cr_nsrsbh", cr_nsrsbh);

							}
							String cr_frsjh ="";

							if(requestJsonmap.get("cr_frsjh")!=null){

								cr_frsjh=requestJsonmap.get("cr_frsjh").toString();
								
								rzmap.put("cr_frsjh", cr_frsjh);

							}
							String cr_zjlx ="";

							if(requestJsonmap.get("cr_zjlx")!=null){

								cr_zjlx=requestJsonmap.get("cr_zjlx").toString();
								
								rzmap.put("cr_zjlx", cr_zjlx);

							}
							String cr_zjhm ="";

							if(requestJsonmap.get("cr_zjhm")!=null){

								cr_zjhm=requestJsonmap.get("cr_zjhm").toString();
								
								rzmap.put("cr_zjhm", cr_zjhm);

							}
							String cr_sqsj ="";

							if(requestJsonmap.get("cr_sqsj")!=null){

								cr_sqsj=requestJsonmap.get("cr_sqsj").toString();
								
								rzmap.put("cr_sqsj", cr_sqsj);

							}
							String cr_rzsj ="";

							if(requestJsonmap.get("cr_rzsj")!=null){

								cr_rzsj=requestJsonmap.get("cr_rzsj").toString();
								
								rzmap.put("cr_rzsj", cr_rzsj);

							}
							String cr_shjg ="";

							if(requestJsonmap.get("cr_shjg")!=null){

								cr_shjg=requestJsonmap.get("cr_shjg").toString();
								
								rzmap.put("cr_shjg", cr_shjg);

							}
							String cr_dbqk ="";

							if(requestJsonmap.get("cr_dbqk")!=null){

								cr_dbqk=requestJsonmap.get("cr_dbqk").toString();
								
								rzmap.put("cr_dbqk", cr_dbqk);

							}
							String creatorid ="";

							if(requestJsonmap.get("creatorid")!=null){

								creatorid=requestJsonmap.get("creatorid").toString();
								
								rzmap.put("creatorid", creatorid);

							}
							
							String grantcode ="";

							if(requestJsonmap.get("grantcode")!=null){

								grantcode=requestJsonmap.get("grantcode").toString();
								
								rzmap.put("grantcode", grantcode);

							}
							
							String authtype ="";

							if(requestJsonmap.get("authtype")!=null){

								authtype=requestJsonmap.get("authtype").toString();
								
								rzmap.put("authtype", authtype);

							}
							String cr_sqdid ="";

							if(requestJsonmap.get("cr_sqdid")!=null){

								cr_sqdid=requestJsonmap.get("cr_sqdid").toString();
								
								rzmap.put("cr_sqdid", cr_sqdid);

							}
							//插入认证表
							try {
								authorizationrecordService.insert("tb_check_record", rzmap);
								rz=1;
								logger.info("===============插入认证记录表成功======================");
							} catch (Exception e) {
								e.printStackTrace();
								logger.info("===============插入认证记录表失败======================");
								InterfaceLogEntity entity=new InterfaceLogEntity();
								entity.setIl_id(IDGenerate.getZJID("interface"));
								entity.setBh(bh);
								entity.setNsrsbh(nsrsbh);
								entity.setInterfaceName("saveRzSqjgGx");
								entity.setErrorLog("&插入认证记录表失败&");
								entity.setContent(content);
							    interfaceLogMapper.insert(entity);
							}
							
						}
						if(rz==1&&sq==1){
							respMap.put("resultcode","00");
							respMap.put("resultmsg","成功");
						}else if(rz==1&&sq==0){
							respMap.put("resultcode","00");
							respMap.put("resultmsg","认证记录插入成功，授权记录插入异常");
						}else if(rz==1&&sq==-1){
							respMap.put("resultcode","00");
							respMap.put("resultmsg","认证记录插入成功，授权记录已存在");
						}else if(rz==0&&sq==1){
							respMap.put("resultcode","00");
							respMap.put("resultmsg","认证记录插入异常，授权记录插入成功");
						}else if(rz==0&&sq==-1){
							respMap.put("resultcode","00");
							respMap.put("resultmsg","认证记录插入异常，授权记录已存在");
						}else if(rz==-1&&sq==-1){
							respMap.put("resultcode","02");
							respMap.put("resultmsg","认证记录已存在，授权记录已存在");
						}
						respString= JSON.toJSONString(respMap);
					}
				} catch (Exception e) {
					logger.info("===================== 第三方调用授权结果接口失败 =========================");
					if (bh.equals("")) {
						respMap.put("resultcode","09");
						respMap.put("resultmsg","接口调用失败");
						respString= JSON.toJSONString(respMap);
					}else {
						respMap.put("bh", bh);
						respMap.put("resultcode", "09");
						respMap.put("resultmsg", "系统错误");
						respString= JSON.toJSONString(respMap);
					}	
					e.printStackTrace();
				}finally{
					try
					{
						if (null != reader){
							reader.close();
						}
					} catch (Exception e){

						e.printStackTrace();

					}
				}
				logger.info("-------------开始返回saveRzSqjgGx---------------");
				response.setContentType("text/xml");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = null;
				try {
					out = response.getWriter();
					out.print(respString);
				} catch (IOException e) {
					logger.info("======================返回报错=====================");
					e.printStackTrace();
				}finally{
					try
					{
						if (null != out)
						{
							out.close();
						}
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		
		
		//插入认证授权记录
		@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
		@RequestMapping(value = "/saveRzSqjgGxtwo", method = RequestMethod.POST)
		protected void saveRzSqjgGxtwo(HttpServletRequest request,HttpServletResponse response){
				BufferedReader reader = null;

				StringBuilder sb = new StringBuilder();

				Map<String, String> respMap = new HashMap<String, String>();

				//请求的内容
				String bh = "";
				
				String nsrsbh ="";
				
				String content = "";

				String respString = "";
                
				
				try {

					reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));

					String line = null;

					while ((line = reader.readLine()) != null) {   
						sb.append(line);   
					}  

					content = sb.toString();

					//如果请求json不为空
					if (!content.equals("")) {

						System.out.println("=========请求参数========="+content);

						Map<String, Object> requestJsonmap = JSON.parseObject(content);

						if(requestJsonmap.get("nsrsbh")!=null){

							nsrsbh=requestJsonmap.get("nsrsbh").toString();	

						}

						if (requestJsonmap.get("bh")!=null) {
							
							bh = requestJsonmap.get("bh").toString();
						}
						
						Map<String, Object> sqparameter=new HashMap<String, Object>();
						sqparameter.put("at_nsrsbh", nsrsbh);
						sqparameter.put("at_sqdid", bh);
						Map<String, Object> rzparameter=new HashMap<String, Object>();
						rzparameter.put("cr_nsrsbh", nsrsbh);
						rzparameter.put("cr_sqdid", bh);
						List<AuthorizationrecordEntity> Sqlist = authorizationrecordService.querySqByPage(sqparameter);
						List<EnterpriseInfoEntity> rzList=enterprisestatisticsService.queryRzByPage(rzparameter);
						int rz=0;
						int sq=0;
						if(Sqlist.size()>0){
							sq=-1;
							respMap.put("resultsqmsg","授权记录已存在");
							respString= JSON.toJSONString(respMap);
						}else{
							//授权入库
							Map<String, Object> sqmap = new HashMap<String, Object>();
							
							String at_id ="";

							if(requestJsonmap.get("at_id")!=null){

								at_id=requestJsonmap.get("at_id").toString();
								
								sqmap.put("at_id", at_id);

							}
							String at_sqsj ="";

							if(requestJsonmap.get("at_sqsj")!=null){

								at_sqsj=requestJsonmap.get("at_sqsj").toString();

								sqmap.put("at_sqsj", at_sqsj);

							}
							String at_qymc ="";

							if(requestJsonmap.get("at_qymc")!=null){

								at_qymc=requestJsonmap.get("at_qymc").toString();
								
								sqmap.put("at_qymc", at_qymc);

							}
							String at_nsrsbh ="";

							if(requestJsonmap.get("at_nsrsbh")!=null){

								at_nsrsbh=requestJsonmap.get("at_nsrsbh").toString();
								
								sqmap.put("at_nsrsbh", at_nsrsbh);

							}
							String at_sqkssj ="";

							if(requestJsonmap.get("at_sqkssj")!=null){

								at_sqkssj=requestJsonmap.get("at_sqkssj").toString();
								
								sqmap.put("at_sqkssj", at_sqkssj);

							}
							String at_sqjssj ="";

							if(requestJsonmap.get("at_sqjssj")!=null){

								at_sqjssj=requestJsonmap.get("at_sqjssj").toString();
								
								sqmap.put("at_sqjssj", at_sqjssj);

							}
							String at_sqsjqx ="";

							if(requestJsonmap.get("at_sqsjqx")!=null){

								at_sqsjqx=requestJsonmap.get("at_sqsjqx").toString();
								
								sqmap.put("at_sqsjqx", at_sqsjqx);

							}
							String at_sqzt ="";

							if(requestJsonmap.get("at_sqzt")!=null){

								at_sqzt=requestJsonmap.get("at_sqzt").toString();
								
								sqmap.put("at_sqzt", at_sqzt);

							}
							String at_sqyy ="";

							if(requestJsonmap.get("at_sqyy")!=null){

								at_sqyy=requestJsonmap.get("at_sqyy").toString();
								
								sqmap.put("at_sqyy", at_sqyy);

							}
							String sqzt ="";

							if(requestJsonmap.get("sqzt")!=null){

								sqzt=requestJsonmap.get("sqzt").toString();
								
								sqmap.put("sqzt", sqzt);

							}
							String sqjg ="";

							if(requestJsonmap.get("sqjg")!=null){

								sqjg=requestJsonmap.get("sqjg").toString();
								
								sqmap.put("sqjg", sqjg);

							}
							String at_frxm ="";

							if(requestJsonmap.get("at_frxm")!=null){

								at_frxm=requestJsonmap.get("at_frxm").toString();
								
								sqmap.put("at_frxm", at_frxm);

							}
							String at_frsfz ="";

							if(requestJsonmap.get("at_frsfz")!=null){

								at_frsfz=requestJsonmap.get("at_frsfz").toString();
								
								sqmap.put("at_frsfz", at_frsfz);

							}
							String at_sjmc ="";

							if(requestJsonmap.get("at_sjmc")!=null){

								at_sjmc=requestJsonmap.get("at_sjmc").toString();
								
								sqmap.put("at_sjmc", at_sjmc);

							}
							String org_id ="";

							if(requestJsonmap.get("org_id")!=null){

								org_id=requestJsonmap.get("org_id").toString();
								
								sqmap.put("org_id", org_id);

							}
							
							String fp_id ="";

							if(requestJsonmap.get("fp_id")!=null){

								fp_id=requestJsonmap.get("fp_id").toString();
								
								sqmap.put("fp_id", fp_id);

							}
							String ag_id ="";

							if(requestJsonmap.get("ag_id")!=null){

								ag_id=requestJsonmap.get("ag_id").toString();
								
								sqmap.put("ag_id", ag_id);

							}
							String at_sqsyxq ="";

							if(requestJsonmap.get("at_sqsyxq")!=null){

								at_sqsyxq=requestJsonmap.get("at_sqsyxq").toString();
								
								sqmap.put("at_sqsyxq", at_sqsyxq);

							}
							String at_sqdid ="";

							if(requestJsonmap.get("at_sqdid")!=null){

								at_sqdid=requestJsonmap.get("at_sqdid").toString();
								
								sqmap.put("at_sqdid", at_sqdid);

							}
							String la_id ="";

							if(requestJsonmap.get("la_id")!=null){

								la_id=requestJsonmap.get("la_id").toString();
								
								sqmap.put("la_id", la_id);

							}
							String createtime ="";

							if(requestJsonmap.get("createtime")!=null){

								createtime=requestJsonmap.get("createtime").toString();
								
								sqmap.put("createtime", createtime);

							}
							
							String grantcode ="";

							if(requestJsonmap.get("grantcode")!=null){

								grantcode=requestJsonmap.get("grantcode").toString();
								
								sqmap.put("grantcode", grantcode);

							}
							
							String authtype ="";

							if(requestJsonmap.get("authtype")!=null){

								authtype=requestJsonmap.get("authtype").toString();
								
								sqmap.put("authtype", authtype);

							}
							
							String at_dkqx ="";

							if(requestJsonmap.get("at_dkqx")!=null){

								at_dkqx=requestJsonmap.get("at_dkqx").toString();
								
								sqmap.put("at_dkqx", at_dkqx);

							}
							//插入授权表
							try {
								authorizationrecordService.insert("tb_authorization_record", sqmap);
								sq=1;
								logger.info("===============插入授权记录表成功======================");
							} catch (Exception e) {
								e.printStackTrace();
								logger.info("===============插入授权记录表失败======================");
								InterfaceLogEntity entity=new InterfaceLogEntity();
								entity.setIl_id(IDGenerate.getZJID("interface"));
								entity.setBh(bh);
								entity.setNsrsbh(nsrsbh);
								entity.setInterfaceName("saveRzSqjgGxtwo");
								entity.setErrorLog("&插入授权记录表失败&");
								entity.setContent(content);
							    interfaceLogMapper.insert(entity);
							}
						}
						if(rzList.size()>0){
							rz=-1;
							respMap.put("resultrzmsg","认证记录已存在");
							respString= JSON.toJSONString(respMap);
						}else{
							//认证入库
							Map<String, Object> rzmap = new HashMap<String, Object>();
							
							String cr_id ="";

							if(requestJsonmap.get("cr_id")!=null){

								cr_id=requestJsonmap.get("cr_id").toString();
								
								rzmap.put("cr_id", cr_id);

							}
							String cr_qymc ="";

							if(requestJsonmap.get("cr_qymc")!=null){

								cr_qymc=requestJsonmap.get("cr_qymc").toString();
								
								rzmap.put("cr_qymc", cr_qymc);

							}
							String cr_nsrsbh ="";

							if(requestJsonmap.get("cr_nsrsbh")!=null){

								cr_nsrsbh=requestJsonmap.get("cr_nsrsbh").toString();
								
								rzmap.put("cr_nsrsbh", cr_nsrsbh);

							}
							String cr_frsjh ="";

							if(requestJsonmap.get("cr_frsjh")!=null){

								cr_frsjh=requestJsonmap.get("cr_frsjh").toString();
								
								rzmap.put("cr_frsjh", cr_frsjh);

							}
							String cr_zjlx ="";

							if(requestJsonmap.get("cr_zjlx")!=null){

								cr_zjlx=requestJsonmap.get("cr_zjlx").toString();
								
								rzmap.put("cr_zjlx", cr_zjlx);

							}
							String cr_zjhm ="";

							if(requestJsonmap.get("cr_zjhm")!=null){

								cr_zjhm=requestJsonmap.get("cr_zjhm").toString();
								
								rzmap.put("cr_zjhm", cr_zjhm);

							}
							String cr_sqsj ="";

							if(requestJsonmap.get("cr_sqsj")!=null){

								cr_sqsj=requestJsonmap.get("cr_sqsj").toString();
								
								rzmap.put("cr_sqsj", cr_sqsj);

							}
							String cr_rzsj ="";

							if(requestJsonmap.get("cr_rzsj")!=null){

								cr_rzsj=requestJsonmap.get("cr_rzsj").toString();
								
								rzmap.put("cr_rzsj", cr_rzsj);

							}
							String cr_shjg ="";

							if(requestJsonmap.get("cr_shjg")!=null){

								cr_shjg=requestJsonmap.get("cr_shjg").toString();
								
								rzmap.put("cr_shjg", cr_shjg);

							}
							String cr_dbqk ="";

							if(requestJsonmap.get("cr_dbqk")!=null){

								cr_dbqk=requestJsonmap.get("cr_dbqk").toString();
								
								rzmap.put("cr_dbqk", cr_dbqk);

							}
							String creatorid ="";

							if(requestJsonmap.get("creatorid")!=null){

								creatorid=requestJsonmap.get("creatorid").toString();
								
								rzmap.put("creatorid", creatorid);

							}
							String cr_sqdid ="";

							if(requestJsonmap.get("cr_sqdid")!=null){

								cr_sqdid=requestJsonmap.get("cr_sqdid").toString();
								
								rzmap.put("cr_sqdid", cr_sqdid);

							}
							
							String grantcode ="";

							if(requestJsonmap.get("grantcode")!=null){

								grantcode=requestJsonmap.get("grantcode").toString();
								
								rzmap.put("grantcode", grantcode);

							}
							
							String authtype ="";

							if(requestJsonmap.get("authtype")!=null){

								authtype=requestJsonmap.get("authtype").toString();
								
								rzmap.put("authtype", authtype);

							}
							//插入认证表
							try {
								authorizationrecordService.insert("tb_check_record", rzmap);
								rz=1;
								logger.info("===============插入认证记录表成功======================");
							} catch (Exception e) {
								e.printStackTrace();
								logger.info("===============插入认证记录表失败======================");
								InterfaceLogEntity entity=new InterfaceLogEntity();
								entity.setIl_id(IDGenerate.getZJID("interface"));
								entity.setBh(bh);
								entity.setNsrsbh(nsrsbh);
								entity.setInterfaceName("saveRzSqjgGxtwo");
								entity.setErrorLog("&插入认证记录表失败&");
								entity.setContent(content);
							    interfaceLogMapper.insert(entity);
							}	
						}
						if(rz==1&&sq==1){
							respMap.put("resultcode","00");
							respMap.put("resultmsg","成功");
						}else if(rz==1&&sq==0){
							respMap.put("resultcode","00");
							respMap.put("resultmsg","认证记录插入成功，授权记录插入异常");
						}else if(rz==1&&sq==-1){
							respMap.put("resultcode","00");
							respMap.put("resultmsg","认证记录插入成功，授权记录已存在");
						}else if(rz==0&&sq==1){
							respMap.put("resultcode","00");
							respMap.put("resultmsg","认证记录插入异常，授权记录插入成功");
						}else if(rz==0&&sq==-1){
							respMap.put("resultcode","00");
							respMap.put("resultmsg","认证记录插入异常，授权记录已存在");
						}else if(rz==-1&&sq==-1){
							respMap.put("resultcode","02");
							respMap.put("resultmsg","认证记录已存在，授权记录已存在");
						}
						respString= JSON.toJSONString(respMap);
					}
				} catch (Exception e) {
                    e.printStackTrace();
					logger.info("===================== 第三方调用授权结果接口失败 =========================");
					if (bh.equals("")) {
						respMap.put("resultcode","09");
						respMap.put("resultmsg","接口调用失败");
						respString= JSON.toJSONString(respMap);
					}else {
						respMap.put("bh", bh);
						respMap.put("resultcode", "09");
						respMap.put("resultmsg", "系统错误");
						respString= JSON.toJSONString(respMap);
					}	

				}finally{
					try
					{
						if (null != reader){
							reader.close();
						}
					} catch (Exception e){

						e.printStackTrace();

					}
				}

				response.setContentType("text/xml");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = null;

				try {
					out = response.getWriter();
					out.print(respString);
				} catch (IOException e) {
					logger.info("======================返回报错=====================");
					e.printStackTrace();
				}finally{
					try
					{
						if (null != out)
						{
							out.close();
						}
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		
			
}
