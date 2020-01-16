package com.Test2;

/**
 * @Author TR
 * @Create 2020年01月06日
 * @Title: TicketWindowRunnable
 * @Description: 实现业务和线程分离
 */
public class TicketWindowRunnable implements Runnable{

    private static final int MAX = 50;
    private int index = 1;// 加入static 保证实例化一次 但是业务和线程混在一起的

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println(Thread.currentThread() + "的号码是:" + (index++));
            try {
                Thread.sleep(1000*5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
