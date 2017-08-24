package com.sunshine.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RunnableDemo
    implements Runnable {
    
    private Thread thread;
    
    private String name;
    
    private Queue<String> target;
    
    RunnableDemo(String name, Queue<String> target){
        this.name = name;
        this.target = target;
    }

    @Override
    public void run() {
        if(target == null 
                || target.size() == 0){
            return;
        }
        while(target.size() != 0){
            System.out.println("Thread" + name + "->" + target.poll());
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    private void start() {
        if (thread == null) {
            thread = new Thread (this, name);
            thread.start ();
         }
    }
    
    
    
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<String>();
        List<String> list = new ArrayList<>();
        for(int i=0; i<100; i++){
            queue.offer(String.valueOf(i));
            list.add(String.valueOf(i));
        }
        System.out.println(queue);
        for(int i=0; i<5; i++){
            RunnableDemo r = new RunnableDemo(String.valueOf(i), queue);
            r.start();
        }
    }

}
