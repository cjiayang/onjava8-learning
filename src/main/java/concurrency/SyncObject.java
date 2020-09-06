package concurrency;

class DualSynch {
    private final Object syncObject = new Object();

    // this作为锁对象
    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void g() {
        // syncObject作为锁对象
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}

// 使用锁对象加锁，当使用锁对象加锁时，必须在所有相关任务中使用同一个锁对象
public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread(ds::f).start();
        ds.g();
    }
}
