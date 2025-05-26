package com.szh.proxy.cglib;

import com.szh.proxy.target.ISolver;
import com.szh.proxy.target.Solver;

public class Client {
    public static void main(String[] args) {
        // 目标对象
        ISolver solver = new Solver();

        // 代理对象
        ISolver proxy = (ISolver) new ProxyFactory(solver).getProxyInstance();

        // 解决问题
        proxy.solve();

    }
}
