package com.DataObject;

public class reviewDO {
	
	private int post_num;
	private String u_id;
	private String order_num;
	private int r_date;
	private String star_rate;
	private String post;
	
	
	
	public reviewDO(int post_num, String u_id, String order_num, int r_date, String star_rate, String post) {
		super();
		this.post_num = post_num;
		this.u_id = u_id;
		this.order_num = order_num;
		this.r_date = r_date;
		this.star_rate = star_rate;
		this.post = post;
	}
	
	
	public reviewDO(String u_id, String order_num, String star_rate, String post) {
		this.u_id = u_id;
		this.order_num = order_num;
		this.star_rate = star_rate;
		this.post = post;
	}


	public reviewDO(String star_rate, String post) {
		this.star_rate = star_rate;
		this.post = post;
	}


	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public int getR_date() {
		return r_date;
	}
	public void setR_date(int r_date) {
		this.r_date = r_date;
	}
	public String getStar_rate() {
		return star_rate;
	}
	public void setStar_rate(String star_rate) {
		this.star_rate = star_rate;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	

}
