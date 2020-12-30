package com.wind.entity;


/**
 * 用户表
 * @author wind
 */
public class User{

    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }
    
    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }
    

}

