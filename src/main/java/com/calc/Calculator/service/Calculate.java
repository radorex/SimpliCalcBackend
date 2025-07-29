package com.calc.Calculator.service;

import com.calc.Calculator.bean.NumToSave;
import com.calc.Calculator.bean.num;
import com.calc.Calculator.entities.MemSave;

public interface Calculate {
	
	public float add(num n);

	public float subs(num num);

	public float multiply(num num);

	public float division(num num);

	public float getAns();

	public float memorySave(NumToSave numS) throws Exception;

	public MemSave memCall(String userName) throws Exception;
}
