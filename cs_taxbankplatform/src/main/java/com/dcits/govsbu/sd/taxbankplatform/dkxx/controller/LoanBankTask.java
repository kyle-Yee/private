package com.dcits.govsbu.sd.taxbankplatform.dkxx.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper.DhDhrmxxMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.DhDkjsxx;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.DhSxjgxx;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.ParametersService;
import com.dcits.govsbu.sd.taxbankplatform.util.DateFormatter;
import com.dcits.govsbu.sd.taxbankplatform.util.FTPUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.StringUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.WebToolUtils;


/**
 * 2.6.数据平台将授信结果及贷后数据推送至智能决策平台
 * @author yaofang
 *
 */

public class LoanBankTask {
	private static Logger log = Logger.getLogger(LoanBankTask.class);
	
	@Autowired
	private DhDhrmxxMapper  dhDhrmxxMapper;
	@Autowired
	private ParametersService  parametersService;
	public void hnResolve() throws Exception{
		try {
			String flag = parametersService.QueryValueByCode("getsjcksj");
			
			String ipString = WebToolUtils.getLocalIP();
			log.info("---------------------当前服务器IP为"+ipString+"----------------");
			if (null != flag) {
				flag = flag.trim();
				if (flag.equals(ipString)) {
					log.info("---------------------当前服务器IP为"+ipString+"开启----------------");
					delete();
					savaDhdate();
					
				}else {
					log.info("---------------------当前服务器"+ipString+"定时任务不开启----------------");
				}
			}else {
				log.info("---------------------当前服务器"+ipString+"定时任务不开启----------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("---------------------当前服务器定时任务出现错误----------------");
		}
	}
	@Transactional(rollbackFor={Exception.class})
	public void delete(){
		log.info("删除贷后数据定时任务执行中…");
		try {
			
			Map<String, Object> map1 = new HashMap<String, Object>();
			List<DhSxjgxx> dhSxjgxxs=dhDhrmxxMapper.querysxjgxxListAll(map1);
			
			if (dhSxjgxxs!= null && dhSxjgxxs.size() > 1) {
				
				List<String> list = new ArrayList<String>();
				
				for (DhSxjgxx dhrmxxMap : dhSxjgxxs) {
		            	String valueString = dhrmxxMap.getCjsj();
						list.add(valueString);
		        }
				
				list.remove(list.size()-1);
				
				int delete = dhDhrmxxMapper.deleteSxjgxx(list);
				System.out.println(delete);
			}
			
			
			log.info("删除贷后数据定时任务执行结束…");
		} catch (Exception e) {
			log.info("删除贷后数据定时任务出现错误");
			e.printStackTrace();
		}
	}
	
	public void savaDhdate() throws Exception{
		 log.info("同步贷后数据定时任务执行中…");
	     List<DhDkjsxx> dhDkjsxxList = new ArrayList<DhDkjsxx>();
	     List<DhSxjgxx> dhSxjgxxList = new ArrayList<DhSxjgxx>();
	     Date today = new Date();	
	     //贷款结束信息文件地址
	     String dkjsxxdz = parametersService.QueryValueByCode("dkjsxx");
	     //String dkjsxxdz ="C:\\20170630";
	     //授信结果信息地址位置
	     String sxjgxxdz = parametersService.QueryValueByCode("sxjgxx");
	     //String sxjgxxdz ="C:\\20170630";
	     //贷款结束信息文件名称
	     String dkjsxxwjmc = parametersService.QueryValueByCode("dkjsxxwjmc");
	     //String dkjsxxwjmc="\\ZNJC_LOAN_END_";
	     //授信结果信息文件名称
	     String sxjgxxwjmc = parametersService.QueryValueByCode("sxjgxxwjmc");
	     //String sxjgxxwjmc = "\\ZNJC_LMT_APPLY_";
	     //是否自动生成时间地址
	     String sfzdscsjdz = parametersService.QueryValueByCode("sfzdscsjdz");
	     //String sfzdscsjdz = "N";
	     String todayStr = DateFormatter.timeJsDay(today, "yyyyMMdd",1);
	     //String todayStr ="20170630";
	     String liststr1 = "";
	     String liststr2 = "";
	     String liststrok1 = "";
	     String liststrok2 = "";
	     if("Y".equals(sfzdscsjdz)){
		     liststr1 = dkjsxxdz+todayStr+dkjsxxwjmc+todayStr+".dat";
		     liststr2 = sxjgxxdz+todayStr+sxjgxxwjmc+todayStr+".dat";
		     liststrok1 = dkjsxxdz+todayStr+dkjsxxwjmc+todayStr+".ok";
		     liststrok2 = sxjgxxdz+todayStr+sxjgxxwjmc+todayStr+".ok";
	     }else{
		     liststr1 = dkjsxxdz+dkjsxxwjmc+todayStr+".dat";
		     liststr2 = sxjgxxdz+sxjgxxwjmc+todayStr+".dat";
		     liststrok1 = dkjsxxdz+dkjsxxwjmc+todayStr+".ok";
		     liststrok2 = sxjgxxdz+sxjgxxwjmc+todayStr+".ok";
	     }
	     List<String> list1 = FTPUtil.readFileByBytes(liststr1);
	     List<String> list2 = FTPUtil.readFileByBytes(liststr2);
	     int dhDkjsxxsnum = 0;
	     int dhSxjgxxnum = 0;
		
		 if(null != list2 && list2.size() > 0){

				 for(String dhSxjgxx :list2){
	
				 String[] dhSxjgxxs = dhSxjgxx.split("\\$");
				 for(int i=0;i<dhSxjgxxs.length;i++){
					    if(("000000000000000000000000000000.00000000").equals(dhSxjgxxs[i].trim())){
					    	dhSxjgxxs[i]="0";
					    }
					}
				
				  
				 Map<String, Object> map1 = new HashMap<String, Object>();
				 map1.put("businessid", dhSxjgxxs[0]);
				 List<DhSxjgxx> sxjgxxlist=dhDhrmxxMapper.querysxjgxxListAll(map1);
				 if(sxjgxxlist.size()==0){
					 if(dhSxjgxxs.length == 16 || dhSxjgxxs.length == 13){
						 String Date= StringUtil.strToDateFormat2(dhSxjgxxs[8]);
						 String DATE2="2017-10-10";
						 String ref=StringUtil.refDate(Date, DATE2);
						 if(("ok").equals(ref)){
							 if(!("0").equals(dhSxjgxxs[12].trim())){
								 DhSxjgxx  sxjgxx = new DhSxjgxx();
								 String sxjgxxid=IDGenerate.getZJID("sxjgxx");
								 java.util.Random random = new java.util.Random();
							     int a = random.nextInt(9000) + 1000;
								 sxjgxx.setSxjgxxId(sxjgxxid+a);
								 sxjgxx.setBusinessid(dhSxjgxxs[0]);
								 sxjgxx.setIndname(dhSxjgxxs[1]);
								 sxjgxx.setIndcerttype(dhSxjgxxs[2]) ;
								 sxjgxx.setIndcertid(dhSxjgxxs[3]);
								 sxjgxx.setEntname(dhSxjgxxs[4]);
								 sxjgxx.setEntcreditid(dhSxjgxxs[5]);
								 sxjgxx.setEntregid(dhSxjgxxs[6]);
								 sxjgxx.setEnttaxid(dhSxjgxxs[7]);
								 sxjgxx.setApprovedate(StringUtil.strToDateFormat2(dhSxjgxxs[8]));
								 sxjgxx.setAdmissionresult(dhSxjgxxs[9]);
								 sxjgxx.setContractamt(StringUtil.objToBigSave8w(dhSxjgxxs[10]));
								 sxjgxx.setContractterm(dhSxjgxxs[11]);
								 sxjgxx.setContractrate(StringUtil.objToBigSave8w(dhSxjgxxs[12]));
								 if(dhSxjgxxs.length == 16){
									 sxjgxx.setPaybacktype(dhSxjgxxs[13]);
									 sxjgxx.setHth(dhSxjgxxs[14]);
									 sxjgxx.setHtzt(dhSxjgxxs[15]);
								 }else{
									 sxjgxx.setPaybacktype("0");
									 sxjgxx.setHth("0");
									 sxjgxx.setHtzt("0");
								 }
								 //sxjgxx.setCjsj(today);
								 sxjgxx.setChannelid("GLBANK");
								 dhSxjgxxList.add(sxjgxx);
								}
						 }
							 
					 }else if(dhSxjgxxs.length == 10){
						 String Date= StringUtil.strToDateFormat2(dhSxjgxxs[8]);
						 String DATE2="2017-10-10";
						 String ref=StringUtil.refDate(Date, DATE2);
						 if(("ok").equals(ref)){
						 DhSxjgxx  sxjgxx = new DhSxjgxx();
						 String sxjgxxid=IDGenerate.getZJID("sxjgxx");
						 java.util.Random random = new java.util.Random();
					     int a = random.nextInt(9000) + 1000;
						 sxjgxx.setSxjgxxId(sxjgxxid+a);
						 sxjgxx.setBusinessid(dhSxjgxxs[0]);
						 sxjgxx.setIndname(dhSxjgxxs[1]);
						 sxjgxx.setIndcerttype(dhSxjgxxs[2]);
						 sxjgxx.setIndcertid(dhSxjgxxs[3]);
						 sxjgxx.setEntname(dhSxjgxxs[4]);
						 sxjgxx.setEntcreditid(dhSxjgxxs[5]);
						 sxjgxx.setEntregid(dhSxjgxxs[6]);
						 sxjgxx.setEnttaxid(dhSxjgxxs[7]);
						 sxjgxx.setApprovedate(StringUtil.strToDateFormat2(dhSxjgxxs[8]));
						 sxjgxx.setAdmissionresult(dhSxjgxxs[9]);
						 sxjgxx.setContractamt("0");
						 sxjgxx.setContractterm("0");
						 sxjgxx.setContractrate("0");
					     sxjgxx.setPaybacktype("0");
					     sxjgxx.setHth("0");
						 sxjgxx.setHtzt("0");
						 //sxjgxx.setCjsj(today);
						 sxjgxx.setChannelid("GLBANK");
						 dhSxjgxxList.add(sxjgxx);
						 }
					 }
			
				 }else{
					 log.info(dhSxjgxxs[0]+"已存在记录");
				 }
			 }
			 int y = dhSxjgxxList.size()/1000;
				for(int i = 0 ; i < y ; i++){
					 try {
					dhSxjgxxnum += dhDhrmxxMapper.insertDhsxjgxx(dhSxjgxxList.subList(i*1000,i*1000+1000) );
					 }catch(Exception e){
						 log.info("同步到授信结果信息：第"+i*1000+"至"+i*1000+1000+"出现错误更新失败");  
						 e.printStackTrace();
					 }
				}
				if(dhSxjgxxList.size() - y*1000 > 0){
					try {
					dhSxjgxxnum += dhDhrmxxMapper.insertDhsxjgxx(dhSxjgxxList.subList(y*1000, dhSxjgxxList.size()));
					}catch(Exception e){
						 log.info("同步到授信结果信息：第"+y*1000+"至"+dhSxjgxxList.size()+"出现错误更新失败");  
						 e.printStackTrace();
					 }
				}
			
		 }else{
			 log.info("没有同步到授信结果信息");  
		 }
		
		 
		 if(null != list1 && list1.size() > 0){
			 for(String dhDkjsxxs :list1){
				 String[] dhDkjsxx = dhDkjsxxs.split("\\$");
				 for(int i=0;i<dhDkjsxx.length;i++){
                        if(("000000000000000000000000000000.00000000").equals(dhDkjsxx[i].trim())){
					    	dhDkjsxx[i]="0";
					    }
					}
				
				 if(dhDkjsxx.length == 17){
				     DhDkjsxx  dkjsxx = new DhDkjsxx();
				     String dkjsxxid=IDGenerate.getZJID("dkjsxx");  
				     java.util.Random random = new java.util.Random();
				     int a = random.nextInt(9000) + 1000;
				     dkjsxx.setDkjsxxId(dkjsxxid+a);
				     dkjsxx.setBusinessid(dhDkjsxx[0]);	 
					 dkjsxx.setIndname(dhDkjsxx[1]);
					 dkjsxx.setIndcerttype(dhDkjsxx[2]);
					 dkjsxx.setIndcertid(dhDkjsxx[3]);
					 dkjsxx.setEntname(dhDkjsxx[4]);
					 dkjsxx.setEntcreditid(dhDkjsxx[5]);
					 dkjsxx.setEntregid(dhDkjsxx[6]);
					 dkjsxx.setEnttaxid(dhDkjsxx[7]);
					 dkjsxx.setSerialno(dhDkjsxx[8]);
					 dkjsxx.setEndtype(dhDkjsxx[9]);	 
					 dkjsxx.setEnddate(StringUtil.strToDateFormat2(dhDkjsxx[10]));
					 if(("0").equals(dhDkjsxx[11])){
						 dkjsxx.setEndbalance(dhDkjsxx[11]);
					 }else{
						 dkjsxx.setEndbalance(StringUtil.objToBigSave8w(dhDkjsxx[11]));
					 }
					 if("".equals(dhDkjsxx[12])){
						 dkjsxx.setOverduecount("0");
					 }else{
						 dkjsxx.setOverduecount(dhDkjsxx[12]);
					 }
					 if("".equals(dhDkjsxx[13])){
						 dkjsxx.setMaxoverduedays("0");
					 }else{
						 dkjsxx.setMaxoverduedays(dhDkjsxx[13]);
					 }
					 dkjsxx.setEndclass(dhDkjsxx[14]);
					 dkjsxx.setBh(dhDkjsxx[15]);
					 dkjsxx.setHtzt(dhDkjsxx[16]);
					 dkjsxx.setChannelid("CLBANK");
					// dkjsxx.setCjsj(today);
					 dhDkjsxxList.add(dkjsxx);
				 
				 }else if(dhDkjsxx.length == 12){
					     DhDkjsxx  dkjsxx = new DhDkjsxx();
					     String dkjsxxid=IDGenerate.getZJID("dkjsxx");  
					     java.util.Random random = new java.util.Random();
					     int a = random.nextInt(9000) + 1000;
					     dkjsxx.setDkjsxxId(dkjsxxid+a);
					     dkjsxx.setBusinessid(dhDkjsxx[0]);	 
						 dkjsxx.setIndname(dhDkjsxx[1]);
						 dkjsxx.setIndcerttype(dhDkjsxx[2]);
						 dkjsxx.setIndcertid(dhDkjsxx[3]);
						 dkjsxx.setEntname(dhDkjsxx[4]);
						 dkjsxx.setEntcreditid(dhDkjsxx[5]);
						 dkjsxx.setEntregid(dhDkjsxx[6]);
						 dkjsxx.setEnttaxid(dhDkjsxx[7]);
						 dkjsxx.setSerialno(dhDkjsxx[8]);
						 dkjsxx.setEndtype(dhDkjsxx[9]);
						 dkjsxx.setEnddate(StringUtil.strToDateFormat2(dhDkjsxx[10]));
						 if(("0").equals(dhDkjsxx[11])){
							 dkjsxx.setEndbalance(dhDkjsxx[11]);
						 }else{
							 dkjsxx.setEndbalance(StringUtil.objToBigSave8w(dhDkjsxx[11]));
						 }

						 dkjsxx.setOverduecount("0");
						 dkjsxx.setMaxoverduedays("0");
						 dkjsxx.setEndclass("");
						 dkjsxx.setChannelid("CLBANK");
						// dkjsxx.setCjsj(today);
						 dhDkjsxxList.add(dkjsxx);
					 }
				
			 }
			 int y = dhDkjsxxList.size()/1000;
				for(int i = 0 ; i < y ; i++){
					 try {
					dhDkjsxxsnum += dhDhrmxxMapper.insertDhDkjsxx(dhDkjsxxList.subList(i*1000,i*1000+1000) );
					 }catch(Exception e){
						 log.info("同步到贷款结束信息：第"+i*1000+"至"+i*1000+1000+"出现错误更新失败");
						 e.printStackTrace();
						 }
					 }
				if(dhDkjsxxList.size() - y*1000 > 0){
				  try {
					dhDkjsxxsnum += dhDhrmxxMapper.insertDhDkjsxx(dhDkjsxxList.subList(y*1000, dhDkjsxxList.size()));
				  }catch(Exception e){
					  log.info("同步到贷款结束信息：第"+y*1000+"至"+dhDkjsxxList.size()+"出现错误更新失败");
						 e.printStackTrace();
				  }
		    	 }
		 }else{
			 log.info("没有同步到贷后授信结束信息 ");  
		 }
		 
			if(dhDkjsxxsnum != dhDkjsxxList.size()){
				log.info("同步贷款结束信息失败条数"+(dhDkjsxxList.size() -dhDkjsxxsnum));  
			}
		
			if(dhSxjgxxnum != dhSxjgxxList.size()){
				log.info("同步授信结果信息失败条数"+(dhSxjgxxList.size() -dhSxjgxxnum));  
			}
		 
		log.info("定时任务执行结束…");  
	}
}