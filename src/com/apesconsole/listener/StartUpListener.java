package com.apesconsole.listener;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.apesconsole.constants.Constants;
import com.apesconsole.dto.MetaData;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class StartUpListener extends ContextLoaderListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		System.out.println("############ Ape's Console ############");
		System.out.println("Initial Context Set-Up - Start");
		ServletContext context = event.getServletContext();
		Constants.CONTEXT = context;
		Map<String, GpioPinDigitalOutput> deviceMap = new Hashtable<String, GpioPinDigitalOutput>();
		context.setAttribute("deviceMap", deviceMap);
		ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		MetaData meta = (MetaData)applicationContext.getBean("metaDataConfig");
		System.out.println("Group List - >"+meta.getGroupList());
		StringBuffer groupList = new StringBuffer();
		for(String group: meta.getGroupList().split(",")){
			groupList.append("<option value='" + group + "'>" + group +"</option>");
		}
		context.setAttribute("group-list", groupList.toString());
		System.out.println("Initial Context Set-Up - Complete");
		setUpPi();
		
		System.out.println("Initial Pi Set-Up - Complete");
		System.out.println("#######################################");
	}
	
	private void setUpPi(){
		try{
			System.out.println("GPIO - Pin Set Up Started");
			final GpioController gpio = GpioFactory.getInstance();
			final GpioPinDigitalOutput hall_light_1_pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, Constants.HALL_LIGHT_1, PinState.LOW);
			final GpioPinDigitalOutput mstrm_light1_pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, Constants.MSTRM_LIGHT_1, PinState.LOW);
			final GpioPinDigitalOutput gstrm_light1_pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, Constants.GSTRM_LIGHT_1, PinState.LOW);
			hall_light_1_pin.low();
			mstrm_light1_pin.low();
			gstrm_light1_pin.low();
			hall_light_1_pin.setShutdownOptions(true, PinState.LOW);
			mstrm_light1_pin.setShutdownOptions(true, PinState.LOW);
			gstrm_light1_pin.setShutdownOptions(true, PinState.LOW);
			Map<String, GpioPinDigitalOutput> deviceMap = (Map<String, GpioPinDigitalOutput>)Constants.CONTEXT.getAttribute("deviceMap");
			deviceMap.put(Constants.HALL_LIGHT_1, hall_light_1_pin);
			deviceMap.put(Constants.MSTRM_LIGHT_1, mstrm_light1_pin);
			deviceMap.put(Constants.GSTRM_LIGHT_1, gstrm_light1_pin);
			System.out.println("GPIO - Pin Set Up Completed");
			
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("############ Ape's Console ############");
		System.out.println("System Shut Down");
		final GpioController gpio = GpioFactory.getInstance();
		gpio.shutdown();
		System.out.println("############ Ape's Console ############");
	}
}
