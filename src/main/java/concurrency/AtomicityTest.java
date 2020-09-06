package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AtomicityTest类
 * 执行原子性测试，对共享变量i的读取和写入，必须要用同一个锁锁住。
 * 该例子中evenIncrement()方法加锁，但getValue()方法没有加锁，
 * 一个线程在写入到一半的时候，另一个线程在没锁的情况下，可以读取共享变量i的值。
 */
public class AtomicityTest implements Runnable {
    private int i = 0;

    public int getValue() {
        return i;
    }

    private synchronized void evenIncrement() {
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        // 该线程对共享变量i执行递增操作
        exec.execute(at);
        // 主线程中执行获取共享变量i的操作
        while (true) {
            int val = at.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
