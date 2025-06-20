package com.calc.Calculator.service;

import com.calc.Calculator.bean.num;

public interface Calculate {
	
	public float add(num n);

	public float subs(num num);

	public float multiply(num num);

	public float division(num num);

	public float getAns();

	public float memorySave(float num1) throws Exception;

	public float memCall();
}
