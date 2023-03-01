package com.ntd.model;

import lombok.Data;

@Data
public class User {
	//id
	private Integer CustomerID;
	/*
	 * name
	 */
	private String CustomerSei;
	private String CustomerMei;
	/*
	 * acount
	 */
	private String CustomerAccount;
	/*
	 * pass
	 */
	private String CustomerPass;
	/*
	 * gender
	 */
	private String CustomerGender;
	/*
	 * age
	 */
	private String Year;
	private String Month;
	private String Day;
	private String CustomerAge;
	
	private String checkBox;
}
