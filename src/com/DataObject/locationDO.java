package com.DataObject;

public class locationDO {
	
	private int order_num;
	private String d_id;
	private int rt_location;
	
	
	
	public locationDO(int order_num, String d_id, int rt_location) {
		super();
		this.order_num = order_num;
		this.d_id = d_id;
		this.rt_location = rt_location;
	}
	
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public int getRt_location() {
		return rt_location;
	}
	public void setRt_location(int rt_location) {
		this.rt_location = rt_location;
	}
	
	

}
