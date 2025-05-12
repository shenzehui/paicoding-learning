package com.szh.delay;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时任务实体类，可以根据不同业务自定义
 *
 * @author szh
 */
@Data
@Accessors(chain = true)
@ToString
public class DelayTask implements Delayed {

    private String id;

    private Long time;

    private Integer type;

    private String dataStr;

    @Override
    public long getDelay(TimeUnit unit) {
        // 计算该任务距离过期还剩多少时间
        long remaining = time - System.currentTimeMillis();
        return unit.convert(remaining, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        // 比较、排序：对任务的延时大小进行排序，将延时时间最小的任务放到队列头部
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }
}
