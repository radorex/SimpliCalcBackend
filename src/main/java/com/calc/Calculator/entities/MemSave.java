package com.calc.Calculator.entities;

import org.springframework.stereotype.Component;

@Component
public class MemSave {
	
	private String username;
	private String memSaveVal;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMemSaveVal() {
		return memSaveVal;
	}

	public void setMemSaveVal(String memSaveVal) {
		this.memSaveVal = memSaveVal;
	}
	
	
}
