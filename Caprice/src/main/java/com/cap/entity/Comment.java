package com.cap.entity;

import java.io.Serializable;

import com.sun.jmx.snmp.Timestamp;

import lombok.Data;
@Data
public class Comment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3789579019695662211L;
	private int commentId;
	private int reviewers;
	private int articleId;
	private String commentContent;
	private Timestamp commentDate;
	private int byReviewers;
	private int bycommentId;

}
