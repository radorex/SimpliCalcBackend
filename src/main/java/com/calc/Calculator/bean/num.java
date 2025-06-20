package com.calc.Calculator.bean;


public class num {
	private float num1;
	private float num2;
	
	public num(float num1, float num2) {
		super();
		this.num1 = num1;
		this.num2 = num2;
	}
	
	public float getNum1() {
		return num1;
	}
	
	public void setNum1(float num1) {
		this.num1 = num1;
	}
	
	public float getNum2() {
		return num2;
	}
	
	public void setNum2(float num2) {
		this.num2 = num2;
	}
	
	@Override
	public String toString() {
		return "num [num1=" + num1 + ", num2=" + num2 + "]";
	}
	
	
}
