package com.szh.caffeinespecial.service;

import com.szh.caffeinespecial.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by szh on 2023-05-12
 *
 * @author szh
 */

@Service
// 使用 customCacheManager 中的 customCache 缓存实例
@CacheConfig(cacheNames = "customCache", cacheManager = "customCacheManager")
public class AnoCacheService {

    /**
     * 用一个 map 来模拟存储
     */
    private Map<Integer, User> userDb = new ConcurrentHashMap<>();

    /**
     * 添加数据，并保存到缓存中，不管缓存中有没有，都会更新缓存
     *
     * @param user
     * @return
     */
    @CachePut(key = "#user.uid")
    public User saveUser(User user) {
        userDb.put(user.getUid(), user);
        return user;
    }

    /**
     * 优先从缓存中获取数据，若不存在，则从 userDb 中查询，并会将结果写入到缓存中
     *
     * @param userId
     * @return
     */
    @Cacheable(key = "#userId")
    public User getUser(int userId) {
        System.out.println("doGetUser from DB:" + userId);
        return userDb.get(userId);
    }

    @CacheEvict(key = "#userId")
    public void removeUser(int userId) {
        userDb.remove(userId);
    }

}
