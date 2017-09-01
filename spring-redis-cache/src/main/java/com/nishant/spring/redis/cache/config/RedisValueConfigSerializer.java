package com.nishant.spring.redis.cache.config;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
@Component
public class RedisValueConfigSerializer implements RedisSerializer<Object> {

	@Override
	public Object deserialize(byte[] bytes)  {
		return  org.springframework.util.SerializationUtils.deserialize(bytes);
	}

	@Override
	public byte[] serialize(Object t) {
		return org.springframework.util.SerializationUtils.serialize(t);
	}



}
