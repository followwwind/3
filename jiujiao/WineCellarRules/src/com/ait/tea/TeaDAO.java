package com.ait.tea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class TeaDAO {
	ConnectionHelper connectionHelper;
	Connection connection;
	public TeaDAO(ConnectionHelper connectionHelper) {
		this.connectionHelper=connectionHelper;
	}

    public List<Tea> findAll() {
        List<Tea> list = new ArrayList<Tea>();
        Connection connection = null;
    	String sql = "SELECT * FROM tea ORDER BY name";
        try {
            connection= connectionHelper.getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			connectionHelper.close(connection);
		}
        return list;
    }
    
    public List<Tea> findByName(String name) {
        List<Tea> list = new ArrayList<Tea>();
        Connection connection = null;
    	String sql = "SELECT * FROM tea where name like '%" + name + "%' ORDER BY name";
        try {
            connection= connectionHelper.getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			connectionHelper.close(connection);
		}
        return list;
    }

    
    public Tea create(Tea tea) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
        	 connection= connectionHelper.getConnection();
            ps = connection.prepareStatement("INSERT INTO tea (name, grapes, country, year, create_time, region) VALUES (?, ?, ?, ?, ?, ?)",
               new String[] { "ID" });
         //   ps = connection.prepareStatement("INSERT INTO tea (name, grapes, country, year) VALUES (?, ?, ?, ?)");
            ps.setString(1, tea.getName());
            ps.setString(2, tea.getGrapes());
            ps.setString(3, tea.getCountry());
            ps.setString(4, tea.getYear());
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
	    ps.setString(6, tea.getRegion());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            tea.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			connectionHelper.close(connection);
		}
        return tea;
    }
    
    public Tea update(Tea tea) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
        	 connection= connectionHelper.getConnection();
            ps = connection.prepareStatement("update tea set name = ?, grapes = ?, country = ?, year = ?, region = ? where id = ?");
            ps.setString(1, tea.getName());
            ps.setString(2, tea.getGrapes());
            ps.setString(3, tea.getCountry());
            ps.setString(4, tea.getYear());
	    ps.setString(5, tea.getRegion());
            ps.setInt(6, tea.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			connectionHelper.close(connection);
		}
        return tea;
    }
    
    
    public int delete(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        int i = 0;
        try {
        	 connection= connectionHelper.getConnection();
            ps = connection.prepareStatement("delete from tea where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			connectionHelper.close(connection);
		}
        return i;
    }

   
    protected Tea processRow(ResultSet rs) throws SQLException {
        Tea tea = new Tea();
        tea.setId(rs.getInt("id"));
        tea.setName(rs.getString("name"));
        tea.setGrapes(rs.getString("grapes"));
        tea.setCountry(rs.getString("country"));
        tea.setYear(rs.getString("year"));
        tea.setRegion(rs.getString("region"));
        tea.setCreateTime(rs.getTimestamp("create_time"));
        return tea;
    }
    
}
