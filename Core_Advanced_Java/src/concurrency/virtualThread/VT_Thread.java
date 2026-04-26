package concurrency.virtualThread;

import java.util.concurrent.TimeUnit;

public class VT_Thread {

    private static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("in run...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Remounting" + Thread.currentThread());
            go();
        }

        private void go() {
            System.out.println("IN go....");
            go2();
        }

        private void go2() {
            System.out.println("in go2......");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        // First way :
        Thread thread = Thread.startVirtualThread(task);
        thread.setName("vt-1");
        // Second way :
        // Thread thread2 = Thread.ofVirtual().name("vt 2").start(task);

        System.out.println("Is virtual? " + thread.isVirtual());
        System.out.println("Is deamon? " + thread.isDaemon());
        System.out.println("Name " + thread.getName());
        System.out.println("Priority: " + thread.getPriority());

        TimeUnit.SECONDS.sleep(1);

        // if no timeout then main thread will exit as above thread is virtual thread
        // which is deamon thread, so main thread does not wait for daemon thread
        // hence either timeout or second option is to use join

        // thread.join();
        System.out.println("In main method");
    }
}
