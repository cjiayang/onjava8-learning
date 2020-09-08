package concurrency.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car {
    private boolean waxOn = false;
    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    public synchronized void waitForMaxing() throws InterruptedException {
        // 此处需要while循环检查，即线程被唤醒后首先应该先检查条件是否满足，
        // 因为这个条件有可能被多个线程所改变，如果条件不满足，则继续等待
        while (!waxOn) {
            wait();
        }
    }
    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn) {
            wait();
        }
    }

}

// 涂蜡线程
class WaxOn implements Runnable{
    private Car car;

    public WaxOn(Car c) {
        this.car = c;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Wax On! "); // 涂蜡开始
                TimeUnit.MILLISECONDS.sleep(200); // 执行涂蜡
                car.waxed();  // 涂蜡完成，通知抛光线程
                car.waitForBuffing(); // 等待抛光
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

// 抛光线程
class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car c) {
        this.car = c;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForMaxing();  // 等待涂蜡，涂蜡线程开始工作
                System.out.println("Wax Off! "); // 涂蜡结束
                TimeUnit.MILLISECONDS.sleep(200);  // 执行抛光
                car.buffed(); //抛光完成，唤醒涂蜡线程
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting vi interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}

/**
 * 线程协作之汽车涂蜡抛光例子
 */
public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
