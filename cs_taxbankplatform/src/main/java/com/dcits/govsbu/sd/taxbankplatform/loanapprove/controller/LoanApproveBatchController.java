package com.dcits.govsbu.sd.taxbankplatform.loanapprove.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.BatchCheckEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveBatchService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;


@Controller
@Scope("prototype")
@RequestMapping("/loanapprovebatch/")
public class LoanApproveBatchController extends BaseController {
	@Autowired
	private LoanApproveBatchService loanApproveBatchService;
	

	@Autowired
	private LoanApproveService loanApproveService;
	
	/**
	 * 功能: 跳到批量审批界面
	 * @param model
	 * @param request
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "batch.html")
	public String showAttachment(Model model, HttpServletRequest request, String path) {
		return Common.BACKGROUND_PATH + "/loanapprove/loanapproveBatch";
	}
	
	
	/**
	 * 功能: 导入excel数据
	 */
	@RequestMapping(value = "uploadExcel.html", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadExcel(@RequestParam MultipartFile excelFile,HttpServletRequest request){
		Map<String,Object> returnMap = new HashMap<>();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		if(sessionUser == null){
			returnMap.put("message", "会话过期，请重新登录");
			returnMap.put("code", 200);//数据错误
			return returnMap;
		}
		
		//判定文件类型
		String suffixs=".xls";
		String suff=excelFile.getOriginalFilename().substring(excelFile.getOriginalFilename().lastIndexOf("."));
		if(suffixs.indexOf(suff)==-1){
			returnMap.put("code", 200);
			returnMap.put("message", "文件格式不正确");
			return returnMap;
		}
		//获取输入流
		POIFSFileSystem fs;
		HSSFWorkbook wb = null;
		try {
			fs = new POIFSFileSystem(excelFile.getInputStream());
			wb=new HSSFWorkbook(fs);//获取表格数据
		} catch (IOException e) {
			returnMap.put("message", "获取表格失败");
			returnMap.put("code", 200);//获取表格失败
			return returnMap;
		}catch (OfficeXmlFileException e) {
			returnMap.put("message", "Office 版本问题，请使用系统下载模板，或将.xlsx文件另存为.xls文件");
			returnMap.put("code", 200);//获取表格失败
			return returnMap;
		}
		//第一个sheet页
		HSSFSheet hssfSheet=wb.getSheetAt(0);
		
		List<BatchCheckEntity> list = new ArrayList<>();
		BatchCheckEntity batchCheckEntity;
		if(hssfSheet!=null){
			int rowCount = hssfSheet.getLastRowNum();
			if(rowCount < 1){
				returnMap.put("code", 200);//数据为空
				returnMap.put("message", "数据为空");
				return returnMap;
			}
			//获取映射关系Map
			Map<String,Object> map = new HashMap<>();
			map.put("org_id", sessionUser.getOrgid());
			List<Map<String,Object>> rwMapping = loanApproveBatchService.getTbpRwId(map);
			Map<String,String> BankToTbp = new HashMap<String,String>();
			for (Map<String,Object> singleMap : rwMapping) {
				 String bank_id = (String) singleMap.get("bank_id");
				 String tbp_id = (String)singleMap.get("tbp_id");
				 BankToTbp.put(bank_id, tbp_id);
			}
			
			for(int rowNum=1;rowNum<=rowCount;rowNum++){
				HSSFRow hssfRow=hssfSheet.getRow(rowNum);
				if(isRowEmpty(hssfRow)){//如果该行每一单元格都是空数据
					continue;
				}
				batchCheckEntity = new BatchCheckEntity();
				try {
					//不判断是否符合规格，只做显示
					batchCheckEntity.setRowNum(rowNum);//行号
					batchCheckEntity.setNsrsbh(myGetCellStringValue(hssfRow.getCell(0)));//纳税人识别号
					batchCheckEntity.setYhsqxh(myGetCellStringValue(hssfRow.getCell(1)));//银行申请序号
					batchCheckEntity.setResult(myGetCellStringValue(hssfRow.getCell(2)));//批贷结果
					batchCheckEntity.setSxje(myGetCellStringValue(hssfRow.getCell(3)));//授信金额
					batchCheckEntity.setSxzq(myGetCellStringValue(hssfRow.getCell(4)));//授信周期
					batchCheckEntity.setSprq(myGetCellStringValue(hssfRow.getCell(5)));//审批日期
					batchCheckEntity.setDkqxStart(myGetCellStringValue(hssfRow.getCell(6)));//贷款期限起
					batchCheckEntity.setDkqxEnd(myGetCellStringValue(hssfRow.getCell(7)));//贷款期限止
					batchCheckEntity.setSxll(myGetCellStringValue(hssfRow.getCell(8)));//授信利率
					
					//还款方式
					String hkfs = myGetCellStringValue(hssfRow.getCell(9));
					batchCheckEntity.setBankHkfs(hkfs);//保存银行还款方式
					batchCheckEntity.setHkfs(bankToTbp(hkfs,BankToTbp));//转化为Tbp平台还款方式代码
					
				} catch (Exception e) {
					e.printStackTrace();
					returnMap.put("message", "表格或数据错误");
					returnMap.put("code", 200);//数据错误
					return returnMap;
				}
				
				//excel所有的数据
				list.add(batchCheckEntity);
			}
			returnMap.put("code", 202);//成功标记
			returnMap.put("list", list);
			returnMap.put("repayWay", loanApproveBatchService.getHkfsList());
			return returnMap;
		}else{
			returnMap.put("code", 200);//数据为空
			returnMap.put("message", "数据为空");
			return returnMap;
		}
	}
	
	
	/**
	 * @category 批量审批数据
	 * @return
	 */
	@RequestMapping(value = "checkBatch.html", method = RequestMethod.POST)
	@ResponseBody
	public Object checkBatch(String list){
		Map<String,Object> returnMap = new HashMap<>();//返回值
		
		JSONArray array = JSONArray.fromObject(list);//由字符串化为List
		List<BatchCheckEntity> batchList = (List<BatchCheckEntity>)JSONArray.toCollection(array, BatchCheckEntity.class);
		List<BatchCheckEntity> listCkeck = new ArrayList<>();//合乎规格数据
		List<BatchCheckEntity> listSuccess = new ArrayList<>();//成功数据
		List<BatchCheckEntity> listFailed = new ArrayList<>();//失败数据
		
		//1，判定数据合法性
		for(BatchCheckEntity batchCheckEntity:batchList){
			if(isRightData(batchCheckEntity)){
				listCkeck.add(batchCheckEntity);//可审核数据
			}else{
				listFailed.add(batchCheckEntity);//不可审核数据
			}
		}
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		
		//2，审批可审核数据，记录成功的审批和失败的审批
		for (BatchCheckEntity batchCheckEntity : listCkeck) {
			//获取申请审批实体
			LoanApproveFinalEntity loanApproveFinalEntity = loanApproveBatchService.getSHEntity(batchCheckEntity,sessionUser);
			if(loanApproveFinalEntity == null){//找不到该序号下的未受理、未授信贷款申请
				listFailed.add(batchCheckEntity);
				continue;
			}
			try{
				int result = loanApproveService.insert(loanApproveFinalEntity);//调用处理审批信息接口
				if(result > 0){
					listSuccess.add(batchCheckEntity);
				}else{
					listFailed.add(batchCheckEntity);
				}	
			}catch(Exception e){
				e.printStackTrace();
				listFailed.add(batchCheckEntity);//有任何异常都算做审批失败数据
			}
			
		}
		
		returnMap.put("listSuccess", listSuccess);
		returnMap.put("listFailed", listFailed);
		returnMap.put("code", 202);
		return returnMap;
	}
	
	/**
	 * @category 导出处理结果表格
	 * @param list
	 */
	@RequestMapping(value = "downloadExport.html")
	public void downloadExport(String fileName,String list,HttpServletRequest request,HttpServletResponse response){
//		Map<String,Object> returnMap = new HashMap<String, Object>();
	
		JSONArray array = JSONArray.fromObject(list);
		List<BatchCheckEntity> batchList = (List<BatchCheckEntity>)JSONArray.toCollection(array, BatchCheckEntity.class);
		
		
		String path = 	request.getServletContext().getRealPath("/")+File.separator+"resources"+File.separator+"batchExcel"+File.separator+"resultDownload.xls";
		InputStream in;
		HSSFWorkbook wb = null;
		try {
			File file = new File(path);
			in = new FileInputStream(file);
			wb = new HSSFWorkbook(new POIFSFileSystem(in));
		}catch (IOException e) {
			e.printStackTrace();
//			returnMap.put("code", 200);
//			returnMap.put("message", "获取表格模板失败");
//			return returnMap;
		}
		
		HSSFSheet sheet = wb.getSheetAt(0);
//		sheet.removeRow(sheet.getRow(2));只清空不删除原样式
		/************************导出按原来的排序输出Start****************************/
		//创建排序规则
		class RowNumSort implements Comparator<BatchCheckEntity>{  
		    @Override  
		    //重写compare方法，return<0不变，return>0则交换顺序（保持升序）  
		    public int compare(BatchCheckEntity e1, BatchCheckEntity e2) {  
		        if(e1.getRowNum() > e2.getRowNum()){  
		            return 1;  
		        } else {  
		            return -1;  
		        }  
		    }  
		}
		Collections.sort(batchList, new RowNumSort()); 
		/************************导出按原来的排序输出End****************************/
		
		int rowIndex=1;
		for (BatchCheckEntity baEntity : batchList) {
			Row row=sheet.createRow(rowIndex++);
			row.createCell(0).setCellValue(baEntity.getNsrsbh());
			row.createCell(1).setCellValue(baEntity.getYhsqxh());
			row.createCell(2).setCellValue(baEntity.getResult());
			row.createCell(3).setCellValue(baEntity.getSxje());
			row.createCell(4).setCellValue(baEntity.getSxzq());
			row.createCell(5).setCellValue(baEntity.getSprq());
			row.createCell(6).setCellValue(baEntity.getDkqxStart());
			row.createCell(7).setCellValue(baEntity.getDkqxEnd());
			row.createCell(8).setCellValue(baEntity.getSxll());
			row.createCell(9).setCellValue(baEntity.getBankHkfs());//excel填写的还款方式
		}
		//设置样式
		int rowNum = sheet.getLastRowNum();//最后的行号
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();//列数
		setStyle(wb,rowNum,colCount);
		try {
			export(response,wb,fileName);//导出表格
//			returnMap.put("code", 202);
//			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
//			returnMap.put("code", 200);
//			returnMap.put("message", "导出表格失败");
//			return returnMap;
		}
		
	}
	
	//导出工具
	private static void export(HttpServletResponse response,Workbook wb,String fileName)throws Exception{
		String fileNames[] = fileName.split("\\.");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuffer name = new StringBuffer(fileNames[0]).append(sdf.format(new Date())).append(".").append(fileNames[1]);
		response.setHeader("Content-Disposition", "attachment;filename="+new String(name.toString().getBytes("utf-8"),"iso8859-1"));
		response.setContentType("application/ynd.ms-excel;charset=UTF-8");
		OutputStream out=response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
	}
	
	//设置样式
	private void setStyle(HSSFWorkbook wb,int rowNum,int colCount){
		//只居中
		HSSFCellStyle styleCenter = wb.createCellStyle();
		styleCenter.setAlignment(CellStyle.ALIGN_CENTER);
		styleCenter.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		//居中变红
		HSSFCellStyle styleColor = wb.createCellStyle();
		styleColor.setAlignment(CellStyle.ALIGN_CENTER);
		styleColor.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		HSSFFont font=wb.createFont();
        font.setColor(HSSFColor.RED.index);//HSSFColor.VIOLET.index //字体颜色
		styleColor.setFont(font);
		
		HSSFSheet sheet = wb.getSheetAt(0);
		for (int i = 1; i <= rowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {
				row.getCell(j).setCellStyle(styleCenter);
				if(j == 0||j ==1||j ==2)
					row.getCell(j).setCellStyle(styleColor);
			}
			
		}
		
		
	}
	
	
	/**
	 * @description 验证数据合规性
	 * @param batchCheckEntity
	 * @return
	 */
	private static boolean isRightData(BatchCheckEntity batchCheckEntity){
		String result = batchCheckEntity.getResult();
		
		/******************************格式检验(注意区分find和matches)*****************************/
		//必填项
		//审批结果：002通过/003不通过
		if(!Pattern.compile("^002|003$").matcher(result).matches())
			return false;
		
		//银行申请序号
		if(!Pattern.compile("^[A-Za-z1-9_-][A-Za-z0-9_-]+$").matcher(batchCheckEntity.getYhsqxh()).matches())
			return false;
		
		//日期格式：20170504 八位无中间符
		String rex = "^2[0-9]{3}(([0][1-9])|([1][12]))(([0][1-9])|([1-2][0-9])|([3][01]))$";
		
		//纳税人识别号样式（从重庆银行给的数据来看，部分未通过审批没有填写纳税人识别号，纳税人识别号现在改为通过时的必填项）
		//后来又说没有填写纳税人识别号不能入库
		if(!Pattern.compile("^[A-Za-z1-9_-][A-Za-z0-9_-]+$").matcher(batchCheckEntity.getNsrsbh()).matches())
			return false;
		
		/*********************************若是不通过，则后面数据不填***********************************/
		if(Pattern.compile("003").matcher(result).matches())
			return true;
		
		
		//审批日期(从重庆银行给的数据来看，部分未通过审批没有填写审批日期，审批日期现在改为通过时的必填项)
		if(!Pattern.compile(rex).matcher(batchCheckEntity.getSprq()).matches())
			return false;
		
		//授信金额:大于0的整数
		if(!Pattern.compile("^[1-9][0-9]{0,}$").matcher(batchCheckEntity.getSxje()).matches())
			return false;
		
		//授信周期：大于0的整数
		if(!Pattern.compile("^[1-9][0-9]{0,}$").matcher(batchCheckEntity.getSxzq()).matches())
			return false;
		
		//贷款期限起
		if(!Pattern.compile(rex).matcher(batchCheckEntity.getDkqxStart()).matches())
			return false;
		
		//贷款期限止
		if(!Pattern.compile(rex).matcher(batchCheckEntity.getDkqxEnd()).matches())
			return false;
		
		//小数最高2位，整数最高3位
		String rex2 = "^((([1-9][0-9]{0,2})|0)(\\.[0-9]{1,2})?)$";
		
		//授信利率：小数最高2位，整数最高3位
		if(!Pattern.compile(rex2).matcher(batchCheckEntity.getSxll()).matches())
			return false;
		
		//还款方式:数字（-1未匹配//-2未设置）
		if(Pattern.compile("^-[12]$").matcher(batchCheckEntity.getHkfs()).matches())
			return false;
		
		return true;
	}
	
	
	/**
	 * @description 获取对应类型的cell数据
	 * @param hssfCell
	 * @return
	 * @throws Exception
	 */
	private static String myGetCellStringValue(HSSFCell hssfCell) throws Exception{
		int type = -1;
		String value = "";
		
		//判断单元格是否null
		if(hssfCell == null){
			return value;
		}else{
			type = hssfCell.getCellType();
		}
		
		switch (type) {
			case Cell.CELL_TYPE_BLANK:
				value = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = String.valueOf(hssfCell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_ERROR:
				value = String.valueOf(hssfCell.getErrorCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				value = String.valueOf(hssfCell.getStringCellValue());//String
				break;
			case Cell.CELL_TYPE_NUMERIC:
				//处理小数问题
				HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
			    value = dataFormatter.formatCellValue(hssfCell);
	//			value = String.valueOf(hssfCell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				value = String.valueOf(hssfCell.getStringCellValue());
				break;
			default:
				break;
		}
		//去两端空格
		return value.trim();
	}
	
	/**
	 * @description 日期格式化
	 * @param dateStr
	 * @param style
	 * @return
	 * @throws ParseException
	 */
	private Date myDate(String dateStr,String style) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(style);  
//		ParsePosition pos = new ParsePosition(8);
	    Date date = sdf.parse(dateStr);
		return date;
	}
	
	
	/**
	 * 判断是否是空行
	 * @param row
	 * @return
	 */
	public static boolean isRowEmpty(Row row) {
		   for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
		       Cell cell = row.getCell(c);
		       if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
		           return false;
		   }
		   return true;
		}
	
	
	/**
	 * 银行到平台还款方式映射
	 * @param hkfs
	 * @return
	 */
	public static String bankToTbp(String hkfs,Map<String,String> BankToTbp){
		 String value = "-2";//未填写
			 
		 //导入化为系统内还款方式代码
		 if(hkfs != null && hkfs.trim().length() > 0){
			 if(BankToTbp.get(hkfs) == null){
				 value = "-1";//未匹配
			 }else{
				 value = BankToTbp.get(hkfs).toString();
			 }
		 }else{
			//未填写
		 }
		 
		 return value;
		 
	}
}
