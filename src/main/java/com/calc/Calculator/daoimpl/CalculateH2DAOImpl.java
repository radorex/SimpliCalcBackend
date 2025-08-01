package com.calc.Calculator.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.calc.Calculator.bean.NumToSave;
import com.calc.Calculator.dao.CalculateDAO;
import com.calc.Calculator.entities.MemSave;

@Repository
public class CalculateH2DAOImpl implements CalculateDAO {
	
	private JdbcTemplate jdbcTemplate;
	private String query;
	private float answer;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean saveAnswer(float ans) throws Exception {
		// TODO Auto-generated method stub
		query="update ans set answer = ? where type = 'result';";
		try {
			int rowEffect = jdbcTemplate.update(query,ans);
			if(rowEffect>0) {
				return true;
			}else {
				throw new Exception("Answer couldn't we saved");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public float getAns() {
		// TODO Auto-generated method stub
		query="select answer from ans where type='result';";
		RowMapper<String> answerList = (rs, rowNum) -> {
			return rs.getString(1);
		}; 
		try {
			String ansL = jdbcTemplate.queryForObject(query, answerList);
			answer = Float.valueOf(ansL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}

	@Override
	public float memorySave(NumToSave numS) throws Exception {
		// TODO Auto-generated method stub
		if(userExist(numS)) {
			query="update memsave set val = ? where user_id = (select user_id from users where username=?);";
			int memSuccess = jdbcTemplate.update(query, numS.getNumToSave(), numS.getUserName());
			if(memSuccess>0) {
				return numS.getNumToSave();
			}else {
				throw new Exception("Memory save failed.");
			}
			}else {
				throw new Exception("User not found. Please register!!");
			}
	}
	
	private boolean userExist(NumToSave numS) throws Exception {
		// TODO Auto-generated method stub
		query="select username from users where username=?;";
		RowMapper<MemSave> mapper = (rs, rowNum) -> {
			MemSave ms = new MemSave();
			ms.setUsername(rs.getString(1));
			return ms;
		}; 
		try {
			List<MemSave> valList = jdbcTemplate.query(query, mapper, numS.getUserName());
			if(valList.size()>0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("DB Connctivity issue.");
		}	
	}

	private boolean userExist(String userName) throws Exception {
		query="select username from users where username=?;";
		RowMapper<MemSave> mapper = (rs, rowNum) -> {
			MemSave ms = new MemSave();
			ms.setUsername(rs.getString(1));
			return ms;
		}; 
		try {
			List<MemSave> valList = jdbcTemplate.query(query, mapper, userName);
			if(valList.size()>0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("DB Connctivity issue.");
		}	
	}

	@Override
	public MemSave memCall(String userName) throws Exception {
		// TODO Auto-generated method stub
		MemSave valList = null;
		if(userExist(userName)) {
			query="select val from memsave where user_id = (select user_id from users where username=?);";
			RowMapper<MemSave> mapper = (rs, rowNum) -> {
				MemSave ms = new MemSave();
				ms.setMemSaveVal(rs.getString(1));
				return ms;
			};
			try {
				valList = jdbcTemplate.queryForObject(query, mapper, userName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return valList;
			}else {
				throw new Exception("User not found. Please register!!");
			}
	}

}
