package com.vercetti.TextCache;

import model.Paste;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class TextCacheApplication {
	public static void main(String[] args) {
		SpringApplication.run(TextCacheApplication.class, args);
	}

	@Bean
	public RedisTemplate<String, Paste> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Paste> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		Jackson2JsonRedisSerializer<Paste> jsonSerializer = new Jackson2JsonRedisSerializer<>(Paste.class);
		redisTemplate.setValueSerializer(jsonSerializer);
		return redisTemplate;
	}

}
