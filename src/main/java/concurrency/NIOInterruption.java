package concurrency;

import java.io.IOException;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.concurrent.*;

/**
 * NIO阻塞可响应中断，也可通过直接关闭底层资源来关闭资源
 */
class NIOBlocked implements Runnable {
    private final SocketChannel sc;

    public NIOBlocked(SocketChannel sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        System.out.println("Waiting for read() in " + this);
        try {
            sc.read(ByteBuffer.allocate(1));
        } catch (ClosedByInterruptException e) {
            System.out.println("ClosedByInterruptException");
        } catch (AsynchronousCloseException e) {
            System.out.println("AsynchronousCloseException");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Exiting NIOBlocked.run() "+ this);
    }
}

public class NIOInterruption {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);
        Future<?> f = exec.submit(new NIOBlocked(sc1));
        exec.execute(new NIOBlocked(sc2));
        exec.shutdown();
        TimeUnit.SECONDS.sleep(1);
        // 中断
        f.cancel(true);
        TimeUnit.SECONDS.sleep(1);
        // 直接关闭资源
        sc2.close();
    }

}
