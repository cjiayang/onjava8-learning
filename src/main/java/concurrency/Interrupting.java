package concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

//可中断
class SleepBlocked implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        System.out.println("Exiting SleepBlocked.run()");
    }
}

// 不可中断，意味着IO具有锁住多线程程序的可能
class IOBlocked implements Runnable {
    private InputStream in;

    public IOBlocked(InputStream is) {
        this.in = is;
    }
    @Override
    public void run() {
        System.out.println("waiting for read():");
        try {
            in.read(); // 等待IO输入，线程处于阻塞状态
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted from blocked IO");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exiting IOBlocked.run()");
    }
}

// 不可中断
class SynchronizedBlocked implements Runnable {
    public synchronized void f() {
        while(true) // Never releases lock
            Thread.yield();
    }
    public SynchronizedBlocked() {
        new Thread() {
            public void run() {
                f(); // Lock acquired by this thread
            }
        }.start();
    }
    public void run() {
        System.out.println("Trying to call f()");
        f();
        System.out.println("Exiting SynchronizedBlocked.run()");
    }
}

/**
 * 测试线程中断
 */
public class Interrupting {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException{
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting " + r.getClass().getSimpleName());
        f.cancel(true);
        System.out.println("Interrupt sent to " + r.getClass().getSimpleName());
    }

    public static void main(String[] args) throws Exception {
//        test(new SleepBlocked());
//        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System exit(0)");
        System.exit(0);
    }

}
