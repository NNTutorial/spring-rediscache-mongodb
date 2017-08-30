package com.nishant.spring.redis.cache.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Data {
	@Autowired
	private Mongodb mongodb;

	public Mongodb getMongodb() {
		return mongodb;
	}

	public void setMongodb(Mongodb mongodb) {
		this.mongodb = mongodb;
	}

	
}
