package com.DataObject;

public class orderDO {

	private int order_num;
	private String d_id;
	private String u_id;
	private int b_num;
	private String destination;
	private int estimated_time;
	private String date; 
	
	
	
	public orderDO(int order_num, String d_id, String u_id, int b_num, String destination, int estimated_time , String date) {
		super();
		this.order_num = order_num;
		this.d_id = d_id;
		this.u_id = u_id;
		this.b_num = b_num;
		this.destination = destination;
		this.estimated_time = estimated_time;
		this.date = date;
		
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
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getEstimated_time() {
		return estimated_time;
	}
	public void setEstimated_time(int estimated_time) {
		this.estimated_time = estimated_time;
	}
	
	public String date() {
		
		return date; 
	}
	
	public void setDate(String date) {
		this.date = date;
		
	}
	
	
}
