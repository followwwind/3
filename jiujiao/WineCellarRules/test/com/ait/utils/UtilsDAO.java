package com.ait.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import com.ait.tea.Tea;

public class UtilsDAO {

	public void resetTable(List<Tea> teas) throws Exception {
		String driver = null;
		Connection connection = null;
		Statement statement = null;
		PreparedStatement ps = null;
		String url;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost/teadb?user=root";
			// driver="com.mysql.jdbc.Driver";
			driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
			connection = DriverManager.getConnection(url);
			String query = "TRUNCATE TABLE tea";
			statement = connection.createStatement();
			statement.execute(query);
			for (Tea tea : teas) {

				ps = connection.prepareStatement("INSERT INTO tea (name, grapes, country, year) VALUES (?, ?, ?, ?)",
						new String[] { "ID" });
				ps.setString(1, tea.getName());
				ps.setString(2, tea.getGrapes());
				ps.setString(3, tea.getCountry());
				ps.setString(4, tea.getYear());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			throw e;
		}

	}

}

