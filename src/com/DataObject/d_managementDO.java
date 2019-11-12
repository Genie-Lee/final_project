package com.DataObject;

public class d_managementDO {
	
	private String d_id;
	private int rt_distance;
	private int d_count;
	private int date;
	private int profit;
	
	
	
	public d_managementDO(String d_id, int rt_distance, int d_count, int date, int profit) {
		super();
		this.d_id = d_id;
		this.rt_distance = rt_distance;
		this.d_count = d_count;
		this.date = date;
		this.profit = profit;
	}
	
	
	
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public int getRt_distance() {
		return rt_distance;
	}
	public void setRt_distance(int rt_distance) {
		this.rt_distance = rt_distance;
	}
	public int getD_count() {
		return d_count;
	}
	public void setD_count(int d_count) {
		this.d_count = d_count;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	
	

}
