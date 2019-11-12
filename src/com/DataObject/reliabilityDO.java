package com.DataObject;

public class reliabilityDO {
	
	private String d_id;
	private String r_assessment;
	private float star_avg;
	private String p_process;
	
	
	
	public reliabilityDO(String d_id, String r_assessment, float star_avg, String p_process) {
		super();
		this.d_id = d_id;
		this.r_assessment = r_assessment;
		this.star_avg = star_avg;
		this.p_process = p_process;
	}
	
	
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public String getR_assessment() {
		return r_assessment;
	}
	public void setR_assessment(String r_assessment) {
		this.r_assessment = r_assessment;
	}
	public float getStar_avg() {
		return star_avg;
	}
	public void setStar_avg(float star_avg) {
		this.star_avg = star_avg;
	}
	public String getP_process() {
		return p_process;
	}
	public void setP_process(String p_process) {
		this.p_process = p_process;
	}
	
	

}
