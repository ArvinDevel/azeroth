package me.jinsui;

/**
 * 生产者，消费者模式下，不能都采用先wait再notify，会持续waiting，无法前进
 */
public class OddEvenPrinter {

    public static void main(String[] args) {
//        Object monitor = new Object();
//        new Thread(new EvenPrintTask(monitor), "偶数").start();
//        new Thread(new OldPrintTask(monitor), "奇数").start();
//        printInterleavely();
        printInterleavely2();
    }


    static class OldPrintTask implements Runnable {

        private Object monitor;
        //奇数线程从1开始打印
        private int value = 1;

        public OldPrintTask(Object monitor) {
            this.monitor = monitor;
        }

        @Override
        public void run() {
            while (value < 100) {
                synchronized (monitor) {
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                    value += 2;
                    monitor.notify();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class EvenPrintTask implements Runnable {

        private Object monitor;
        //偶数对象
        private int value = 0;

        public EvenPrintTask(Object monitor) {
            this.monitor = monitor;
        }

        @Override
        public void run() {
            while (value <= 100) {
                synchronized (monitor) {
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                    value += 2;
                    monitor.notify();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * use one monitor lock to control
     */
    private static void printInterleavely() {
        Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            int odd = 1;
            while (true) {
                synchronized (lock) {
                    System.out.println(odd);
                    odd += 2;
                    // 完成自己的任务后，通知后续依赖任务
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //放这里不合适，导致两个线程同时被唤醒，可能偶数的先抢到锁
//                    lock.notify();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            int even = 2;
            while (true) {
                synchronized (lock) {
                    System.out.println(even);
                    even += 2;
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }


    /**
     * 使用两个锁:死锁，因为锁的获取顺序不同
     */
    private static void printInterleavely2() {
        Object lock1 = new Object();
        Object lock2 = new Object();
        Thread thread1 = new Thread(() -> {
            int odd = 1;
            while (true) {
                synchronized (lock1) {
                    System.out.println(odd);
                    odd += 2;
                    synchronized (lock2) {
                        lock2.notify();
                    }
                    try {
                        lock1.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            int even = 2;
            while (true) {
                synchronized (lock2) {
                    System.out.println(even);
                    even += 2;
                    synchronized (lock1) {
                        lock1.notify();
                    }
                    try {
                        lock2.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 错误：很容易出现先打印偶数，再打印奇数，
                // 但放入锁内则容易死锁
//                synchronized (lock1) {
//                    lock1.notify();
//                }
            }
        });
        thread1.start();
        thread2.start();
    }

}


