package com.dcits.govsbu.sd.taxbankplatform.provincecities.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;


/**
 * @author liwangxiong
 * @date 2016年8月9日
 * @caption 省市区代码表
 */
public class ProvinceCitiesEntity extends BaseEntity{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/*
	 *地域名称
	 */
	private String pcname;
	/*
	 *父节点id
	 */
	private String pcpid;
	/*
	 *有效标志
	 */
	private String pccode;
	
	
	//--------set 、get 方法
	
	
	public String getPcname() {
		return pcname;
	}
	public void setPcname(String pcname) {
		this.pcname = pcname;
	}
	public String getPcpid() {
		return pcpid;
	}
	public void setPcpid(String pcpid) {
		this.pcpid = pcpid;
	}
	public String getPccode() {
		return pccode;
	}
	public void setPccode(String pccode) {
		this.pccode = pccode;
	}
	
	  @Override 
	    public boolean equals(Object obj) { 
	        if(this==obj){ 
	            return true ; 
	        } 
	        //如果是空  
	        if(obj==null ){ 
	            return false; 
	        } 
	        //比较两个People的名字是否相同  
	        if(obj!=null && obj instanceof ProvinceCitiesEntity){ 
	            if(((ProvinceCitiesEntity)obj).pccode.equals(this.pccode)) 
	                return  true ; 
	        } 
	        return false; 
	 
	    } 	 
	    @Override 
	    public int hashCode(){ 
	        // String的hashcode本来就是用来比较两个字符是否相等  
	        return pccode.hashCode(); 
	 
	    }  
}
