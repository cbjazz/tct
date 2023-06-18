package com.lgcns.tct.jetty.client;

import java.util.Collections;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentProvider;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

public class MyHttpClient {
	
	public static void main(String args[]) {
		MyHttpClient.sendGetMessage("http://localhost:5001/get?x=1");
		
		MyHttpClient.sendPostMessage("http://localhost:5001/post?x=1");
		
	}
	
	public static void sendGetMessage(String requestURL) {
		 try {
		     HttpClient client = new HttpClient();
		     client.start();
		     
		     ContentResponse res = client.newRequest(requestURL)
		    		 .method(HttpMethod.GET).send();
		     
			// Get Status
			System.out.println("Status =====================");
			System.out.println(res.getStatus());		
			
			// Header 내용 확인
			System.out.println("Headers =====================");
			for(String header: Collections.list(res.getHeaders().getFieldNames())) {
				System.out.println(header + ":" + res.getHeaders().get(header));
			}
			
			// Result 내용 확인
			System.out.println("Content =====================");
			System.out.println(res.getContentAsString());
			
			client.stop();
		     
		 } catch(Exception e) {
			 System.out.println(e.getMessage());
		 }
	}
	
	public static void sendPostMessage(String requestURL) {
		 try {
		     HttpClient client = new HttpClient();
		     client.start();
		     
		     String content = "{\r\n"
		     		+ "  \"port\": 5001,\r\n"
		     		+ "  \"routes\": [\r\n"
		     		+ "    {\r\n"
		     		+ "      \"pathPrefix\": \"/front\",\r\n"
		     		+ "      \"url\": \"http://127.0.0.1:8081\"\r\n"
		     		+ "    },\r\n"
		     		+ "    {\r\n"
		     		+ "      \"pathPrefix\": \"/auth\",\r\n"
		     		+ "      \"url\": \"http://127.0.0.1:5002\"\r\n"
		     		+ "    },\r\n"
		     		+ "    {\r\n"
		     		+ "      \"pathPrefix\": \"/notice\",\r\n"
		     		+ "      \"url\": \"http://127.0.0.1:5003\"\r\n"
		     		+ "    }\r\n"
		     		+ "\r\n"
		     		+ "  ]\r\n"
		     		+ "}";
		     
		     ContentProvider provider = new StringContentProvider(content);
		    		 
		     ContentResponse res = client.newRequest(requestURL)
		    		 .method(HttpMethod.POST).header(HttpHeader.CONTENT_TYPE, "application/json")
		    		 .content(provider).send();
		     
			// Get Status
			System.out.println("Status =====================");
			System.out.println(res.getStatus());		
			
			// Header 내용 확인
			System.out.println("Headers =====================");
			for(String header: Collections.list(res.getHeaders().getFieldNames())) {
				System.out.println(header + ":" + res.getHeaders().get(header));
			}
			
			// Result 내용 확인
			System.out.println("Content =====================");
			System.out.println(res.getContentAsString());
			
			client.stop();
		     
		 } catch(Exception e) {
			 System.out.println(e.getMessage());
		 }
	}
}
