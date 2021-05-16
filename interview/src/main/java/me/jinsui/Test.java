package me.jinsui;

public class Test {
    public static void main(String[] args) {
        printInterleavely();
    }

    private static void printInterleavely() {
        Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            int odd = 1;
            while (true) {
                synchronized (lock) {
                    System.out.println(odd);
                    odd += 2;
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
}
