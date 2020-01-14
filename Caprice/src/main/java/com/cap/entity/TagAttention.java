package com.cap.entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class TagAttention implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3684868944042980301L;
	private int userId;
	private int tag;
}
