package me.jinsui.lang;

/**
 * 某个线程wait 之后释放锁，被notify后，重新获取锁，然后执行wait后面的语句；
 * notify不释放锁，后续指令如果继续持有（如sleep），则其他线程抢不到锁
 */
public class ThreadCommunication {
    static class MultiThread {
        /**
         * @param args
         */
        public static void main(String[] args) {
            new Thread(new Runnable1()).start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new Runnable2()).start();
        }

        private static class Runnable1 implements Runnable {

            @Override
            public void run() {
                // 由于这里的Thread1和下面的Thread2内部run方法要用同一对象作为监视器，我们这里不能用this，因为在Thread2里面的this和这个Thread1的this不是同一个对象。我们用MultiThread.class这个字节码对象，当前虚拟机里引用这个变量时，指向的都是同一个对象。
                synchronized (MultiThread.class) {
                    System.out.println("enter thread1...");
                    System.out.println("thread1 is waiting");
                    try {
                        // 释放锁有两种方式，第一种方式是程序自然离开监视器的范围，也就是离开了synchronized关键字管辖的代码范围，另一种方式就是在synchronized关键字管辖的代码内部调用监视器对象的wait方法。这里，使用wait方法释放锁。
                        MultiThread.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("thread1 is going on...");
                    System.out.println("thread1 is being over!");
                }
            }

        }

        private static class Runnable2 implements Runnable {

            @Override
            public void run() {
                synchronized (MultiThread.class) {

                    System.out.println("enter thread2...");
                    System.out.println("thread2 notify other thread can release wait status..");
                    // 由于notify方法并不释放锁，
                    // 即使thread2调用下面的sleep方法休息了10毫秒，但thread1仍然不会执行，因为thread2没有释放锁，所以Thread1无法得不到锁。
                    //notify并不释放锁，只是告诉调用过wait方法的线程可以去参与获得锁的竞争了，但不是马上得到锁，因为锁还在别人手里，别人还没释放。如果notify方法后面的代码还有很多，需要这些代码执行完后才会释放锁
                    MultiThread.class.notify();
                    System.out.println("thread2 is sleeping ten millisecond...");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread2 is going on...");
                    System.out.println("thread2 is being over!");
                }
            }

        }
        /**
         * 运行结果：
         *  enter thread1...
         thread1 is waiting
         enter thread2...
         thread2 notify other thread can release wait status..
         thread2 is sleeping ten millisecond...
         thread2 is going on...
         thread2 is being over!
         thread1 is going on...
         thread1 is being over!
         */
    }

}

