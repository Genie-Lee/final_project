package com.DataObject;

public class enter_main_viewerDO {
	
	private String d_name;
	private String d_num;
	private String star_avg;
	private String photo;
	private String txt_m;
	
	public enter_main_viewerDO(String d_name, String d_num, String star_avg, String photo, String txt_m) {
		super();
		this.d_name = d_name;
		this.d_num = d_num;
		this.star_avg = star_avg;
		this.photo = photo;
		this.txt_m = txt_m;
	}
	
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getD_num() {
		return d_num;
	}
	public void setD_num(String d_num) {
		this.d_num = d_num;
	}
	public String getStar_rate() {
		return star_avg;
	}
	public void setStar_avg(String star_avg) {
		this.star_avg = star_avg;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getTxt_m() {
		return txt_m;
	}
	public void setTxt_m(String txt_m) {
		this.txt_m = txt_m;
	}

}
