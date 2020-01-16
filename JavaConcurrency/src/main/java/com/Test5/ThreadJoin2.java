package com.Test5;

/**
 * @Author TR
 * @Create 2020年01月07日
 * @Title:
 * @Description:
 */
public class ThreadJoin2 {

    public static void ThreadJoinMain1(){
        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunnable("M1",10000L));
        Thread t2 = new Thread(new CaptureRunnable("M2",30000L));
        Thread t3 = new Thread(new CaptureRunnable("M3",15000L));
        t1.start();t2.start();t3.start();
        try {
            t1.join();t2.join();t3.join();// 没加之前 数据还没取出来就已经保存完成了
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("save data begin timestamp is: %s, end timestamp is: %s\n", startTime, endTime);
    }

    public static void main(String[] args) {
        ThreadJoinMain1();
    }
}

class CaptureRunnable implements Runnable{

    private String machineName;

    private long spendTime;

    public CaptureRunnable (String machineName, long spendTime){
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        // 模拟做真正的事情
        try {
            Thread.sleep(spendTime);
            System.out.printf(machineName + " completed data capture at timestamp [%s] and successfully.\n", System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult(){

        return machineName + " Finished.";
    }
}