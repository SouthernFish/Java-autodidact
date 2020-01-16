package com.Test2;

/**
 * @Author TR
 * @Create 2020年01月06日
 * @Title: TicketWindow
 * @Description: 叫号窗口
 */
public class TicketWindow extends Thread{

/*
    private final int MAX = 50;
    private int index = 1;// 每个柜台都有一份数据 各玩各的
*/

    private static final int MAX = 50;
    private static int index = 1;// 加入static 保证实例化一次 但是业务和线程混在一起的
    private final String name;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {// 业务实现的模块
        while (index <= MAX){
            System.out.println("柜台：" + name + "当前的号码是" + (index++));
        }
    }
}
