package com.nishant.spring.redis.cache.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nishant.spring.redis.cache.property.RedisProperty;
import com.nishant.spring.redis.cache.vo.RedisVO;

@Configuration
@Profile(value= {"redis"})
public class RedisConfig {
	@Autowired
	private RedisValueConfigSerializer redisValueConfigSerializer;

	@Autowired
	private RedisProperty redisProperty; 

	@Bean
	public JedisConnectionFactory jedisConnFactory() {
		JedisConnectionFactory jd=new JedisConnectionFactory();
		jd.setUsePool(true);
		jd.setHostName(redisProperty.getRedis().getHost());
		jd.setPort(Integer.parseInt(redisProperty.getRedis().getPort()));
		return jd;
	}

	@Bean
	public RedisTemplate<RedisVO, RedisVO> redisTemplate() {
		RedisTemplate<RedisVO, RedisVO> rt=new RedisTemplate<>();
		rt.setConnectionFactory(jedisConnFactory());
		rt.setKeySerializer(new StringRedisSerializer());
		rt.setValueSerializer(redisValueConfigSerializer);
		return rt;
	}

	@Bean
	public ObjectMapper ojectMapper() {
		return new ObjectMapper();
	}
	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager rc=new RedisCacheManager(redisTemplate());
		rc.setDefaultExpiration(60);
		rc.setTransactionAware(true);
		return rc;
	}

	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {

			@Override
			public Object generate(Object arg0, Method arg1, Object... arg2) {
				Integer key = null;
				for (Object param : arg2) {
					if(param instanceof RedisVO) {
						RedisVO rv=(RedisVO) param;
						key=rv.getKeyID();
					}
					if(param instanceof Integer) {
						key=(Integer) param;
					}
				}
				return String.valueOf(key);
			}
		};
	}
}
