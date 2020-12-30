package com.wind.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MvcServlet extends HttpServlet{
	
	private UserServlet userServlet = new UserServlet();
	
	
	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String url = req.getRequestURI();
        req.setCharacterEncoding("utf-8");
        if(url.contains("login.do")){
        	userServlet.login(req, resp);
        }else if(url.contains("reg.do")){
        	userServlet.reg(req, resp);
        }

    }

	
	
}
