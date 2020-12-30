package com.wind.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import com.wind.dao.UserDao;
import com.wind.dao.base.BaseDaoImpl;
import com.wind.dao.callback.PsBack;
import com.wind.entity.User;
import com.wind.entity.base.Page;

/**
 * 用户表 dao接口实现
 * @author wind
 */
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao{

    @Override
    public int insert(User r) {
        String sql = "insert into user values (?, ?, ?)";
        if(r == null){
            return -1;
        }
        return executeUpdate(sql, ps -> {
            ps.setInt(1, r.getId() != null ? r.getId() : 0);
            ps.setString(2, r.getUsername());
            ps.setString(3, r.getPassword());
        });
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "delete from user where id = ?";
        return executeUpdate(sql, ps -> ps.setInt(1, id));
    }

    @Override
    public int deleteByCondition(User r){
        String joinSql = "";
        if(r != null){
            joinSql += joinSql(r, " and ", "");
        }
        int i = -1;
        if(!"".equals(joinSql)){
            String sql = "delete from user where 1=1" + joinSql;
            return executeUpdate(sql, ps -> {});
        }
        return i;
    }



    @Override
    public User findById(Integer id) {
        String sql = "select * from user where id = ?";
        List<User> entitys = executeQuery(sql, ps -> ps.setInt(1, id));
        if(entitys.size() == 1){
            return entitys.get(0);
        }
        return null;
    }

    @Override
    public List<User> findByCondition(User r) {
        String sql = "select * from user where 1 = 1 ";
        String joinSql = "";
        if(r != null){
            joinSql += joinSql(r, " and ", "");
        }
        sql += joinSql;
        return executeQuery(sql, ps -> {});
    }

    @Override
    public void findPageList(User r, Page page){
        String sql = "select count(1) from user where 1 = 1 ";
        String joinSql = "";
        if(r != null){
            joinSql += joinSql(r, " and ", "");
        }
        sql += joinSql;
        int totalCount =  countQuery(sql, ps -> {});
        sql += " limit " + page.getStartRow() + ", " + page.getLineNumber();
        List<User> entitys =  executeQuery(sql.replace("count(1)", "*"), ps -> {});
        page.setResult(entitys);
    }

    @Override
    public int updateByCondition(User r) {
        String sql = "update user ";
        if(r == null){
            return -1;
        }
        String setSql = "set " + joinSql(r, "", ", ");
        Integer id = r.getId();
        if(id != null && setSql.contains(", ")){
            sql += setSql.substring(0, setSql.length() - 2);
            sql += " where id = ?";
            return executeUpdate(sql, ps -> ps.setInt(1, id));
        }
        return -1;
    }

    @Override
    public int countByCondition(User r){
        String sql = "select count(1) from user where 1 = 1 ";
        String joinSql = "";
        if(r != null){
            joinSql += joinSql(r, " and ", "");
        }
        sql += joinSql;
        return countQuery(sql, ps -> {});
    }

    @Override
    public User parseTable(ResultSet rs) throws SQLException {
        User entity = null;
        if(rs != null) {
            entity = new User();
            entity.setId(rs.getInt("id"));
            entity.setUsername(rs.getString("username"));
            entity.setPassword(rs.getString("password"));
        }
        return entity;
    }

    @Override
    public String joinSql(User r, String prefix, String suffix){
        String joinSql = "";
        Integer id = r.getId();
        if(id != null){
            joinSql += prefix + "id = " + id + suffix;
        }
        String username = r.getUsername();
        if(username != null){
            joinSql += prefix + "username = " + "'" + username + "'" + suffix;
        }
        String password = r.getPassword();
        if(password != null){
            joinSql += prefix + "password = " + "'" + password + "'" + suffix;
        }
        return joinSql;
    }
}

