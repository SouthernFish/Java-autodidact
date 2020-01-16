package com.Test1;

/**
 * @Author TR
 * @Create 2020年01月06日
 * @Title:
 * @Description:
 */
public class TryConcurrency {
    public void readDataBase(){
        try {
            System.out.println("Read DataBase Start");
            Thread.sleep(1000*300L);
            System.out.println("Read DataBase Done And Handle it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Read DataBase handle is successfully");
    }

    public void writeToFile(){
        try {
            System.out.println("Write Data To File Start");
            Thread.sleep(1000*30L);
            System.out.println("Write Data To File Done And Handle it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Write Data To File handle is successfully");
    }

    public void TryConcurrencyMain(){

        Thread t = new Thread("TEST-THREAD"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
//        t.start();
//        t.start();// 不能调用两次  输出TEST-THREAD
        t.run(); // 输出 main

        new Thread("READ-THREAD"){
            @Override
            public void run() {
                readDataBase();
            }
        }.start();

        new Thread("WRITE-THREAD"){
            @Override
            public void run() {
                writeToFile();
            }
        }.start();

    }
}
