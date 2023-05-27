package com.practice.boardgame;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private Integer redisPort;
    // @Value("${spring.redis.database}")
    // private Integer redisDatabase;
    // @Value("${spring.redis.username}")
    // private String redisUsername;
    // @Value("${REDIS_PASSWORD}")
    // private String redisPassword;

    @Bean
    public RedisTemplate<String, Object> initRedisTemplate() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(redisHost);
        redisConfig.setPort(redisPort);
        // redisConfig.setUsername(redisUsername);
        // redisConfig.setPassword(redisPassword);

        JedisClientConfiguration jedisConfig = JedisClientConfiguration.builder().build();

        JedisConnectionFactory jedisFac = new JedisConnectionFactory(redisConfig, jedisConfig);
        jedisFac.afterPropertiesSet();

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisFac);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());
        redisTemplate.setValueSerializer(serializer);
        return redisTemplate;
    }
    
}

//Jackson2JsonRedisSerializer jackson2JsonJsonSerializer = new Jackson2JsonRedisSerializer(Mastermind.class);
//RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());
