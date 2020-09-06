package concurrency;

/**
 * 序列号生成器，非线程安全
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public static int nextSerialNumber() {
        // 不是原子操作，非线程安全
        return serialNumber++;
    }
}
