package com.lgcns.tct.fileio.model;

public class Routes {
	String pathPrefix;
	String url;
	
	public Routes() {
		pathPrefix = "";
		url = "";
	}

	public String getPathPrefix() {
		return pathPrefix;
	}

	public void setPathPrefix(String pathPrefix) {
		this.pathPrefix = pathPrefix;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Routes [pathPrefix=" + pathPrefix + ", url=" + url + "]";
	}
}
