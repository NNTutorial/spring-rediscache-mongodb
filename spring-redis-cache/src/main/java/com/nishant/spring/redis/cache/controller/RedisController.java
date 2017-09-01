package com.nishant.spring.redis.cache.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.spring.redis.cache.vo.RedisVO;

@RestController
@RequestMapping("/redis")
@CacheConfig(cacheNames="redisVO")
public class RedisController {

	private static final String MONGODB_COLLECTION_NAME = "redisVO";
	@Autowired
	private MongoTemplate mongoTemplate;

	@RequestMapping(value="/set",method=RequestMethod.POST)
	public RedisVO set(@RequestBody RedisVO redisVO){
		mongoTemplate.insert(redisVO, MONGODB_COLLECTION_NAME);
		return redisVO;
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@CachePut(keyGenerator="keyGenerator")
	public RedisVO update(@RequestBody RedisVO redisVO){
		mongoTemplate.save(redisVO, MONGODB_COLLECTION_NAME);
		return redisVO;
	}
	@RequestMapping(value="/get/{Id}",method=RequestMethod.GET)
	@Cacheable(keyGenerator="keyGenerator")
	public RedisVO get(@PathVariable Integer Id) throws IOException {
		System.out.println("method /get/{userId} executed"+Id);
		return mongoTemplate.findById(Id,RedisVO.class, MONGODB_COLLECTION_NAME);
	}

	@RequestMapping(value="/del/{key}",method=RequestMethod.DELETE)
	@CacheEvict(keyGenerator="keyGenerator")
	public String delKey(@PathVariable String key) {
		System.out.println("method /del/{key} executed"+key);
		Query q=new Query();
		q.addCriteria(Criteria.where("keyname").is(key));
		mongoTemplate.findAndRemove(q,RedisVO.class, MONGODB_COLLECTION_NAME);
		return "deleted key:"+key;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String invalidRequest(Exception e) {
		return "invalid request";
	}


}
