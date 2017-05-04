package com.apesconsole.services;

public interface GpIoService {
	
	public boolean readPinState(final String deviceId);
	public String readCurrentState(final String deviceId);
	public String trigger(final String deviceId);

}
