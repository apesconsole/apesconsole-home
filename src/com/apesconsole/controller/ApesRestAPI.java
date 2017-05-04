package com.apesconsole.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.apesconsole.dto.Status;
import com.apesconsole.services.GpIoService;
import com.apesconsole.services.ValidationService;

/**
 * @author snath44
 *
 */
@Controller
@RequestMapping("/appaccess")
public class ApesRestAPI {
	
	@Autowired
	private ValidationService validationService;
	
	@Autowired
	private GpIoService gpIoService;
    
    private final List<Room> loadHomeList(){
    	List<Room> roomList = new ArrayList<Room>();
    	Room hall = new Room();
		hall.setId(1);
		hall.setTitle("Hall");
		hall.setIcon("hall");
		List<Device> hallDeviceList = new ArrayList<Device>();
		Device hallLight = new Device();
		hallLight.setId(Constants.HALL_LIGHT_1);
		hallLight.setTitle("Light");
		hallLight.setStatus(gpIoService.readPinState(Constants.HALL_LIGHT_1));
		Device hallAC = new Device();
		hallAC.setTitle("AC");
		hallAC.setId("random-1");
		Device hallMusic = new Device();
		hallMusic.setTitle("Music");
		hallMusic.setId("random-2");
		hallDeviceList.add(hallLight);
		hallDeviceList.add(hallAC);
		hallDeviceList.add(hallMusic);
		hall.setDeviceList(hallDeviceList);
		
		Room master = new Room();	
		master.setId(2);
		master.setTitle("Master");
		master.setIcon("master");
		List<Device> masterDeviceList = new ArrayList<Device>();
		Device masterlight = new Device();
		masterlight.setId(Constants.MSTRM_LIGHT_1);
		masterlight.setTitle("Light");
		hallLight.setStatus(gpIoService.readPinState(Constants.MSTRM_LIGHT_1));
		Device masterAC = new Device();
		masterAC.setId("random-3");
		masterAC.setTitle("AC");
		Device masterMusic = new Device();
		masterMusic.setId("random-4");
		masterMusic.setTitle("Music");
		Device masterHeater = new Device();
		masterHeater.setId("random-5");
		masterHeater.setTitle("Heater");
		masterDeviceList.add(masterlight);
		masterDeviceList.add(masterAC);
		masterDeviceList.add(masterMusic);
		masterDeviceList.add(masterHeater);
		master.setDeviceList(masterDeviceList);
		
		Room guest = new Room();	
		guest.setId(3);
		guest.setTitle("Guest");
		guest.setIcon("guest");
		List<Device> guestDeviceList = new ArrayList<Device>();
		Device questlight = new Device();
		questlight.setId(Constants.GSTRM_LIGHT_1);
		hallLight.setStatus(gpIoService.readPinState(Constants.GSTRM_LIGHT_1));
		guest.setDeviceList(guestDeviceList);
		
		roomList.add(hall);
		roomList.add(master);
		roomList.add(guest);
		return roomList;
    }
    
    private enum ActionEnum {
    	searchbridge, validate, click, fetch, roomlist, devicelist
    }
    
    /*
     * Open To All
     */
    @RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Object appAccess(ModelAndView model, HttpServletRequest request, HttpServletResponse response) {
    	Object json = null;
    	if(null != request.getParameter("action")){
    		String deviceId = (String)request.getParameter("deviceId");
    		ActionEnum action = ActionEnum.valueOf(request.getParameter("action"));
    		switch(action){
    		case searchbridge:
    			return validationService.getBridge();
    		case validate:
    			if(null != request.getParameter("id") && null != request.getParameter("key")){
    				Bridge bridge = new Bridge();
    				bridge.setBridgeid((String) request.getParameter("id"));
    				bridge.setKey((String) request.getParameter("key"));
    				validationService.validate(bridge);
    				return bridge;
    			}
    		case roomlist: 
    			return loadHomeList();
    		case click: 
    			if(null != request.getParameter("deviceId")){
    				gpIoService.trigger(deviceId);
    				Status status = new Status();
    				status.setStatus(gpIoService.readPinState(deviceId));
    				return status;
    			}
    			break;
    		case fetch: 
    			if(null != request.getParameter("deviceId")){
    				Status status = new Status();
    				status.setStatus(gpIoService.readPinState(deviceId));
    				return status;
    			}
    		}
    	} 
	    return json;
	}
	

}
