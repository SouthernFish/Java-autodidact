package com.Test3;

import java.util.Arrays;

/**
 * @Author TR
 * @Create 2020年01月06日
 * @Title:
 * @Description:
 */
public class CreateThread1 {

    public static void threadMain1(){
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        t1.start();
        t2.start();
        System.out.println(t1.getName());
        System.out.println(t2.getName());
    }

    public static void threadMain2(){
        Thread t1 = new Thread();
        Thread t2 = new Thread(){
            @Override
            public void run(){
                System.out.println("------------------------");
            }
        };
        t1.start();
        t2.start();
        System.out.println(t1.getName());
        System.out.println(t2.getName());
    }

    public static void threadMain3(){
        Thread t3 = new Thread("MyName");
        t3.start();
        System.out.println(t3.getName());
        Thread t4 = new Thread(()->{
            System.out.println("Runnable.........");
        });
        System.out.println(t4.getName());
        Thread t5 = new Thread(()->{
            System.out.println("Runnable........." + Thread.currentThread().getName());
        },"RunnableThread");
        t5.start();
        System.out.println(t5.getName());// 执行输出的语句在start()之前 ？？？
    }

    public static void threadMain4(){
        Thread t = new Thread();
        t.start();
        System.out.println(t.getThreadGroup()); // t的 parent
        System.out.println(Thread.currentThread().getName());// main
        System.out.println(Thread.currentThread().getThreadGroup().getName());// main
    }

    public static void threadMain5(){
        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(1000*5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.activeCount());
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);

        // 第一种遍历方式
        for(Thread thread : threads){
            System.out.println(thread.getName());
        }
        // 第二种遍历方式
        Arrays.asList(threads).forEach(System.out::println);

    }

    public static void main(String[] args) {
//        threadMain1();
//        threadMain2();
//        threadMain3();
//        threadMain4();
        threadMain5();
    }
}
