package concurrency;

public class EvenGenerator extends IntGenerator{
    private int currentEvenValue = 0;
    @Override
    public int next() {
        // 这里不是原子操作，当在多线程环境中，就不安全了
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
