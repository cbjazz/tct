package com.lgcns.tct.jetty.client;

import java.io.FileOutputStream;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;

public class MyHttpStreamClient {
	public static void main(String args[]) {
		MyHttpStreamClient.sendBinarytMessage("http://localhost:5001/picture");
	}
	
	public static void sendBinarytMessage(String requestURL) {
		try {
		     HttpClient client = new HttpClient();
		     client.start();
		     
		     ContentResponse res = client.newRequest(requestURL)
		    		 .method(HttpMethod.GET).send();
		     byte[] myPictureByteArray = res.getContent();
		     
		     try(FileOutputStream fos = new FileOutputStream("examples/out/picture.png")) {
		    	 fos.write(myPictureByteArray);
		     }
		     
			
			client.stop();
		     
		 } catch(Exception e) {
			 System.out.println(e.getMessage());
		 }
	}
}
