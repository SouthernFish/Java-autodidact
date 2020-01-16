package com.Test2;

/**
 * @Author TR
 * @Create 2020年01月06日
 * @Title: Bank
 * @Description: 银行大厅
 */
public class Bank {

    private final static int MAX_NUMBER = 50;

    public static void ticketWindowMain(){
        TicketWindow ticketWindow1 = new TicketWindow("一号柜台");
        ticketWindow1.start();
        TicketWindow ticketWindow2 = new TicketWindow("二号柜台");
        ticketWindow2.start();
        TicketWindow ticketWindow3 = new TicketWindow("三号柜台");
        ticketWindow3.start();
    }

    public static void ticketWindowRunnableMain1(){
        final TicketWindowRunnable ticketWindow = new TicketWindowRunnable();
        Thread windowThread1 = new Thread(ticketWindow,"一号窗口");
        Thread windowThread2 = new Thread(ticketWindow,"二号窗口");
        Thread windowThread3 = new Thread(ticketWindow,"三号窗口");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();

    }

    public static void ticketWindowRunnableMain2(){// 使用java8的新特性
        final Runnable ticketWindow = ()->{
            int index = 1;
            while (index <= MAX_NUMBER){
                System.out.println(Thread.currentThread() + "的号码是:" + (index++));
                try {
                    Thread.sleep(1000*5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread windowThread1 = new Thread(ticketWindow,"一号窗口");
        Thread windowThread2 = new Thread(ticketWindow,"二号窗口");
        Thread windowThread3 = new Thread(ticketWindow,"三号窗口");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();

    }

    public static void TaxCalculatorMain1(){

       // 如果计算要改变税率的话 会改变整个程序 因此还需要将计算税率的方法抽离出来 CalculatorStrategy
        final TaxCalculator calculator = new TaxCalculator(10000d,2000d){
            @Override
            protected double calculateTax() {
                return getSalary() * 0.1 + getBonus() * 0.15;
            }
        };
        double tax = calculator.calculate();
        System.out.println(tax);

    }
    public static void TaxCalculatorMain2(){
        // 使用策略方法 当税率改变只需要改变 SimpleCalculatorStrategy 就可以
        TaxCalculator calculator = new TaxCalculator(10000d,2000d);
        CalculatorStrategy calculatorStrategy = new SimpleCalculatorStrategy();
        calculator.setCalculatorStrategy(calculatorStrategy);
        System.out.println(calculator.calculate());

    }

    public static void TaxCalculatorMain3(){
        // 使用java8 的新特性
        TaxCalculatorNew calculator = new TaxCalculatorNew(10000d,2000d,(s,b)-> s * 0.1 + b * 0.15);
        System.out.println(calculator.calculate());

    }

    public static void main(String[] args){
//        ticketWindowMain();
//        ticketWindowRunnableMain1();
//        ticketWindowRunnableMain2();
//        TaxCalculatorMain1();
//        TaxCalculatorMain2();
        TaxCalculatorMain3();
    }
}
