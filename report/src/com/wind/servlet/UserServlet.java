package com.wind.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wind.entity.User;
import com.wind.service.UserService;
import com.wind.service.impl.UserServiceImpl;
import com.wind.util.Md5Util;
import com.wind.util.ServletUtil;

/**
 * 用户servlet
 * @author follow
 *
 */
public class UserServlet{
	
	private UserService service = new UserServiceImpl();
	
	/**
	 * 登录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(Md5Util.getMD5String(password));
        List<User> list = service.findList(user); 
        if(list.isEmpty()){
        	ServletUtil.fail(resp, "账号或密码错误");
        	return;
        }
        HttpSession session = req.getSession();
        session.setAttribute(ServletUtil.SESSION_USER, user);
        ServletUtil.ok(resp, "");
    	return;
    }

    /**
     * 注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void reg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        user.setUsername(username);
        List<User> list = service.findList(user); 
        if(!list.isEmpty()){
        	ServletUtil.fail(resp, "账号已存在");
        	return;
        }
        user.setPassword(Md5Util.getMD5String(password));
    	service.insert(user);
    	ServletUtil.ok(resp, "");
    	return;
    }
	
}
