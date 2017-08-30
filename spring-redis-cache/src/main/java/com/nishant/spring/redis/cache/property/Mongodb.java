package com.nishant.spring.redis.cache.property;

import org.springframework.stereotype.Component;

@Component
public class Mongodb {

	private String host;
	private String port;
	private String database;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}


}
