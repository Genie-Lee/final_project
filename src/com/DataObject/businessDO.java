package com.DataObject;

public class businessDO {
	
	private int b_num;
	private String b_name;
	private String b_address;
	
	
	
	public businessDO(int b_num, String b_name, String b_address) {
		super();
		this.b_num = b_num;
		this.b_name = b_name;
		this.b_address = b_address;
	}
	
	
	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
		
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getB_address() {
		return b_address;
	}
	public void setB_address(String b_address) {
		this.b_address = b_address;
	}
	
	

}
