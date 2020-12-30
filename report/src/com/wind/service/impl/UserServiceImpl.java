package com.wind.service.impl;

import java.util.List;
import com.wind.dao.UserDao;
import com.wind.dao.impl.UserDaoImpl;
import com.wind.entity.User;
import com.wind.service.UserService;
import com.wind.service.base.BaseServiceImpl;
import com.wind.entity.base.Page;

/**
 * 用户表 service接口实现
 * @author wind
 */
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService{

    private UserDao dao = new UserDaoImpl();


    @Override
    public int insert(User r) {
        int i = dao.insert(r);
        return i;
    }

    @Override
    public int delete(User r) {
        return dao.deleteByCondition(r);
    }

    @Override
    public User findEntity(User r) {
        List<User> entitys = dao.findByCondition(r);
        return entitys.size() == 1 ? entitys.get(0) : null;
    }

    @Override
    public List<User> findList(User r) {
        return dao.findByCondition(r);
    }

    @Override
    public void findPageList(User r, Page page){
        dao.findPageList(r, page);
    }

    @Override
    public int update(User r) {
        return dao.updateByCondition(r);
    }

    @Override
    public int count(User r){
        return dao.countByCondition(r);
    }
}

