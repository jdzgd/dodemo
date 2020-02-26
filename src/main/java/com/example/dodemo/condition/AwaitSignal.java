package com.example.dodemo.condition;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AwaitSignal
 * 验证condition.await()和 condition.signalAll()操作
 * 开启了两个线程waiter和signaler，waiter线程开始执行的时候由于条件不满足，
 * 执行condition.await方法使该线程进入等待状态同时释放锁，signaler线程获取到锁之后更改条件，
 * 并通知所有的等待线程后释放锁。这时，waiter线程获取到锁，并由于signaler线程更改了条件此时相对于waiter来说条件满足，
 * 继续执行。
 *
 * @author jiangdongzhao
 * @version V1.0.0
 * @date 2020/2/26
 */

public class AwaitSignal {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static volatile boolean flag = true;
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        executorService.execute(new Waiter("waiter"));
        executorService.execute(new Signaler("signaler"));
        executorService.shutdown();
    }

    static class Waiter implements Runnable {
        private String threadName;

        public Waiter(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                while (flag) {
                    System.out.println(this.threadName + "当前条件不满足等待");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(this.threadName + "接收到通知条件满足");
            } finally {
                lock.unlock();
            }
        }
    }

    static class Signaler implements Runnable {
        private String threadName;
        public Signaler(String threadName) {
            this.threadName = threadName;
        }
        @Override
        public void run() {
            lock.lock();
            try {
                flag = false;
                System.out.println(this.threadName + "执行signalAll()");
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}

