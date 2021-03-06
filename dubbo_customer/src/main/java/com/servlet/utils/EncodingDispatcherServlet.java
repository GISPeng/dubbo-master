package com.servlet.utils;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

public class EncodingDispatcherServlet extends DispatcherServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String encoding;
	
	@Override
    public void init(ServletConfig config) throws ServletException
    {
        this.encoding = config.getInitParameter("encoding");
        super.init(config);
    }

	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding(encoding);
    	response.setCharacterEncoding(encoding);
    	super.service(request, response);
    }
}
