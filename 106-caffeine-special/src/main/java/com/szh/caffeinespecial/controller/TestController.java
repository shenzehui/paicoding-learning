package com.szh.caffeinespecial.controller;

import com.szh.caffeinespecial.entity.User;
import com.szh.caffeinespecial.service.AnoCacheService;
import com.szh.caffeinespecial.service.AnoCacheService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * anoCacheService 写入缓存
 * anoCacheService2 查看缓存，此时不应该能查到前面写入的缓存
 * Created by szh on 2023-05-12
 *
 * @author szh
 */

@RestController
public class TestController {

    @Autowired
    private AnoCacheService anoCacheService;

    @Autowired
    private AnoCacheService2 anoCacheService2;

    private AtomicInteger uid = new AtomicInteger(1);

    private AtomicInteger uid2 = new AtomicInteger(1);

    @RequestMapping(path = "save")
    public User save(String name, @RequestParam(required = false, defaultValue = "1") Integer type) {
        if (type == 1) {
            return anoCacheService.saveUser(new User(uid.getAndAdd(1), name));
        } else {
            return anoCacheService2.saveUser(new User(uid2.getAndAdd(1), name));
        }
    }

    @RequestMapping(path = "query")
    public User query(int userId, @RequestParam(required = false, defaultValue = "1") Integer type) {
        User user = type == 1 ? anoCacheService.getUser(userId) : anoCacheService2.getUser(userId);
        return user == null ? new User() : user;
    }

    @RequestMapping(path = "remove")
    public String remove(int userId, @RequestParam(required = false, defaultValue = "1") Integer type) {
        if (type == 1) {
            anoCacheService.removeUser(userId);
        } else {
            anoCacheService2.removeUser(userId);
        }
        return "ok";
    }
}
