package com.lgcns.tct.jetty.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyJettyServlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {		
		String path = req.getServletPath();	
		
		// Get Url
		System.out.println("URL =====================");
		System.out.println(req.getRequestURL());		
		
		// Get Path
		System.out.println("Path =====================");
		System.out.println(path);
		
		// Get QueryString
		System.out.println("QueryString =====================");
		System.out.println(req.getQueryString());
		
		// Header 내용 확인
		System.out.println("Headers =====================");
		for(String header: Collections.list(req.getHeaderNames())) {
			System.out.println(header + ":" + req.getHeader(header));
		}
		
		
		// @TODO Route with path
		if (path.startsWith("/picture")) {
			File file = new File("examples/picture.png"); 
			byte[] fileContent = Files.readAllBytes(file.toPath());
			
			res.setStatus(200);
			ServletOutputStream stream = res.getOutputStream();
			stream.flush();
			stream.write(fileContent);
			stream.flush();
			stream.close();
		} else {
			// 프록시 응답 설정 
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/event-stream; charset=utf-8");
			res.getWriter().print("Success");
		}
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String path = req.getServletPath();		
		
		//@TODO Route with path
		
		
		// Get Url
		System.out.println("URL =====================");
		System.out.println(req.getRequestURL());		
		
		// Get Path
		System.out.println("Path =====================");
		System.out.println(path);
		
		// Get QueryString
		System.out.println("QueryString =====================");
		System.out.println(req.getQueryString());
		
		// Header 내용 확인
		System.out.println("Headers =====================");
		for(String header: Collections.list(req.getHeaderNames())) {
			System.out.println(header + ":" + req.getHeader(header));
		}
		
		// Body 내용 확인
		System.out.println("Body =====================");
		String content = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));;
		System.out.println(content);
		
		// 프록시 응답 설정 
		res.setStatus(HttpServletResponse.SC_OK);
		res.setContentType("text/event-stream; charset=utf-8");
		res.getWriter().print("Success");		
		
	}
}
