/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：ClientController.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2017-7-26下午上午11:08:132:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.gxgsService.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.govsbu.sd.taxbankplatform.common.Showproperties;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.common.util.WriteContent;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.service.ClientService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

/**
 * @versions:1.0
 * @filename：ClientController.java
 * @Company:dfwyBank
 * @Created: 2017-7-26下午上午11:08:132:57:43
 * @department:深圳IT部门
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName ClientController
 */
@Controller("clientController")
@Scope("prototype")
public class ClientController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	static boolean osFlag = false;
    static{
    	if(System.getProperties().getProperty("os.name").toLowerCase().startsWith("win")){  
    		osFlag = true;
    	} 
    }

	@Autowired
	private ClientService clientService;
	/**
	 * 动态拼装insert 语句
	 * 
	 * @Author Zhongyj
	 * @date 2017-7-27 上午8:39:29
	 * @return
	 */
	public String findDataByTime(String tableName, String pch) {
		logger.info("findDataByTime start !");
		Date date = new Date();
		
		/***根据传入的表名动态拼接获取主键id的sql***/
		String keySql = "SHOW CREATE TABLE " + tableName;
		
		/***根据传入的表名动态拼接获取该表在tb_data_synchro_yhi表中的最新一条记录的sql***/
		String keyidSql = "SELECT * FROM tb_data_synchro_yhi WHERE tablename = '"+ tableName + "' ORDER BY createtime DESC LIMIT 1";
		
		Map<String, Object> tableMap = null;
		
		StringBuffer result = new StringBuffer();
		
		try {
			tableMap = clientService.findPrimaryKey(keySql);
			
			Map<String, Object> keyidMap = clientService.findPrimaryKeyByTableName(keyidSql);
			
			/***获取传入表的建表语句***/
			String primKeys = (String) tableMap.get("Create Table");
			
			/***过滤获取传入表的主键id***/
			String pa = primKeys.substring(primKeys.lastIndexOf("KEY"),primKeys.lastIndexOf(")"));
			
			String id = pa.substring(pa.lastIndexOf("(") + 2,pa.lastIndexOf(")") - 1);
			
			String starTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			
			String ssql = null;
			
			String keyId = null;
			
			/**
			 * 判断 传入的表在tb_data_synchro_yhi中是否有记录
			 * 1.如果有记录 获取到synendtime同步数据结束的时间戳作为需要当前同步数据的开始时间戳
			 * 2.如果没有记录 则 直接查询传入表小于当前时间戳的所有数据
			 */
			if (keyidMap != null) {
				
				if (keyidMap.get("synendtime") != null) {
					
					if (keyidMap.get("keyid") != null) {
						
						keyId = (String) keyidMap.get("keyid");
					}
					
					starTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) keyidMap.get("synendtime"));
					
					ssql = "SELECT * FROM "+ tableName+ " Where " +id+ " > '"+keyId +"' "+" ORDER BY "+ id+" ASC";
				
				}
				
			} else {
				
				ssql = "SELECT * FROM " + tableName  ;
			}
			
			/***根据传入的表名和条件 查询出需要同步的数据***/
			List<Map<String, Object>> list = clientService.findDataByTime(ssql);
			
			if (list!=null && list.size() > 0) {
				
				StringBuffer sb = new StringBuffer();
				
				/***循环获取到需要同步数据的表字段名便于在下面动态获取需要插入的数据 做到一一对应***/
				Map<String, Object> mp = list.get(0);
				
				for (String key : mp.keySet()) {
					
					sb.append(key);
					
					sb.append(",");
				}
				String zd = sb.toString();
				
				int size = zd.length();
				
				zd = zd.substring(0, size - 1);
				
				String arry[] = zd.split(",");
				
				System.out.println(arry);
				
				result.append("INSERT INTO " + tableName + "(" + zd + ") values ");
				
				for (int i = 0; i < list.size(); i++) {
					
					Map<String, Object> mpvalues = list.get(i);
					
					String tableId = null;
					
					if (mpvalues != null) {
						
						tableId = (String) mpvalues.get(id);
					}
					
					/***记录表中没有记录，需要同步的数据表有数据***/
					if (keyId == null && tableId != null) {
						
						StringBuffer sbvalu = new StringBuffer();
						
						sbvalu.append("(");
						
						String vs = null;
						
						for (int j = 0; j < arry.length; j++) {
							
							Object param = mpvalues.get(arry[j]);
							
							/***判断表中传回来的数据类型 动态拼接 插入语句的值***/
							if (param instanceof Integer) {
								
								sbvalu.append(String.valueOf(((Integer) param).intValue()));
								
								sbvalu.append(",");
								
							} else if (param instanceof String) {
								
								sbvalu.append("'");
								
								sbvalu.append((String) param);
								
								sbvalu.append("'");
								
								sbvalu.append(",");
								
							} else if (param instanceof Double) {
								
								sbvalu.append(String.valueOf(((Double) param).doubleValue()));
								
								sbvalu.append(",");
								
							} else if (param instanceof Float) {
								
								sbvalu.append(String.valueOf(((Float) param).floatValue()));
								
								sbvalu.append(",");
								
							} else if (param instanceof Long) {
								
								sbvalu.append(String.valueOf(((Long) param).longValue()));
								
								sbvalu.append(",");
								
							} else if (param instanceof Boolean) {
								
								sbvalu.append(String.valueOf(((Boolean) param).booleanValue()));
								
								sbvalu.append(",");
								
							} else if (param instanceof Date) {
								
								sbvalu.append("'");
								
								sbvalu.append(String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) param)));
								
								sbvalu.append("'");
								
								sbvalu.append(",");
								
							} else {
								
								sbvalu.append("null");
								
								sbvalu.append(",");
							}
							vs = sbvalu.toString();
							
							int vssize = vs.length();
							
							vs = vs.substring(0, vssize - 1);
						}
						if (i == list.size() - 1) {
							
							vs = vs + ");";
							
						} else {
							
							vs = vs + "),"+"\r\n";
							
						}
						/***注释掉的这一句 是写入文件的语句 在测试的时候 可以打开，便于查看封装的sql语句是否正确***/
						result.append(vs);
						//WriteContent.writeContent(vs + "\r\n","C:/Users/Administrator/Desktop/tb_loan_apply/","tb_loan_apply.txt", true);
					
						/***记录表中有记录 需要同步的数据表中也有数据***/
					} else if (keyId != null && tableId != null) {
						
						if (!keyId.equals(tableId)) {
							
							StringBuffer sbvalu = new StringBuffer();
							
							sbvalu.append("(");
							
							String vs = null;
							
							for (int j = 0; j < arry.length; j++) {
								
								Object param = mpvalues.get(arry[j]);
								
								if (param instanceof Integer) {
									
									sbvalu.append(String.valueOf(((Integer) param).intValue()));
									
									sbvalu.append(",");
									
								} else if (param instanceof String) {
									
									sbvalu.append("'");
									
									sbvalu.append((String) param);
									
									sbvalu.append("'");
									
									sbvalu.append(",");
									
								} else if (param instanceof Double) {
									
									sbvalu.append(String.valueOf(((Double) param).doubleValue()));
									
									sbvalu.append(",");
									
								} else if (param instanceof Float) {
									
									sbvalu.append(String.valueOf(((Float) param).floatValue()));
									
									sbvalu.append(",");
									
								} else if (param instanceof Long) {
									
									sbvalu.append(String.valueOf(((Long) param).longValue()));
									
									sbvalu.append(",");
									
								} else if (param instanceof Boolean) {
									
									sbvalu.append(String.valueOf(((Boolean) param).booleanValue()));
									
									sbvalu.append(",");
									
								} else if (param instanceof Date) {
									
									sbvalu.append("'");
									
									sbvalu.append(String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) param)));
									
									sbvalu.append("'");
									
									sbvalu.append(",");
									
								} else {
									
									sbvalu.append("null");
									
									sbvalu.append(",");
									
								}
								vs = sbvalu.toString();
								
								int vssize = vs.length();
								
								vs = vs.substring(0, vssize - 1);
								
							}
							if (i == list.size() - 1) {
								
								vs = vs + ");";
								
							} else {
								
								vs = vs + "),"+"\r\n";
								
							}
							result.append(vs);
							//WriteContent.writeContent(result.toString() + "\r\n","C:/Users/Administrator/Desktop/tb_loan_apply/","tb_loan_apply.txt", true);
						}
						
						/***记录表中有数据 但是需要同步的数据表中没有需要同步的数据***/
					} else if (keyId != null && tableId == null) {
						
						result = null;
						
					/***记录表和需要同步的表中都没有数据***/	
					} else {
						
						result = null;
					}
					
					/***如果需要同步的数据到了最后的一条数据 将其记录到 tb_data_synchro_yhi中***/
					if (i == list.size() - 1) {
						
						Map<String, Object> map = new HashMap<String, Object>();
						
						map.put("xh", IDGenerate.getZJID("XH"));
						
						map.put("pch", pch);
						
						map.put("keyid", mpvalues.get(id));
						
						map.put("tablename", tableName);
						
						map.put("synstartime", starTime);
						
						map.put("synendtime", new Date());
						
						map.put("createtime", new Date());
						
						map.put("updatetime", new Date());
						
						clientService.insertYhi(map);
					}
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return result.toString();
		}
		logger.info("findDataByTime end !");
		//WriteContent.writeContent(result.toString() + "\r\n","C:/Users/Administrator/Desktop/tb_loan_apply/","tb_loan_apply.txt", true);
		return result.toString();
	}

	/**
	 * 动态拼装update 语句
	 * 
	 * @Author Zhongyj
	 * @date 2017-7-27 上午8:39:29
	 * @return
	 */
	public String findUpdateDataByTime(String tableName, String pch) {
		
		logger.info("findUpdateDataByTime end !");
		
		Date date = new Date();
		
		/***根据传入的表名动态拼接获取主键id的sql***/
		String keySql = "SHOW CREATE TABLE " + tableName;
		
		/***封装SQL语句 根据传入的表名获取在tb_data_synchro_yhu 中的最新一条记录***/
		String keyidSql = "SELECT * FROM tb_data_synchro_yhu WHERE tablename = '" + tableName + "' ORDER BY createtime DESC LIMIT 1";
		
		Map<String, Object> keyidMap = null;
		
		StringBuffer result = new StringBuffer();
		
		Map<String, Object> tableMap = null;
		try {
			
			tableMap = clientService.findPrimaryKey(keySql);
			
			/***获取传入表的建表语句***/
			String primKeys = (String) tableMap.get("Create Table");
			
			/***过滤获取传入表的主键id***/
			String pa = primKeys.substring(primKeys.lastIndexOf("KEY"),primKeys.lastIndexOf(")"));
			
			String id = pa.substring(pa.lastIndexOf("(") + 2,pa.lastIndexOf(")") - 1);
			
			keyidMap = clientService.findPrimaryKeyByTableName(keyidSql);
			
			String starTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			
			String ssql = null;
			
			String upSql59 = null;
			
			String keyId = null;
			
			String id59 = null;
			
			List<Map<String, Object>> listu59 = null;
			
			/**
			 * 判断传入的表在tb_data_synchro_yhu 表中是否有最新记录
			 * 1.如果有记录则获 记录里的同步数据时间结束的时间戳 作为查询需要同步数据的开始时间
			 * 2.如果没有记录 则 直接查询传入表的小于当前系统时间的所以数据
			 */
			if (keyidMap != null) {
				
				if (keyidMap.get("synendtime") != null) {
					
					String synstartime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) keyidMap.get("synstartime"));
					
					starTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) keyidMap.get("synendtime"));
					
					//upSql59 = "SELECT * FROM "+ tableName+ " Where updatetime BETWEEN '"+ synstartime +"' AND '" +starTime+"' AND updatetime like '%59'"+" AND "+id+" LIKE '%ZX%'";
					upSql59 = "SELECT * FROM "+ tableName+ " Where updatetime BETWEEN '"+ synstartime +"' AND '" +starTime+"' AND updatetime like '%59'";
					
					ssql = "SELECT * FROM "+ tableName+ " Where updatetime BETWEEN '"+ starTime+ "' AND '"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) + "'" +" ORDER BY updatetime ASC";
				}
				if(keyidMap.get("keyid") != null){
					
					keyId = (String)keyidMap.get("keyid");
				}
				
				//list59 是处理在时间59秒时没有同步数据到局端的数据集合
				listu59 = clientService.findDatau59BySynstarAndSynend(upSql59);
				
			} else {
				
				ssql = "SELECT * FROM " + tableName + " Where updatetime <= '"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+ "'" ;
			}
			
			List<Map<String, Object>> list = clientService.findDataByTime(ssql);
			
			if (listu59!=null && listu59.size() > 0) {
				
				String vsu59 = null;
				
				for (int i=0;i<listu59.size();i++){
					
					Map<String, Object> mpvaluesu59 = listu59.get(i);
					
					if(keyId!=null && id59!= null){
						
						StringBuffer sbvaluu59 = new StringBuffer();
						
						/***根据传入的表动态拼接 更新语句***/
						sbvaluu59.append("UPDATE " + tableName + " SET ");
						
						for (String key : mpvaluesu59.keySet()) {
							
							String keyName = key;
							
							Object param = mpvaluesu59.get(key);
							
							/***判断数据库中返回的数据类型 做相应的转换 然后放到更新语句中***/
							if (param instanceof Integer) {
								
								sbvaluu59.append(keyName + "=");
								
								sbvaluu59.append(String.valueOf(((Integer) param).intValue()));
								
								sbvaluu59.append(",");
								
							} else if (param instanceof String) {
								
								sbvaluu59.append(keyName + "='");
								
								sbvaluu59.append((String) param);
								
								sbvaluu59.append("',");
								
							} else if (param instanceof Double) {
								
								sbvaluu59.append(keyName + "=");
								
								sbvaluu59.append(String.valueOf(((Double) param).doubleValue()));
								
								sbvaluu59.append(",");
								
							} else if (param instanceof Float) {
								
								sbvaluu59.append(keyName + "=");
								
								sbvaluu59.append(String.valueOf(((Float) param).floatValue()));
								
								sbvaluu59.append(",");
								
							} else if (param instanceof Long) {
								
								sbvaluu59.append(keyName + "=");
								
								sbvaluu59.append(String.valueOf(((Long) param).longValue()));
								
								sbvaluu59.append(",");
								
							} else if (param instanceof Boolean) {
								
								sbvaluu59.append(keyName + "=");
								
								sbvaluu59.append(String.valueOf(((Boolean) param).booleanValue()));
								
								sbvaluu59.append(",");
								
							} else if (param instanceof Date) {
								
								sbvaluu59.append(keyName + "='");
								
								sbvaluu59.append(String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) param)));
								
								sbvaluu59.append("'");
								
								sbvaluu59.append(",");
								
							} else {
								sbvaluu59.append(keyName + "=");
								
								sbvaluu59.append("null");
								
								sbvaluu59.append(",");
								
							}
						}
						
						vsu59 = sbvaluu59.toString();
						
						int vssize = vsu59.length();
						
						vsu59 = vsu59.substring(0, vssize - 1);
						
						vsu59 = vsu59 + " WHERE " + id + "=" + "'"+ (String) mpvaluesu59.get(id) + "'" + ";";
						
					}
						
					if (vsu59 != null){
						
						result.append(vsu59);
					}
					
				}
				
			}
			
			if (list!=null && list.size() > 0) {
				
				for (int i = 0; i < list.size(); i++) {
					
					if (list.size() > 0) {
						
						Map<String, Object> mpvalues = list.get(i);
						
						StringBuffer sbvalu = new StringBuffer();
						
						String vs = null;
						
						/***根据传入的表动态拼接 更新语句***/
						sbvalu.append("UPDATE " + tableName + " SET ");
						
						for (String key : mpvalues.keySet()) {
							
							String keyName = key;
							
							Object param = mpvalues.get(key);
							
							/***判断数据库中返回的数据类型 做相应的转换 然后放到更新语句中***/
							if (param instanceof Integer) {
								
								sbvalu.append(keyName + "=");
								
								sbvalu.append(String.valueOf(((Integer) param).intValue()));
								
								sbvalu.append(",");
								
							} else if (param instanceof String) {
								
								sbvalu.append(keyName + "='");
								
								sbvalu.append((String) param);
								
								sbvalu.append("',");
								
							} else if (param instanceof Double) {
								
								sbvalu.append(keyName + "=");
								
								sbvalu.append(String.valueOf(((Double) param).doubleValue()));
								
								sbvalu.append(",");
								
							} else if (param instanceof Float) {
								
								sbvalu.append(keyName + "=");
								
								sbvalu.append(String.valueOf(((Float) param).floatValue()));
								
								sbvalu.append(",");
								
							} else if (param instanceof Long) {
								
								sbvalu.append(keyName + "=");
								
								sbvalu.append(String.valueOf(((Long) param).longValue()));
								
								sbvalu.append(",");
								
							} else if (param instanceof Boolean) {
								
								sbvalu.append(keyName + "=");
								
								sbvalu.append(String.valueOf(((Boolean) param).booleanValue()));
								
								sbvalu.append(",");
								
							} else if (param instanceof Date) {
								
								sbvalu.append(keyName + "='");
								
								sbvalu.append(String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) param)));
								
								sbvalu.append("'");
								
								sbvalu.append(",");
								
							} else {
								sbvalu.append(keyName + "=");
								
								sbvalu.append("null");
								
								sbvalu.append(",");
								
							}
						}
						vs = sbvalu.toString();
						
						int vssize = vs.length();
						
						vs = vs.substring(0, vssize - 1);
						
						vs = vs + " WHERE " + id + "=" + "'"+ (String) mpvalues.get(id) + "'" + ";";
						
						result.append(vs);
						//WriteContent.writeContent(result.toString() + "\r\n","C:/Users/Administrator/Desktop/tb_loan_apply/","tb_loan_apply.txt", true);
						
						/***如果需要同步的数据到了最后一条，将最后一条的主键id出入到tb_data_synchro_yhu中***/
						if (i == list.size() - 1) {
							
							Map<String, Object> map = new HashMap<String, Object>();
							
							map.put("xh", IDGenerate.getZJID("XH"));
							
							map.put("pch", pch);
							
							map.put("keyid", mpvalues.get(id));
							
							map.put("tablename", tableName);
							
							map.put("synstartime", starTime);
							
							map.put("synendtime", new Date());
							
							map.put("createtime", new Date());
							
							map.put("updatetime", new Date());
							
							clientService.insertYhu(map);
						}
					}
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return result.toString();
		}
		
		logger.info("findUpdateDataByTime end !");
		//WriteContent.writeContent(result.toString() + "\r\n","C:/Users/Administrator/Desktop/tb_loan_apply/","tb_loan_apply.txt", true);
		return result.toString();
	}

	/*** 将数据转换为json窜 ***/
	@SuppressWarnings("unchecked")
	public void getJsonStr() {
		
		logger.info("getJsonStr start !");
		
		try {
	        Date date = new Date();
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String dateTag = dateFormat.format(date);
			
			String path = null;
	      	if(osFlag == true){
	      		path = Showproperties.GetBd_Doing_Dir();
			} else {
				path = Showproperties.GetDoing_Dir();
			}
			
			String selectTable = Showproperties.GetSelect_table();
			
			String arrayTable[] = selectTable.split(",");
			
			String pch = IDGenerate.getZJID("PCH");
			
			System.out.println(arrayTable);
			
			StringBuffer inBuffer = new StringBuffer();
			StringBuffer upBuffer = new StringBuffer();
			String inStr = null;
			String upStr = null;
			for (int i = 0; i < arrayTable.length; i++) {
				
				String tableName = arrayTable[i];
				inStr = null;
				inStr = findDataByTime(tableName, pch);
				 if (inStr!=null && inStr.length()>0) {
					 inBuffer.append(inStr);
					 inBuffer.append("\r\n");
				}
			}
			String insString = null;
			if (inBuffer != null && inBuffer.length()>0) {
				insString = inBuffer.toString();
				WriteContent.writeContent(insString + "\r\n",path,dateTag+"_insterTable.txt", true);
			}
			String updateTable = Showproperties.GetUpdate_table();
			
			String arrayUpdateTable[] = updateTable.split(",");
			for (int i = 0; i < arrayUpdateTable.length; i++) {
				String tableName = arrayUpdateTable[i];
				upStr = null;
				upStr = findUpdateDataByTime(tableName, pch);
				if (upStr != null && upStr.length()>0) {
					upBuffer.append(upStr);
					upBuffer.append("\r\n");
				}
			}
			if (upBuffer != null && upBuffer.length()>0) {
				WriteContent.writeContent(upBuffer.toString() + "\r\n",path,dateTag+"_updateTable.txt", true);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		logger.info("getJsonStr end !");
	}

}
