package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MutexEvenGenerator类
 * 使用显式锁对象进行加锁和解锁，比synchronized更加灵活，
 * 一般问题使用synchronized就可以解决，当解决某些特殊问题时，可以使用显式锁来处理
 */
public class MutexEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    // 显式锁对象
    private Lock lock = new ReentrantLock();
    @Override
    public int next() {
        lock.lock();
        try {
            ++currentEvenValue;
            Thread.yield();
            ++currentEvenValue;
            return currentEvenValue;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}
