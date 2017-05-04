package com.apesconsole.services;

import com.apesconsole.dto.Bridge;
import com.apesconsole.dto.User;

public interface ValidationService {
	public Bridge getBridge();
	public void validate(final Bridge bridge);
	public boolean validate(final User user);
}
