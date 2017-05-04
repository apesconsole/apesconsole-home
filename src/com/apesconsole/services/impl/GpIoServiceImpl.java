package com.apesconsole.services.impl;

import java.util.Map;

import com.apesconsole.constants.Constants;
import com.apesconsole.services.GpIoService;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class GpIoServiceImpl implements GpIoService {

	public boolean readPinState(final String deviceId) {
		Map<String, GpioPinDigitalOutput> deviceMap = (Map<String, GpioPinDigitalOutput>)Constants.CONTEXT.getAttribute("deviceMap");
		if(deviceMap.containsKey(deviceId)){
			final GpioPinDigitalOutput pin = deviceMap.get(deviceId);
			return pin.isHigh() || pin.getState().equals(PinState.HIGH);
		}
		return false;
	}
	
	public String readCurrentState(final String deviceId) {
		String status = "3";
		Map<String, GpioPinDigitalOutput> deviceMap = (Map<String, GpioPinDigitalOutput>)Constants.CONTEXT.getAttribute("deviceMap");
		if(deviceMap.containsKey(deviceId)){
			final GpioPinDigitalOutput pin = deviceMap.get(deviceId);
			status = pin.isHigh() || pin.getState().equals(PinState.HIGH)  ? "1" : "2";
		}
		return status;
	}

	public String trigger(final String deviceId) {
		String status = "3";
		Map<String, GpioPinDigitalOutput> deviceMap = (Map<String, GpioPinDigitalOutput>)Constants.CONTEXT.getAttribute("deviceMap");
		if(deviceMap.containsKey(deviceId)){
			final GpioPinDigitalOutput pin = deviceMap.get(deviceId);
			if(pin.isHigh()){
				pin.low();
				pin.setState(PinState.LOW);
				System.out.println("GPIO - IF - " + (pin.isHigh() ? "ON" : "OFF"));
				status = "2";
			} else {
				pin.high();
				pin.setState(PinState.HIGH);
				System.out.println("GPIO - ELSE - " + (pin.isHigh() ? "ON" : "OFF"));
				status = "1";
			}
		}
		return status;
	}


}
