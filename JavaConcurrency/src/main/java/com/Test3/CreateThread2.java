package com.Test3;

/**
 * @Author TR
 * @Create 2020年01月07日
 * @Title:
 * @Description:
 */
public class CreateThread2 {

    private int i= 0;
    private byte[] bytes = new byte[1024];
    private static int counter = 0;// 栈针个数
    // 递归 recursion 示例
    private static void add(int i){
        ++counter;
        add(i + 1);
    }

    public static void threadMain1(){
        try {
            add(0);// 压栈 因为没有返回会一直压栈 捕捉异常 StackOverflowError
        }catch (Error e){
            e.printStackTrace();
            System.out.println(counter);
        }
    }


    public  static void threadMain2(){

        // Thread(ThreadGroup group, Runnable target, String Name, Long stackSize)
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    add(1);// 压栈 因为没有返回会一直压栈 捕捉异常 StackOverflowError
                }catch (Error e){
                    System.out.println(counter);
                }
            }

            private void add(int i){
                counter++;
                add(i + 1);
            }
        });
        t.start();
    }

    public  static void threadMain3(){
        // Thread(ThreadGroup group, Runnable target, String Name, Long stackSize)
        Thread t = new Thread(null,new Runnable() {
            @Override
            public void run() {
                try {
                    add(1);// 压栈 因为没有返回会一直压栈 捕捉异常 StackOverflowError
                }catch (Error e){
                    System.out.println(counter);
                }
            }

            private void add(int i){
                counter++;
                add(i + 1);
            }
        },"recursion",1 << 24);// stackSize 1 右移 24
        t.start();
    }

    public  static void threadMain4(){
        // Thread(ThreadGroup group, Runnable target, String Name, Long stackSize)
        try {
            for(int i = 0; i < Integer.MAX_VALUE; i++){
                counter++;
                new Thread(() -> {
                    byte[] bytes = new byte[1024*1024*1024];// 增加栈针宽度
                    while(true) {
                       /* try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }
                }).start();
            }
        }catch (Error e){
//            e.printStackTrace();
        }
        System.out.println("Total Created Thread Number >> "+counter);
    }

    // JVM会创建一个线程 main
    public static void main(String[] args) {
        // 此时就已经开辟了一个虚拟机栈 stack
        int j = 0; // 局部变量表里
        int arr[] = new int[1024];// 引用在局部变量表中，但是数据存在放在堆内存中
//        threadMain1();
//        threadMain2();
//        threadMain3();
        threadMain4();
    }
}
