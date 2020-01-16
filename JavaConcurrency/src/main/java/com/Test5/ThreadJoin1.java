package com.Test5;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @Author TR
 * @Create 2020年01月07日
 * @Title:
 * @Description:
 */
public class ThreadJoin1 {

    public static void threadJoinMain1(){// main 和 Thread-0 交替的打印名字
        Thread t = new Thread( () -> {
            IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"-"+i));
        });
        t.start();
        try {
            t.join(); // 不做join之前 main 和 Thread-0 交替的打印名字
                      // 调用join之后会先将Thread-0线程的业务执行完再去执行main线程的业务
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"-"+i));
    }

    public static void threadJoinMain2() throws InterruptedException{// main 和 Thread-0 交替的打印名字
        Thread t1 = new Thread( () -> {
            IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"-"+i));
        });
        Thread t2 = new Thread( () -> {
            IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"-"+i));
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // t1 t2 相对于当前线程（main）会执行完后才执行 main线程的业务逻辑，但t1和t2还是会交替执行，否则就是串行执行，不是多线程
        Optional.of("All Thread Finish Done").ifPresent(System.out::println);
        IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"-"+i));
    }

    public static void threadJoinMain3() throws InterruptedException{// main 和 Thread-0 交替的打印名字
        Thread t1 = new Thread( () -> {
            try {
                System.out.println("t1 Thread Running......");
                Thread.sleep(10_000);
                System.out.println("t1 Thread Done......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t1.join(100,10);// main结束后等100ms 10纳秒 如果还不能执行完 就不等了

        Optional.of("All Thread Finish Done").ifPresent(System.out::println);
        IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"-"+i));

//        Thread.currentThread().join(); // 当前线程是main线程  main线程一直在等main线程结束
    }


    public static void main(String[] args) {

        try {
            threadJoinMain1();
            threadJoinMain2();
            threadJoinMain3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
