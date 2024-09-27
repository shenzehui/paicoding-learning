package com.szh.redisson.pojo;

import com.szh.redisson.lock.IDistributedLock;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

@AllArgsConstructor
public class ILock implements AutoCloseable {

    @Getter
    private Object lock;

    @Getter
    private IDistributedLock distributedLock;

    @Override
    public void close() throws Exception {
        if (Objects.nonNull(lock)) {
            distributedLock.unLock(lock);
        }
    }
}
