package com.apesconsole.constants;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;

import com.pi4j.io.gpio.GpioPinDigitalOutput;


public class Constants {
	public static ServletContext CONTEXT = null;
	public static final String HALL_LIGHT_1 = "hall-light1";
	public static final String MSTRM_LIGHT_1 = "mstrm-light1";
	public static final String GSTRM_LIGHT_1 = "gstrm-light1";
	public static Map<String, GpioPinDigitalOutput> deviceMap = new Hashtable<String, GpioPinDigitalOutput>();
}
