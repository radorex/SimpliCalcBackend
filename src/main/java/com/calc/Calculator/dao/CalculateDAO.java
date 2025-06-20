package com.calc.Calculator.dao;

public interface CalculateDAO {

	public boolean saveAnswer(float ans);

	public float getAns();

	public float memorySave(float num1) throws Exception;

	public float memCall();
}
