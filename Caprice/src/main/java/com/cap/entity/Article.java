package com.cap.entity;

import java.io.Serializable;
import com.sun.jmx.snmp.Timestamp;

import lombok.Data;
@Data
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5598506237128833566L;
	private int articleId;
	private int userId;
	private int accessPermission;
	//发布日期
	private Timestamp releaseDate;
	//最后修改日期
	private Timestamp lastModifiedDate;
	private String content;
	private String articleTag;
	
}
