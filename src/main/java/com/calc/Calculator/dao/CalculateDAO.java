package com.calc.Calculator.dao;

import com.calc.Calculator.bean.NumToSave;
import com.calc.Calculator.entities.MemSave;

public interface CalculateDAO {

	public boolean saveAnswer(float ans) throws Exception;

	public float getAns();

	public float memorySave(NumToSave numS) throws Exception;

	public MemSave memCall(String userName) throws Exception;
}
