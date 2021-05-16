package me.jinsui.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 可以指定线程池，否则使用默认的；
 * run then apply函数、accept消费者、supply提供
 */
public class CompletableFuturePractice {
    public static void main(String[] args) {
        exceptionHandle();
    }

    private static void getResult() throws Exception {
        CompletableFuture future = new CompletableFuture();
        future.get();
        future.join();
    }

    /**
     * handle有返回值,whenComplete没有返回值
     */
    private static void handleResult() {

    }

    private static void exceptionHandle() {
        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "futureA result:" + s)
                .exceptionally(e -> {
                    System.out.println(e.getMessage()); //java.lang.ArithmeticException: / by zero
                    return "futureA result: 100";
                });
        CompletableFuture<String> futureB = CompletableFuture.
                supplyAsync(() -> "执行结果:" + 50)
                .thenApply(s -> "futureB result:" + s)
                .exceptionally(e -> "futureB result: 100");
        System.out.println(futureA.join());//futureA result: 100
        System.out.println(futureB.join());//futureB result:执行结果:50
    }

}
