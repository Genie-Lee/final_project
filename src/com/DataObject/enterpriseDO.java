package com.DataObject;

public class enterpriseDO {
	
	private String e_id;
	private String e_pw;
	private String e_name;
	
	
	
	public enterpriseDO(String e_id, String e_pw) {
		super();
		this.e_id = e_id;
		this.e_pw = e_pw;
	}
	
	
	
	public enterpriseDO(String e_name) {
		super();
		this.e_name = e_name;
	}



	public enterpriseDO(String e_id, String e_pw, String e_name) {
		super();
		this.e_id = e_id;
		this.e_pw = e_pw;
		this.e_name = e_name;
	}



	public String getE_id() {
		return e_id;
	}
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
	public String getE_pw() {
		return e_pw;
	}
	public void setE_pw(String e_pw) {
		this.e_pw = e_pw;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	
	
}
