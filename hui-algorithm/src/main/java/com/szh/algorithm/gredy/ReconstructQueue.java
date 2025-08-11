package com.szh.algorithm.gredy;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 406. 根据身高重建队列
 */
public class ReconstructQueue {

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                // 身高相同的ki小的站前面
                return a[1] - b[1];
            }
            return b[0] - a[0]; // 降序，身高从高到低排列
        });

        LinkedList<int[]> que = new LinkedList<>();

        for (int[] p : people) {
            que.add(p[1], p);   //LinkedList.add(index, value)，会将value插入到指定index里。
        }
        return que.toArray(new int[people.length][]);
    }
}
