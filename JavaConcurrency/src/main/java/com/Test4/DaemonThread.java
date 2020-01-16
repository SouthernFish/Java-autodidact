package com.Test4;

/**
 * @Author TR
 * @Create 2020年01月07日
 * @Title:
 * @Description: 守护线程
 */
public class DaemonThread {

    public static void daemonThreadMain1(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" Running......");
                    Thread.sleep(1000*10L);
                    System.out.println(Thread.currentThread().getName()+" Done......");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // runnable --> running --> dead --> blocked
        t.setDaemon(true);// 将main线程设置成守护线程 main线程结束，t1就结束了
        t.start();
//        Thread.sleep(50_000); // jdk 1.7 jconsole可以看到 main线程和 Thread-0 线程共存时 main 线程处于Waiting状态
        System.out.println(Thread.currentThread().getName());// Thread-0
    }

    public static void daemonThreadMain2(){
        Thread t = new Thread(() ->{
           Thread innerThread = new Thread(() -> {
               try {
                   System.out.println(Thread.currentThread().getName()+" Running......");
                   Thread.sleep(1000*10L);
                   System.out.println(Thread.currentThread().getName()+" Done......");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           });
            innerThread.setDaemon(true);// 内部线程设置为守护线程 t死掉了就不会innerThread也就自动关闭了
            innerThread.start();
            try {
                Thread.sleep(1_000);
                System.out.println("T Thread Finish Done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        System.out.println(Thread.currentThread().getName());// Thread-0
    }

    public static void main(String[] args) {
//        daemonThreadMain1();
        daemonThreadMain2();
    }

    /*
    * A <---------------------------------------> B 每隔一段时间发一段心跳
    * --> DaemonThread(health check)
    */
}
