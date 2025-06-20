package com.calc.Calculator.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.calc.Calculator.dao.CalculateDAO;

@Repository
public class CalculateDAOImpl implements CalculateDAO {

	Connection connection=null;
	String query;
	float answer=0;
	
	@Override
	public boolean saveAnswer(float ans) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean update(String stmt) {
		
		boolean success = false;
		
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/calc", "root", "tiger");
			System.out.println("Connection established successfully!");
			Statement statement = connection.createStatement();
			int ra = statement.executeUpdate(stmt);
			if(ra>0) {
				success=true;
			}
			System.out.println("success is "+success);
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(connection!=null) {
				try {
					connection.close();
					System.out.println();
				} catch (SQLException e2) {
					// TODO: handle exception
					System.err.println("Error closing the connection: " + e2.getMessage());
				}
			}
		}
		
		return success;
	}
	
	private ResultSet select(String stmt) {
		Connection connection=null;
		ResultSet resultSet=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/calc", "root", "tiger");
			System.out.println("Connection established successfully!");
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(stmt);
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
		
	}

	@Override
	public float getAns() {
		// TODO Auto-generated method stub
		query="select answer from ans where type='result';";
		ResultSet resultSet=select(query);
		try {
			resultSet.next();
			answer = resultSet.getFloat("answer");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(connection!=null) {
				try {
					connection.close();
					System.out.println();
				} catch (SQLException e2) {
					// TODO: handle exception
					System.err.println("Error closing the connection: " + e2.getMessage());
				}
			}
		}
		return answer;
	}

	@Override
	public float memorySave(float num1) throws Exception {
		// TODO Auto-generated method stub
		query="update ans set answer = "+num1+" where type = 'mem1';";
		boolean memSuccess = update(query);
		if(memSuccess) {
			return num1;
		}else {
			throw new Exception("Memory save failed.");
		}
	}

	@Override
	public float memCall() {
		// TODO Auto-generated method stub
		query="select answer from ans where type='mem1';";
		ResultSet resultSet=select(query);
		try {
			resultSet.next();
			answer = resultSet.getFloat("answer");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(connection!=null) {
				try {
					connection.close();
					System.out.println();
				} catch (SQLException e2) {
					// TODO: handle exception
					System.err.println("Error closing the connection: " + e2.getMessage());
				}
			}
		}
		return answer;
	}

}
