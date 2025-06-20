package com.calc.Calculator.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class calcUtil {

	private calcUtil() {
		
	}
	
	public static boolean saveAnswer(float ans) {
		boolean success = false;
		
		String query="update ans set answer = "+ans+" where type = 'result';";
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/calc", "root", "tiger");
			System.out.println("Connection established successfully!");
			Statement statement = connection.createStatement();
			int ra = statement.executeUpdate(query);
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
}
