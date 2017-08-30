package com.nishant.spring.redis.cache.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.nishant.spring.redis.cache.property.RedisProperty;

@Configuration
public class MongoConfig {
	
	@Autowired
	private RedisProperty redisProperty; 

	
	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
		return new MongoTemplate(mongoDbFactory);
	}
	@Bean
	public MongoDbFactory mongoDbFactory() {
		return new SimpleMongoDbFactory(mongoClient(), redisProperty.getData().getMongodb().getDatabase());
	}
	@Bean
	public MongoClient mongoClient() {
		return new MongoClient(redisProperty.getData().getMongodb().getHost(), Integer.parseInt(redisProperty.getData().getMongodb().getPort()));
	}

}