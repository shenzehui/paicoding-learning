package com.szh.webloginusercount.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by szh on 2023-05-09
 *
 * @author szh
 */

@Service
public class CountService {

    private AtomicInteger cnt = new AtomicInteger(0);

    public void incr(int cnt) {
        this.cnt.addAndGet(cnt);
    }

    public int getOnlineCnt() {
        return cnt.get();
    }
}
