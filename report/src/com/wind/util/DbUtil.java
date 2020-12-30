/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wind.util;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 *
 * @author follow
 */
public class DbUtil {
    
    private DbUtil(){
        
    }
    
    /**
     * 获取数据库连接
     * @return 
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/test?"
                    + "useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "123456");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    /**
     * 解析表关联实体模型
     * @param rs
     * @param c
     * @return
     */
    public static <T> T parseTable(ResultSet rs, Class<T> c){
        T entity = null;
		try {
			entity = c.newInstance();
			Field[] fields = c.getDeclaredFields();
			final T tmp = entity;
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			Map<String, String> colMap = new HashMap<>(16);
			Set<String> colList = new HashSet<>();
			IntStream.range(0, count).forEach(i -> {
				try {
					String colName = md.getColumnName(i + 1);
					String camelName = getCamelCase(colName, false);
					colList.add(camelName);
					colMap.put(camelName, colName);
				} catch (SQLException e1) {	
					//e1.printStackTrace();
				}
			});
			//System.out.println(colMap);
	        Arrays.asList(fields).stream().filter(f -> colList.contains(f.getName())).forEach(f -> {
	        	String name = f.getName();
	        	String colName = colMap.getOrDefault(name, name);
	        	try {
	        		Object obj = rs.getObject(colName);
	        		Object val = obj != null ? obj : rs.getObject(name);
	        		if(val != null){
	        			f.setAccessible(true);
						f.set(tmp, val);
	        		}		
				} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
					//e.printStackTrace();
				}
	        });
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
        return entity;
    }
    
    
    /**
     * 执行查询语句
     * @param sql
     * @param psBack
     */
    public static <R> List<R> executeQuery(String sql, PsCallBack call, Class<R> c){
        List<R> list = new ArrayList<>();
        Connection conn = DbUtil.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if(call != null){
            	call.done(ps);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
            	list.add(parseTable(rs, c));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn);
        }

        return list;
    }
    /**
     * 
     * @param sql
     * @param psBack
     * @return
     */
    public static int executeUpdate(String sql, PsCallBack call){
        Connection conn = DbUtil.getConnection();
        int i = -1;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if(call != null){
            	call.done(ps);
            }
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn);
        }
        return i;
    }
    
    /**
     * 关闭连接
     * @param conn 
     */
    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * 驼峰命名 hello_world => HelloWorld
     * @param name 数据库列名
     * @param isFirst 首字母小写为false helloWorld， 大写为true HelloWorld
     * @return
     */
    public static String getCamelCase(String name, boolean isFirst){
        StringBuilder sb = new StringBuilder();
        if(name != null && !"".equals(name)){
            String[] strArr = name.split("_");
            for(int i = 0; i < strArr.length; i++){
                String s = strArr[i];
                if(i == 0){
                    sb.append(getCap(s, isFirst));
                }else{
                    sb.append(getCap(s, true));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 将单词首字母变大小写
     * @param str
     * @param flag true变大写， false变小写
     * @return
     */
    public static String getCap(String str, boolean flag){
        StringBuilder sb = new StringBuilder();
        int length = str != null ? str.length() : 0;
        if(length >= 1){
            if(flag){
                sb.append(str.substring(0, 1).toUpperCase());
            }else{
                sb.append(str.substring(0, 1).toLowerCase());
            }
            if(length > 1){
                sb.append(str.substring(1));
            }
        }
        return sb.toString();
    }
    
    @FunctionalInterface
    public interface PsCallBack {

        /**
         * call back
         * @param ps
         * @throws SQLException
         */
        void done(PreparedStatement ps) throws SQLException;
    }
}
