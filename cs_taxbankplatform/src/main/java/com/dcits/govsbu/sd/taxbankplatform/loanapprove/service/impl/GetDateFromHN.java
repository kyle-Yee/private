package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.govsbu.sd.taxbankplatform.dataExchange.common.ServiceCommon;
import com.dcits.govsbu.sd.taxbankplatform.datastorage.service.TaxDataSaveService;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.service.InvokeRecodeService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.NsryhxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveService;
import com.dcits.govsbu.sd.taxbankplatform.sendmessage.templet.RandomUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.east.microsilver.common.Commons;
import com.east.microsilver.common.request.Parameter;
import com.east.microsilver.common.request.RequestConfig;
import com.google.gson.Gson;
/**
 * @versions:1.0 
 * @filename：GetDateFromHN.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午3:56:432:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName GetDateFromHN
 */
public class GetDateFromHN {
	private static final Logger logger = LoggerFactory.getLogger(GetDateFromHN.class);
	/**
	 * @Author Zhongyj
	 * @date 2017-4-21 下午3:57:03
	 * @param id
	 * @param taxDataSaveService
	 * @param loanApproveService
	 * @param invokeRecodeService
	 * @return
	 * @throws Exception
	 */
	public String getDateFromHNJK(String id,TaxDataSaveService taxDataSaveService,LoanApproveService loanApproveService,InvokeRecodeService invokeRecodeService) throws Exception {
		logger.info("getDateFromHNJK started !");
		StringBuffer sb = new StringBuffer("success");
		String sqxh = IDGenerate.generateMessageHNID();
		NsryhxxEntity nsryhxxEntity = loanApproveService.findNsryhxx(id);
		//日期比较
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		Date nowDate = new Date();
		Date yxqq = null;
		Date yxqz = null;
		try {
			yxqq = df.parse(nsryhxxEntity.getYxqq());
			yxqz = df.parse(nsryhxxEntity.getYxqz());
		} catch (ParseException e1) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
				sb.append("getDateFromHNJK yxqq 或是  yxqz 转换失败");
			}
			logger.error("getDateFromHNJK yxqq 或是  yxqz 转换失败");
		}
		if (yxqz.getTime() > nowDate.getTime() && nowDate.getTime() > yxqq.getTime()) {
			Calendar now = Calendar.getInstance();
			now.add(Calendar.YEAR,-1);
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			int sj ;
			//获取基础信息数据
			try {
				hnJksjCc(nsryhxxEntity,taxDataSaveService,sqxh, invokeRecodeService);
			} catch (Exception e) {
				if("success".equals(sb.toString())){
					sb=new StringBuffer();
					sb.append("获取湖南 接口中的企业基础数据失败 ！");
				}
				logger.error("jksjCc error !");
			}
			//需要传入三年的时间按年去查询数据
			try {
				for(int j=0;j<3;j++){
					if(j==0){
						sj = now.get(Calendar.YEAR);
						now.add(Calendar.YEAR,-1);
						calendar.set(Calendar.YEAR, sj);
						Date currYearFirst = calendar.getTime();
						String sqsjq = f.format(currYearFirst);
						calendar.roll(Calendar.DAY_OF_YEAR, -1);
						Date currYearLast = calendar.getTime();
						String sqsjz = f.format(currYearLast);
						nsryhxxEntity.setYxqq(sqsjq);
						nsryhxxEntity.setYxqz(sqsjz);
						getNianBaoxx(nsryhxxEntity,taxDataSaveService,sqxh, invokeRecodeService);
						
					}
					if(j==1){
						sj = now.get(Calendar.YEAR);
						now.add(Calendar.YEAR,-1);
						calendar.clear();
						calendar.set(Calendar.YEAR, sj);
						Date currYearFirst = calendar.getTime();
						String sqsjq = f.format(currYearFirst);
						calendar.roll(Calendar.DAY_OF_YEAR, -1);
						Date currYearLast = calendar.getTime();
						String sqsjz = f.format(currYearLast);
						nsryhxxEntity.setYxqq(sqsjq);
						nsryhxxEntity.setYxqz(sqsjz);
						getNianBaoxx(nsryhxxEntity,taxDataSaveService,sqxh, invokeRecodeService);
					}
					if(j==2){
						sj = now.get(Calendar.YEAR);
						calendar.clear();
						calendar.set(Calendar.YEAR, sj);
						Date currYearFirst = calendar.getTime();
						String sqsjq = f.format(currYearFirst);
						calendar.roll(Calendar.DAY_OF_YEAR, -1);
						Date currYearLast = calendar.getTime();
						String sqsjz = f.format(currYearLast);
						nsryhxxEntity.setYxqq(sqsjq);
						nsryhxxEntity.setYxqz(sqsjz);
						getNianBaoxx(nsryhxxEntity,taxDataSaveService,sqxh, invokeRecodeService);
					}
				}
			} catch (Exception e) {
				if("success".equals(sb.toString())){
					sb=new StringBuffer();
					sb.append("获取湖南 接口中的企业利润年报数据失败");
				}
				logger.error("getNianBaoxx error !");
			}
			//需要传入三年的时间按月去查询数据
			//获取前一年的时间
			Calendar qynCalendar = Calendar.getInstance();
			qynCalendar.add(Calendar.YEAR,-1);
			int qn = qynCalendar.get(Calendar.YEAR);
			qynCalendar.clear();
			qynCalendar.set(Calendar.YEAR, qn);
			qynCalendar.roll(Calendar.DAY_OF_YEAR, -1);
			qynCalendar.add(Calendar.MONTH, 1);
			try {
				for(int j=0;j<36;j++){
					calendar.set(Calendar.DAY_OF_MONTH,0); 
					String lastDay = f.format(calendar.getTime());
					nsryhxxEntity.setYxqz(lastDay);
					calendar.set(Calendar.DAY_OF_MONTH,1); 
					String  firstDay = f.format(calendar.getTime());
					nsryhxxEntity.setYxqq(firstDay);
					getThreeYearData(nsryhxxEntity, taxDataSaveService,sqxh, invokeRecodeService);
				}
			} catch (Exception e) {
				if("success".equals(sb.toString())){
					sb=new StringBuffer();
					sb.append("获取湖南 接口中的企业税务数据失败");
				}
				logger.error("getThreeYearData error !");
			}
		}else{
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
				sb.append(" false 获取湖南 接口中的企业税务数据失败");
			}
		}
		logger.info("getDateFromHNJK end !");
		String returnFlag = sb.toString();
		return returnFlag;
	}
	//获取企业利润年报信息
	/**
	 * @Author Zhongyj
	 * @date 2017-4-21 下午3:57:39
	 * @param nsryhxxEntity
	 * @param taxDataSaveService
	 * @param sqxh
	 * @param invokeRecodeService
	 * @return
	 * @throws Exception
	 */
	public String  getNianBaoxx(NsryhxxEntity nsryhxxEntity,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception {
		logger.info("getNianBaoxx 开始获取年报信息数据");
		StringBuffer sb = new StringBuffer("success");
		String userId = nsryhxxEntity.getId().toString();
		Parameter parameter = new Parameter();
		parameter.setNsrdzdah(nsryhxxEntity.getDjxh());
		parameter.setLpbm(nsryhxxEntity.getLpbm());
		parameter.setSsqq(nsryhxxEntity.getYxqq());
		parameter.setSsqz(nsryhxxEntity.getYxqz());
		Gson gson = new Gson();
		RequestConfig requestConfig_xqylrbnb = new RequestConfig();
		requestConfig_xqylrbnb.setServiceId(ServiceCommon.SERVICE_ID_XQYLRBNB);
		requestConfig_xqylrbnb.setChannelId("dfwyhn");
		requestConfig_xqylrbnb.setPassword(RandomUtil.randomFor8());
		requestConfig_xqylrbnb.setContent(gson.toJson(parameter));
		List<Map<String, String>> xqylrbnb = null;
		try {
			xqylrbnb = Commons.achieveData(requestConfig_xqylrbnb, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
				sb.append("获取利润表(小企业会计制度)_年报表,信息未取到 ");
			}
			logger.error("获取利润表(小企业会计制度)_年报表,信息未取到");
		}
		taxDataSaveService.insertXqylrbnb(xqylrbnb);
		logger.info("getNianBaoxx 结束获取年报信息数据");
		return sb.toString();
	}
	
	/**
	 * 获取近三年数据的接口数据
	 * @Author Zhongyj
	 * @date 2017-4-21 下午3:57:58
	 * @param nsryhxxEntity
	 * @param taxDataSaveService
	 * @param sqxh
	 * @param invokeRecodeService
	 * @return
	 * @throws Exception
	 */
	public String getThreeYearData(NsryhxxEntity nsryhxxEntity,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService)throws Exception {
		logger.info("getThreeYearData start !");
		StringBuffer sb = new StringBuffer("success");
		String userId = nsryhxxEntity.getId().toString();
		Parameter parameter = new Parameter();
		parameter.setNsrdzdah(nsryhxxEntity.getDjxh());
		parameter.setLpbm(nsryhxxEntity.getLpbm());
		parameter.setSsqq(nsryhxxEntity.getYxqq());
		parameter.setSsqz(nsryhxxEntity.getYxqz());

		Thread thread1 = new Thread( new GetHnSbxx().getHnSbxx(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService) );

		Thread thread2 = new Thread( new GetHnYjsf().getHnYjsf(parameter,userId, taxDataSaveService,sqxh,invokeRecodeService) );

		Thread thread3 = new Thread( new GetHnXqyzcfzb().getHnXqyzcfzb(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService));

		Thread thread4 = new Thread( new GetHnXqylrb().getHnXqylrb(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService));

		Thread thread5 = new Thread( new GetHnYnbsr().getHnYnbsr(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService));

		Thread thread6 = new Thread( new GetHnXgmnsr().getHnXgmnsr(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService));

		Thread thread7 = new Thread( new GetHnNsrzgxxjgb().getHnNsrzgxxjgb(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService));

		Thread thread8 = new Thread( new GetHnQsclxx().getHnQsclxx(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService));

		Thread thread9 = new Thread( new GetHnZlbscjb().getHnZlbscjb(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService));

		Thread thread10 = new Thread( new GetHnQyzcfzb().getHnQyzcfzb(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService));

		Thread thread11 = new Thread( new GetHnQylrb().getHnQylrb(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService));

		Thread thread12 = new Thread( new GetHnYbqyzcfzb().getHnYbqyzcfzb(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService));

		Thread thread13 = new Thread( new GetHnYbqylrb().getHnYbqylrb(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService));

		Thread thread14 = new Thread( new GetHnAyxx().getHnAyxx(parameter,userId,taxDataSaveService,sqxh,invokeRecodeService));
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		thread9.start();
		thread10.start();
		thread11.start();
		thread12.start();
		thread13.start();
		thread14.start();
		if(thread1.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread2.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread3.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread4.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread5.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread5.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread7.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread8.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread9.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread10.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread11.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread12.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread13.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}
		if(thread14.isInterrupted()){
			Thread.currentThread();
			Thread.interrupted();
		}

		return sb.toString();
	}
	/**
	 * 调用接口存储数据
	 * @Author Zhongyj
	 * @date 2017-4-21 下午4:04:08
	 * @param nsryhxxEntity
	 * @param taxDataSaveService
	 * @param sqxh
	 * @param invokeRecodeService
	 * @return
	 * @throws Exception
	 */
	public String hnJksjCc(NsryhxxEntity nsryhxxEntity,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception {
		//调接口，取数据
		logger.info("hnJksjCc started !");
		StringBuffer sb = new StringBuffer("success");
		String userId = nsryhxxEntity.getId().toString();
		Parameter parameter = new Parameter();
		parameter.setNsrdzdah(nsryhxxEntity.getDjxh());
		parameter.setLpbm(nsryhxxEntity.getLpbm());
		parameter.setSsqq(nsryhxxEntity.getYxqq());
		parameter.setSsqz(nsryhxxEntity.getYxqz());
		Gson gson = new Gson();
		//纳税人基础信息表
		RequestConfig requestConfig_nsrjcxx = new RequestConfig();
		requestConfig_nsrjcxx.setServiceId(ServiceCommon.SERVICE_ID_NSRJCXX);
		requestConfig_nsrjcxx.setChannelId("dfwyhn");
		requestConfig_nsrjcxx.setPassword(RandomUtil.randomFor8());
		requestConfig_nsrjcxx.setContent(gson.toJson(parameter));
		List<Map<String, String>> nsrjcxx = null;
		try {
			nsrjcxx = Commons.achieveData(requestConfig_nsrjcxx, userId, sqxh,invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
				sb.append("纳税人基础信息表,信息未取到");
			}
			logger.error("纳税人基础信息表,信息未取到");
		}
		taxDataSaveService.insertNsrjcxx(nsrjcxx);
		
		//纳税人基础信息扩展
		RequestConfig requestConfig_nsrjcxxkz = new RequestConfig();
		requestConfig_nsrjcxxkz.setServiceId(ServiceCommon.SERVICE_ID_NSRXXKZ);
		requestConfig_nsrjcxxkz.setChannelId("dfwyhn");
		requestConfig_nsrjcxxkz.setPassword(RandomUtil.randomFor8());
		requestConfig_nsrjcxxkz.setContent(gson.toJson(parameter));
		List<Map<String, String>> nsrjcxxkz = null;
		try {
			nsrjcxxkz = Commons.achieveData(requestConfig_nsrjcxxkz, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
				sb.append("纳税人基础信息扩展表,信息未取到");
			}
			logger.error("纳税人基础信息扩展表,信息未取到");
		}
		taxDataSaveService.insertNsrxxKz(nsrjcxxkz);
		//获取企业批准机构
		RequestConfig requestConfig_pzjgxx = new RequestConfig();
		requestConfig_pzjgxx.setServiceId(ServiceCommon.SERVICE_ID_PZJGXX);
		requestConfig_pzjgxx.setChannelId("dfwyhn");
		requestConfig_pzjgxx.setPassword(RandomUtil.randomFor8());
		requestConfig_pzjgxx.setContent(gson.toJson(parameter));
		List<Map<String, String>> pzjgxx = null;
		try {
			pzjgxx = Commons.achieveData(requestConfig_pzjgxx, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
				sb.append("获取企业批准机构表,信息未取到");
			}
			logger.error("获取企业批准机构表,信息未取到");
		}
		taxDataSaveService.insertPzjgxx(pzjgxx);
		//获取企业变更信息
		RequestConfig requestConfig_bgdjmx = new RequestConfig();
		requestConfig_bgdjmx.setServiceId(ServiceCommon.SERVICE_ID_BGDJMX);
		requestConfig_bgdjmx.setChannelId("dfwyhn");
		requestConfig_bgdjmx.setPassword(RandomUtil.randomFor8());
		requestConfig_bgdjmx.setContent(gson.toJson(parameter));
		List<Map<String, String>> bgdjmx = null;
		try {
			bgdjmx = Commons.achieveData(requestConfig_bgdjmx, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
				sb.append("获取企业变更信息表,信息未取到");
			}
			logger.error("获取企业变更信息表,信息未取到");
		}
		taxDataSaveService.insertBgdjmx(bgdjmx);
		//获取企业投资方
		RequestConfig requestConfig_tzfxx = new RequestConfig();
		requestConfig_tzfxx.setServiceId(ServiceCommon.SERVICE_ID_TZFXX);
		requestConfig_tzfxx.setChannelId("dfwyhn");
		requestConfig_tzfxx.setPassword(RandomUtil.randomFor8());
		requestConfig_tzfxx.setContent(gson.toJson(parameter));
		List<Map<String, String>> tzfxx = null;
		try {
			tzfxx = Commons.achieveData(requestConfig_tzfxx, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
				sb.append("获取企业投资方表,信息未取到");
			}
			logger.error("纳税人基础信息表,信息未取到");
		}
		taxDataSaveService.insertTzfxx(tzfxx);
		//获取企业分支机构
		RequestConfig requestConfig_fzjgxx = new RequestConfig();
		requestConfig_fzjgxx.setServiceId(ServiceCommon.SERVICE_ID_FZJGXX);
		requestConfig_fzjgxx.setChannelId("dfwyhn");
		requestConfig_fzjgxx.setPassword(RandomUtil.randomFor8());
		requestConfig_fzjgxx.setContent(gson.toJson(parameter));
		List<Map<String, String>> fzjgxx = null;
		try {
			fzjgxx = Commons.achieveData(requestConfig_fzjgxx, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
				sb.append("获取企业分支机构表,信息未取到");
			}
			logger.error("纳税人基础信息表,信息未取到");
		}
		taxDataSaveService.insertFzjgxx(fzjgxx);
		//获取企业总分机构信息
		RequestConfig requestConfig_zzjgxx = new RequestConfig();
		requestConfig_zzjgxx.setServiceId(ServiceCommon.SERVICE_ID_ZZJGXX);
		requestConfig_zzjgxx.setChannelId("dfwyhn");
		requestConfig_zzjgxx.setPassword(RandomUtil.randomFor8());
		requestConfig_zzjgxx.setContent(gson.toJson(parameter));
		List<Map<String, String>> zzjgxx = null;
		try {
			zzjgxx = Commons.achieveData(requestConfig_zzjgxx, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
				sb.append("获取企业总分机构信息表,信息未取到");
			}
			logger.error("纳税人基础信息表,信息未取到");
		}
		taxDataSaveService.insertZzjgxx(zzjgxx);
		//获取企业违法违章
		RequestConfig requestConfig_sswfxwdj = new RequestConfig();
		requestConfig_sswfxwdj.setServiceId(ServiceCommon.SERVICE_ID_SSWFXWDJ);
		requestConfig_sswfxwdj.setChannelId("dfwyhn");
		requestConfig_sswfxwdj.setPassword(RandomUtil.randomFor8());
		requestConfig_sswfxwdj.setContent(gson.toJson(parameter));
		List<Map<String, String>> sswfxwdj = null;
		try {
			sswfxwdj = Commons.achieveData(requestConfig_sswfxwdj, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
				sb.append("获取企业总分机构信息表,信息未取到");
			}
			logger.error("纳税人基础信息表,信息未取到");
		}
		taxDataSaveService.insertSswfxwdj(sswfxwdj);
		//获取企业稽查案件信息
		RequestConfig requestConfig_ajxx = new RequestConfig();
		requestConfig_ajxx.setServiceId(ServiceCommon.SERVICE_ID_AJXX);
		requestConfig_ajxx.setChannelId("dfwyhn");
		requestConfig_ajxx.setPassword(RandomUtil.randomFor8());
		requestConfig_ajxx.setContent(gson.toJson(parameter));
		List<Map<String, String>> ajxx = null;
		try {
			ajxx = Commons.achieveData(requestConfig_ajxx, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
				sb.append("获取企业稽查案件信息表,信息未取到");
			}
			logger.error("纳税人基础信息表,信息未取到");
		}
		taxDataSaveService.insertAjxx(ajxx);
		//授权编码有效期查询
		RequestConfig requestConfig_lpbmcx = new RequestConfig();
		requestConfig_lpbmcx.setServiceId(ServiceCommon.SERVICE_ID_LPBMCX);
		requestConfig_lpbmcx.setChannelId("dfwyhn");
		requestConfig_lpbmcx.setPassword(RandomUtil.randomFor8());
		requestConfig_lpbmcx.setContent(gson.toJson(parameter));
		List<Map<String, String>> lpbmcx = null;
		try {
			lpbmcx = Commons.achieveData(requestConfig_lpbmcx, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			logger.error("纳税人基础信息表,信息未取到");
			sb.append("授权编码有效期查询表,信息未取到");
		}
		taxDataSaveService.insertLpbmcx(lpbmcx);
		//纳税人信用等级查询
		RequestConfig requestConfig_xydj = new RequestConfig();
		requestConfig_xydj.setServiceId(ServiceCommon.SERVICE_ID_XYDJ);
		requestConfig_xydj.setChannelId("dfwyhn");
		requestConfig_xydj.setPassword(RandomUtil.randomFor8());
		requestConfig_xydj.setContent(gson.toJson(parameter));
		List<Map<String, String>> xydj = null;
		try {
			xydj = Commons.achieveData(requestConfig_xydj, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
			}
			logger.error("纳税人基础信息表,信息未取到");
			sb.append("纳税人信用等级查询表,信息未取到");
		}
		taxDataSaveService.insertNsxyjb(xydj);
		logger.info("hnJksjCc end !");
		return sb.toString();
	}
}
