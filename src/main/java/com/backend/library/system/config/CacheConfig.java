package com.backend.library.system.config;

import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public ConcurrentMapCacheManager cacheManager() { //ConcurrentMapCacheManager for development, could use redis for production
        return new ConcurrentMapCacheManager("bookDetailsCache","patronDetailsCache");
    }
}
