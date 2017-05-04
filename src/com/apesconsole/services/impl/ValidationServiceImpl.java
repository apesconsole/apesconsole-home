package com.apesconsole.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.apesconsole.dto.MetaData;
import com.apesconsole.dto.User;
import com.apesconsole.dto.Bridge;
import com.apesconsole.services.ValidationService;

public class ValidationServiceImpl implements ValidationService {
	
	@Autowired
	public MetaData metaDataConfig;
	
	private enum GroupEnum {
		Admin, Home, Guest
	}
	
	private enum UserEnum {
		sam, sai
	}
	
	private final String bridgeid = "SDFSADFSADFG3254856983465TERTWER";
	private final String bridgename = "apesconsole-bridge";
	private final String key = "secret";
	
	public Bridge getBridge(){
		Bridge bridge = new Bridge();
		bridge.setBridgeid(bridgeid);
		bridge.setBridgename(bridgename);
		bridge.setStaus(true);
		return bridge;
	}
	
	public void validate(final Bridge bridge){
		if(bridge.getBridgeid().equals(bridgeid) && bridge.getKey().equals(key)){
			bridge.setStaus(true);
		}
	}
	
	public boolean validate(final User user){
		GroupEnum groupEnum = null;
		try{
			groupEnum = GroupEnum.valueOf(user.getGroup());
		} catch(Exception e){
			user.setValidationMessage("Invalid User Group !");
		}
		UserEnum userEnum = null;
		try{
			userEnum = UserEnum.valueOf(user.getUserId());
		} catch(Exception e){
			user.setValidationMessage("Invalid User !");
		}			
		switch(groupEnum){
		case Admin:
			switch(userEnum){
				case sam: 
				user.setName("Sam");
				user.setUserLinks(linkSetUp(user));
				if(null != user.getPwd() && "adminuser".equals(user.getPwd())){
					return true;
				} else {
					user.setValidationMessage("Invalid Password !");
					return false;
				}
				default: 
					user.setValidationMessage("Invalid User !");
					return false;
			}
		case Home:
			switch(userEnum){
				case sai: ;
				user.setName("Sai");
				user.setUserLinks(linkSetUp(user));
				if(null != user.getPwd() && "homeuser".equals(user.getPwd())){
					return true;
				} else {
					user.setValidationMessage("Invalid Password !");
					return false;
				}
				default: 
					user.setValidationMessage("Invalid User !");
					return false;
			}
		case Guest:
			user.setName("Guest");
			user.setUserLinks(linkSetUp(user));
			if(null != user.getPwd() && "guestuser".equals(user.getPwd())){
				return true;
			} else {
				user.setValidationMessage("Invalid Password !");
			}
			return false;	
		}
		return false;
	}
	
	public String linkSetUp(final User user){
		GroupEnum groupEnum = GroupEnum.valueOf(user.getGroup());
		StringBuffer links = new StringBuffer();
		boolean activate = true;
		switch(groupEnum){
		case Admin:
			links.append("<li class='active' id='hall' onclick='activate(this.id)' style='font-size: 20px;'><a  href='#'>Drawing Room</a></li>");
			activate = false;
		case Home:
			links.append("<li " + (activate ? "class='active'" : "") + " id='mstrm' onclick='activate(this.id)' style='font-size: 20px;'><a href='#'>Master Room</a></li>");
			activate = false;
		case Guest:
			links.append("<li " + (activate ? "class='active'" : "") + "  id='gstrm' onclick='activate(this.id)' style='font-size: 20px;'><a href='#'>Guest Room</a></li>");
			break;
		}
		return links.toString();
	}
}
