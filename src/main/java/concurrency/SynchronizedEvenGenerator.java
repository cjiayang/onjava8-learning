package concurrency;

/**
 * SynchronizedEvenGenerator类
 * 使用synchronized关键字对共享变量访问进行加锁保证线程安全
 */
public class SynchronizedEvenGenerator extends IntGenerator{
    private int currentEvenValue = 0;
    // 加锁同步，保证可见性和原子性
    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield(); // 引入线程让步，提高CPU在currentEvenValue是奇数状态时上下文切换的几率
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
