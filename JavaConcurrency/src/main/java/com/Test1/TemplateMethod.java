package com.Test1;

/**
 * @Author TR
 * @Create 2020年01月06日
 * @Title: TemplateMethod start()源码 其模板方法的
 * @Description:  作为模板方法的时候需要写成抽象 abstract 类
 */
public class TemplateMethod {

    public final void print(String message){
        System.out.println("########################");
        wrapPrint(message);
        System.out.println("########################");
    }

    protected void wrapPrint(String message){
        // 不想暴露给子类 只给自己使用 protected
        // 可以空实现
        // 作为模板方法的时候需要写成抽象的方法，此时类也要是抽象的
    }

    public static void main(String[] args){
        TemplateMethod t1 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String message){
                System.out.println("*"+message+"*");
            };
        };
        t1.print("Hello Thread");
        TemplateMethod t2 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String message){
                System.out.println("+"+message+"+");
            };
        };
        t2.print("Hello Thread");

    }
}
