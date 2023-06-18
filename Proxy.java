package com.lgcns.tct.fileio.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Proxy {
	Integer port; 
	List<Routes> routes;
	
	Map<String, String> routeMap = new HashMap<>();
	
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	public List<Routes> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Routes> routes) {
		this.routes = routes;
	}
	
	public void toMap() {
		routeMap.clear();
		
		for(Routes route: routes) {
			routeMap.put(route.getPathPrefix(), route.getUrl());
		}
	}
	
	public String getUrl(String path)  {
		int idx = path.lastIndexOf("/");
		if(idx > 0) {
			path = path.substring(0, idx);
		}
		if (routeMap.containsKey(path)) {
			return routeMap.get(path);
		} else {
			System.out.println("ERROR:");
			return "";
		}
	}
	 
	@Override
	public String toString() {
		return "Proxy [port=" + port + ", routes=" + routes + "]";
	}

}
