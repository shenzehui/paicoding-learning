package com.szh.caffeine.serivice;

import com.szh.caffeine.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by szh on 2023-05-09
 *
 * @author szh
 */

@Service
/**
 * 这个注释的是默认的缓存策略，对应此时的 cacheManager 由 spring.cache.caffeine.spec 来指定缓存规则
 * 当我们使用缓存注解时，需要注意的是这个 cacheNames 必须得有，否则就会报错
 */
@CacheConfig(cacheNames = "customCache")
public class AnoCacheService {

    /**
     * 用一个 map 来存储数据
     */
    private Map<Integer, User> userDb = new ConcurrentHashMap<>();

    /**
     * 添加数据，并保存到缓存中，不管缓存中有没有，都会更新缓存
     *
     * @param user
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

    /**
     * 删除指定缓存数据
     *
     * @param userId
     */
    @CacheEvict(key = "#userId")
    public void removeUser(int userId) {
        userDb.remove(userId);
    }


}
