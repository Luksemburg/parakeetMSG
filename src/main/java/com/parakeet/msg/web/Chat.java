package com.parakeet.msg.web;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface Chat {
	void setHost(String host);
	void setClient(String client);
	void setMessage(String message);
	void setDateTime(Timestamp timestamp);
	
	String getHost();
	String getClient();
	String getMessage();
	Timestamp getDateTime();
}
