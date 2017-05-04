package com.apesconsole.dto;

public class User {

	private String userId;
	private String group;
	private String name;
	private String pwd;
	private String userLinks;
	private String validationMessage;
	
	public String getValidationMessage() {
		return validationMessage;
	}
	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserLinks() {
		return userLinks;
	}
	public void setUserLinks(String userLinks) {
		this.userLinks = userLinks;
	}
}
