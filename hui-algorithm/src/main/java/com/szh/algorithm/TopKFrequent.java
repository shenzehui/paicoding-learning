package com.szh.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {

    /**
     * 347. 前 K 个高频元素（小顶堆实现）
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 统计出现次数
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[k];
        int index = 0;

        for (int num : nums) {
//            if (map.containsKey(num)) {
//                map.put(num, map.get(num) + 1);
//            } else {
//                map.put(num, 1);
//            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 小顶堆，定义比较器（只是比较出现次数）
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((pair1, pair2) -> pair1[1] - pair2[1]);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(new int[]{entry.getKey(), entry.getValue()});
            } else {
                if (entry.getValue() > priorityQueue.peek()[1]) { // 比队头的元素（最小元素）大
                    priorityQueue.poll();  //弹出队头(小顶堆的根结点),即把堆里出现次数最少的那个删除,留下的就是出现次数多的了
                    priorityQueue.add(new int[]{entry.getKey(), entry.getValue()}); // 入队
                }
            }
        }

        //使用iterator()方法
        Iterator<int[]> iterate = priorityQueue.iterator();
        while (iterate.hasNext()) {
            res[index++] = iterate.next()[0];
        }
        return res;
    }


    /**
     * 347. 前 K 个高频元素（大顶堆实现）
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent1(int[] nums, int k) {
        // 统计出现次数
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[k];
        int index = 0;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 大顶堆，定义比较器（只是比较出现次数）
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((pair1, pair2) -> pair2[1] - pair1[1]);

        // 全部塞到大顶堆里
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            priorityQueue.add(new int[]{entry.getKey(), entry.getValue()});
        }

        // 出队K个元素即可
        for (int i = 0; i < k; i++) {
            res[index++] = priorityQueue.poll()[0];
        }
        return res;
    }
}
