package com.lgcns.tct.jetty.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class MyJettyServer {
	
	public static void main(String args[]) {
		MyJettyServer server = new MyJettyServer();
		server.start();
	}
	
	public void start() {
		System.out.println("Starts with....");
		
		//Server server = new Server(proxy.getPort());
		Server server = new Server();
	    ServerConnector http = new ServerConnector(server);
	    http.setHost("127.0.0.1");
	    http.setPort(5001);
	    server.addConnector(http);
	    
		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");
		//context.setAttribute(ATTR_NAME_PROXY, proxyRules);
		//context.setAttribute(ATTR_NAME_HISTORY, mapHistory);
		
		//https://stackoverflow.com/questions/30733910/whats-the-difference-between-a-servlethandler-and-a-servletcontexthandler-in-je
		//context.addServlet(TraceServlet.class, "/trace");
		context.addServlet(MyJettyServlet.class, "/");
		server.setHandler(context);
				
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
