package com.DataObject;

public class review_viewerDO {
	private String d_name;
	private String photo;
	private String r_date;
	private String star_rate;
	private String post;
	
	public review_viewerDO(String d_name, String photo, String r_date, String star_rate, String post) {
		super();
		this.d_name = d_name;
		this.r_date = r_date;
		this.star_rate = star_rate;
		this.post = post;
		this.photo = photo;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public String getR_date() {
		return r_date;
	}

	public void setR_date(String r_date) {
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
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
