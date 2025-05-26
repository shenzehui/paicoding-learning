package com.szh.proxy.target;

/**
 * 目标类
 */
public class Solver implements ISolver {
    @Override
    public void solve() {
        System.out.println("疯狂掉头发解决问题");
    }

    @Override
    public void call() {
        System.out.println("感谢咨询，给个好评叭~");
    }
}
