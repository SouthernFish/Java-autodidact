package com.Test4;

import java.util.Optional;

/**
 * @Author TR
 * @Create 2020年01月07日
 * @Title: ThreadSimpleAPI
 * @Description: Thread 的 API
 */
public class ThreadSimpleAPI {


    public static void threadSimpleMain1(){
        Thread t = new Thread(() -> {
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        Optional.of(t.getName()).ifPresent(System.out::println); // 线程名字
        Optional.of(t.getId()).ifPresent(System.out::println); // 线程ID
        Optional.of(t.getPriority()).ifPresent(System.out::println); // 优先级
    }

    public static void threadSimpleMain2(){
        Thread t1 = new Thread(() -> {
            for(int i = 0; i< 1000; i++){
                Optional.of(Thread.currentThread().getName()+"-index "+ i).ifPresent(System.out::println);
            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);
        Thread t2 = new Thread(() -> {
            for(int i = 0; i< 1000; i++){
                Optional.of(Thread.currentThread().getName()+"-index "+ i).ifPresent(System.out::println);
            }
        });
        t2.setPriority(Thread.NORM_PRIORITY);
        Thread t3 = new Thread(() -> {
            for(int i = 0; i< 1000; i++){
                Optional.of(Thread.currentThread().getName()+"-index "+ i).ifPresent(System.out::println);
            }
        });
        t3.setPriority(Thread.MIN_PRIORITY);
        t1.start();t2.start();t3.start();
       // 设置优先级之前 输出是混乱的  设置之后依然是混乱的 ？？？？？
    }

    public static void main(String[] args) {
//        threadSimpleMain1();
        threadSimpleMain2();
    }
}
