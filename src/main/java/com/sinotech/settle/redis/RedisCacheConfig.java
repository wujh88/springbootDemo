package com.sinotech.settle.redis;

import com.sinotech.settle.config.ConfigConsts;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
        /**
       * redis哨兵配置
       * @return
       */
      @Bean
      public RedisSentinelConfiguration redisSentinelConfiguration(){
            RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
            String[] host = ConfigConsts.REDIS_SENTINEL.split(",");
            for(String redisHost : host){
                    String[] item = redisHost.split(":");
                    String ip = item[0];
                    String port = item[1];
                    configuration.addSentinel(new RedisNode(ip, Integer.parseInt(port)));
                }
            configuration.setMaster("mymaster");
            return configuration;
        }
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration());
        redisConnectionFactory.setUsePool(true);

        // Defaults
//        redisConnectionFactory.setHostName(ConfigConsts.REDIS_ADDR);
//        redisConnectionFactory.setPort(ConfigConsts.REDIS_PORT);
        redisConnectionFactory.setPassword(ConfigConsts.REDIS_AUTH);
        return redisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(cf);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

        // Number of seconds before expiration. Defaults to unlimited (0)
        // Sets the default expire time (in seconds)
        cacheManager.setDefaultExpiration(3000);
        return cacheManager;
    }



}
