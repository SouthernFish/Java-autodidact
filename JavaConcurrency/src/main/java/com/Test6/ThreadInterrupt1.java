package com.Test6;

/**
 * @Author TR
 * @Create 2020年01月07日
 * @Title:
 * @Description:
 */
public class ThreadInterrupt1 {

    public static void threadInterruptMain1(){
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){

                    System.out.println(">> "+ this.isInterrupted());
                }
            }
        };
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.isInterrupted());
        t1.interrupt();
        System.out.println(t1.isInterrupted());
    }

    public static void threadInterruptMain2(){
        Thread t1 = new Thread(){
            @Override
            public void run() {

                while (true){
                    synchronized (ThreadInterrupt1.class){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //System.out.println(">> "+ this.isInterrupted());
                }
            }
        };
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.isInterrupted());
        t1.interrupt();
        System.out.println(t1.isInterrupted());
    }

    public static void main(String[] args) {
        threadInterruptMain1();
        threadInterruptMain2();
    }
}
