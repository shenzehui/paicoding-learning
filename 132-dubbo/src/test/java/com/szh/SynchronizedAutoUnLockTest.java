package com.szh;

/**
 * synchronized代码块或方法的代码如果抛出异常，锁会自动释放。
 * <p>
 * 这是因为 Java 的 synchronized 关键字是基于 JVM 的监视器锁（Monitor Lock）机制实现的。
 * 当一个线程进入 synchronized 代码块或方法时，它会获取该对象的 monitor 锁。当
 * 线程离开 synchronized 代码块或方法时，它会释放 monitor 锁。
 * JVM 会确保在 synchronized 代码块或方法执行结束后（无论是正常结束还是异常结束），
 * 锁都会被正确释放。这种机制避免了因异常导致的死锁问题，确保了锁的可靠释放。
 * <p>
 * 下面通过代码来实际演示一下：
 */
public class SynchronizedAutoUnLockTest {

    private static final Object lock = new Object();
    private static int counter = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                incrementAndThrow();
            } catch (RuntimeException e) {
                System.out.println(Thread.currentThread().getName() + " 捕获到异常: " + e.getMessage());
            }
        }, "Thread 1");

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " 获取到锁，计数器值: " + counter);
            }
        }, "Thread 2");

        thread1.start();
        // 稍微延迟一下，确保 thread1 先执行
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }

    private static void incrementAndThrow() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " 获取到锁，增加计数器");
            counter++;
            throw new RuntimeException("故意抛出异常");
        }
    }
}
