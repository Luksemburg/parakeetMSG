package com.parakeet.msg.web;

import java.sql.Timestamp;

import org.springframework.context.ApplicationContext;

public interface Sender {
	void write();
	void setHelp(DBHelper helper);
}
