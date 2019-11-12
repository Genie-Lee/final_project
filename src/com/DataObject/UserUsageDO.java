package com.DataObject;

public class UserUsageDO {

	//user_usageDetails 을 얻기 위한 order_num , d_name , b_name , date  
	
	private int order_num;
	private String d_name;
	private String b_name;
	private String date;
	private String photo;
	
	
	public UserUsageDO(int order_num, String d_name, String b_name, String date, String photo) {
	
		super() ;
this.order_num = order_num ; 
this.d_name = d_name; 
this.b_name = b_name ; 
this.date = date ; 
this.photo = photo ; 
		 
		
	}
	
	
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
}
