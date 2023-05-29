package com.szh.caffeine.controller;

import com.szh.caffeine.entity.User;
import com.szh.caffeine.serivice.AnoCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by szh on 2023-05-09
 *
 * @author szh
 */

@RestController
public class TestController {

    @Autowired
    private AnoCacheService anoCacheService;

    private AtomicInteger uid = new AtomicInteger(1);

    @RequestMapping(path = "save")
    public User save(String name) {
        return anoCacheService.saveUser(new User(uid.getAndAdd(1), name));
    }

    @RequestMapping(path = "query")
    public User query(int userId) {
        User user = anoCacheService.getUser(userId);
        return user == null ? new User() : user;
    }

    @RequestMapping(path = "remove")
    public String remove(int userId) {
        anoCacheService.removeUser(userId);
        return "ok";
    }
}
