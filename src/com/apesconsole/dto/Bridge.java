package com.apesconsole.dto;

public class Bridge {

	private String bridgeid;
	private String bridgename;
	private String key;
	private boolean staus = false;
	
	public String getBridgeid() {
		return bridgeid;
	}
	public void setBridgeid(String bridgeid) {
		this.bridgeid = bridgeid;
	}
	public String getBridgename() {
		return bridgename;
	}
	public void setBridgename(String bridgename) {
		this.bridgename = bridgename;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean isStaus() {
		return staus;
	}
	public void setStaus(boolean staus) {
		this.staus = staus;
	}
	
	
}
