package com.ait.tea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper
{
	
	private String url;


	public Connection getConnection() throws Exception {
		String driver = null;
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			url = "jdbc:mysql://localhost/winedb?user=root";
			url = "jdbc:mysql://localhost:3306/winedb?user=root&password=123456&serverTimezone=UTC&useSSL=false";
          //  driver="com.mysql.jdbc.Driver";
			driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            connection=DriverManager.getConnection(url);
            return connection;
		} catch (Exception e) {
			throw e ;
		}
		
	}
	
	public void close(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
