package com.calc.Calculator.bean;

public class NumToSave {

	private String userName;
	private float numToSave;
	
	public NumToSave(String userName, float numToSave) {
		super();
		this.userName = userName;
		this.numToSave = numToSave;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public float getNumToSave() {
		return numToSave;
	}

	public void setNumToSave(float numToSave) {
		this.numToSave = numToSave;
	}

	@Override
	public String toString() {
		return "num [userName=" + userName + ", numToSave=" + numToSave + "]";
	}
}

