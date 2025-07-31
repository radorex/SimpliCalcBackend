package com.calc.Calculator.entities;

import org.springframework.stereotype.Component;

@Component
public class MemSave {
	private String memSaveVal;

	public String getMemSaveVal() {
		return memSaveVal;
	}

	public void setMemSaveVal(String memSaveVal) {
		this.memSaveVal = memSaveVal;
	}
	
	
}
