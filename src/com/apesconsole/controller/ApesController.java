/**
 * 
 */
package com.apesconsole.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.apesconsole.constants.Constants;
import com.apesconsole.dto.Bridge;
import com.apesconsole.dto.Device;
import com.apesconsole.dto.Room;
import com.apesconsole.dto.User;
import com.apesconsole.dto.Status;
import com.apesconsole.services.GpIoService;
import com.apesconsole.services.ValidationService;
/**
 * @author snath44
 *
 */
@Controller
public class ApesController {
	
	@Autowired
	private ValidationService validationService;
	
	@Autowired
	private GpIoService gpIoService;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model, HttpServletRequest request) {
	    return prepareDropDown(model, request);
	}
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model, HttpServletRequest request) {
    	return prepareDropDown(model, request);
	}
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(ModelAndView model, HttpServletRequest request) {
    	prepareDropDown(model, request);
	    request.getSession().removeAttribute("user");
	    request.getSession().invalidate();
	    return model;
	}
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView validate(ModelAndView model, HttpServletRequest request) {
    	if(StringUtils.isNotBlank(request.getParameter("userId"))){
    		User user = new User();
    		user.setUserId(request.getParameter("userId"));
        	if(StringUtils.isNotBlank(request.getParameter("password"))){
        		user.setPwd(request.getParameter("password"));
            	if(StringUtils.isNotBlank(request.getParameter("group"))){
            		user.setGroup(request.getParameter("group"));
                	if(validationService.validate(user)){
                		request.getSession().setAttribute("user", user);
                		model.setViewName("redirect:home.html");
                		return model;
                	}
            	}
        	}
    	} 
	    return prepareDropDown(model, request);
	}
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView model, HttpServletRequest request) {
    	if(null == request.getSession().getAttribute("user")){
    		model.setViewName("redirect:login.html");
    		return model;
    	}
    	model.setViewName("home");
	    return model;
	}
    
    private enum ActionEnum {
    	click, fetch
    }
    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
	public ModelAndView ajax(ModelAndView model, HttpServletRequest request, HttpServletResponse response) {
    	if(null == request.getSession().getAttribute("user")){
    		model.setViewName("redirect:login.html");
    		return model;
    	}
    	String status = "3";
    	if(null != request.getParameter("deviceId") && null != request.getParameter("action")){
    		String deviceId = (String)request.getParameter("deviceId");
    		ActionEnum action = ActionEnum.valueOf(request.getParameter("action"));
    		switch(action){
    		case click: status = gpIoService.trigger(deviceId);
    			break;
    		case fetch: status = gpIoService.readCurrentState(deviceId);
    		}
    	} 
		try{
			response.getWriter().print(status);
		} catch(Exception e){
			
		}
	    return null;
	}

    private ModelAndView prepareDropDown(ModelAndView model, HttpServletRequest request){
    	model.setViewName("login");
	    model.addObject("groupList", Constants.CONTEXT.getAttribute("group-list"));
	    return model;
    }
}
