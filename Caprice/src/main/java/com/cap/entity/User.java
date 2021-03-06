package com.cap.entity;

import java.io.Serializable;

import com.sun.jmx.snmp.Timestamp;

import lombok.Data;
@Data
public class User implements Serializable {

	private int userId;
	private String userName;
	private String sex;
	private String telephone;
	private int level;
	private String mailBox;
	private String passWord;
	private Timestamp registerTime;
	private Timestamp birthday;
	private String headPortrait;
	private String Signature;
	
}
