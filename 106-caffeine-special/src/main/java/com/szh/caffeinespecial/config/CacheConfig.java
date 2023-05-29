package com.szh.caffeinespecial.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by szh on 2023-05-12
 *
 * @author szh
 */

@Configuration
public class CacheConfig {

    @Primary
    @Bean("customCacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        // 这里每个 Cache 对象就可以理解为一个缓存实例
        List<Cache> cacheList = new ArrayList<>();
        cacheList.add(customerCache());
        cacheList.add(customerCache2());
        simpleCacheManager.setCaches(cacheList);
        return simpleCacheManager;
    }

    private Cache customerCache() {
        // customCache 与 @CacheConfig 中的 cacheNames 保持一致
        return new CaffeineCache("customCache", Caffeine.newBuilder()
                .maximumSize(200)
                .initialCapacity(100)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .recordStats()
                .build(), true);
    }

    /**
     * 添加新的缓存实例
     *
     * @return
     */
    private Cache customerCache2() {
        return new CaffeineCache("customCache", Caffeine.newBuilder()
                .maximumSize(200)
                .initialCapacity(100)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .recordStats()
                .build(), true);
    }

    @Bean("otherCacheManager")
    public CacheManager cacheManager2() {
        CaffeineCacheManager manager = new CaffeineCacheManager();
        manager.setCaffeine(Caffeine.newBuilder()
                // 设置过期时间，写入五分钟后过期
                .expireAfterWrite(5, TimeUnit.MINUTES)
                // 初始化缓存空间大小
                .initialCapacity(100)
                // 最大的缓存条数
                .maximumSize(200)
        );
        return manager;
    }
}
