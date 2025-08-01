package com.calc.Calculator.serviceImpl;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.calc.Calculator.bean.NumToSave;
import com.calc.Calculator.bean.num;
import com.calc.Calculator.dao.CalculateDAO;
import com.calc.Calculator.entities.MemSave;
import com.calc.Calculator.service.Calculate;
import com.calc.Calculator.utilities.calcUtil;

@Service
public class CalculateImpl implements Calculate {
	
	@Autowired
	@Qualifier("calculateH2DAOImpl")
	CalculateDAO calculateDAO;
	
	private float result;

	@Override
	public float add(num n) {
		// TODO Auto-generated method stub
		result=n.getNum1()+n.getNum2();
		saveAns(result);
		return result;
	}

	@Override
	public float subs(num n) {
		// TODO Auto-generated method stub
		result=n.getNum1()-n.getNum2();
		saveAns(result);
		return result;
	}

	@Override
	public float multiply(num n) {
		// TODO Auto-generated method stub
		result=n.getNum1()*n.getNum2();
		saveAns(result);
		return result;
	}

	@Override
	public float division(num n) {
		// TODO Auto-generated method stub
		float val1=n.getNum1();
		float val2=n.getNum2();
		
		try {
		result = val1/val2;
		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		saveAns(result);
		return result;
	}
	
	private void saveAns(float ans) {
		boolean success=calcUtil.saveAnswer(ans);
		boolean s1 = false;
		try {
			s1 = calculateDAO.saveAnswer(ans);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(success==true&&s1==true) {
			System.out.println("Answer Saved Successfully");
		}else {
			System.out.println("Answer Saving Failed");
		}
	}

	@Override
	public float getAns() {
		// TODO Auto-generated method stub
		return calculateDAO.getAns();
	}

	@Override
	public float memorySave(NumToSave numS) throws Exception {
		// TODO Auto-generated method stub
		return calculateDAO.memorySave(numS);
	}

	@Override
	public MemSave memCall(String userName) throws Exception {
		// TODO Auto-generated method stub
		return calculateDAO.memCall(userName);
	}

}
