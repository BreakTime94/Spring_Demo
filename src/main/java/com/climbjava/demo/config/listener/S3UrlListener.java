package com.climbjava.demo.config.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class S3UrlListener implements ServletContextListener{
// https://breaktime-bucket.s3.ap-northeast-2.amazonaws.com/upload/2025/07/08/b56585ec-d3ed-4d76-91e0-cd7952b8e2d1.pdf

	
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc =  sce.getServletContext();

	}
}
