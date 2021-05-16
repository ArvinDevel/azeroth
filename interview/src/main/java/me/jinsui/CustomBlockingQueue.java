package me.jinsui;

import java.util.Queue;

public class CustomBlockingQueue<T> {
    Queue queue;
    int maxLen;
    void put(){
        if(queue.size() == maxLen){
            synchronized (this){
                while (queue.size() == maxLen){
                    try {
                        this.wait();
                    } catch (InterruptedException interruptedException){
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}
