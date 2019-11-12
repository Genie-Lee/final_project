package com.DataObject;

public class driverDO {

	private String d_id;
	private String d_pw;
	private String d_name;
	private String d_num;
	private String e_id;
	private String photo;
	
	
	
	public driverDO(String d_id, String d_pw) {
		super();
		this.d_id = d_id;
		this.d_pw = d_pw;
	}


/*	public driverDO(String d_name, String d_num, String e_id, String photo) {
		super();
		this.d_name = d_name;
		this.d_num = d_num;
		this.e_id = e_id;
		this.photo = photo;
	}
*/


	public driverDO(String d_id, String d_pw, String d_name, String d_num, String e_id, String photo) {
		super();
		this.d_id = d_id;
		this.d_pw = d_pw;
		this.d_name = d_name;
		this.d_num = d_num;
		this.e_id = e_id;
		this.photo = photo;
	}
	
	
	
	public driverDO(String photo, String d_id, String d_name, String d_num) {
		super();
		this.d_id = d_id;
		this.d_name = d_name;
		this.d_num = d_num;
		this.photo = photo;
	}



	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public String getD_pw() {
		return d_pw;
	}
	public void setD_pw(String d_pw) {
		this.d_pw = d_pw;
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
	public String getE_id() {
		return e_id;
	}
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
}
