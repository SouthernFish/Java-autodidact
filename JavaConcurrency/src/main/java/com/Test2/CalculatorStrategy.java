package com.Test2;

/**
 * @Author TR
 * @Create 2020年01月06日
 * @Title: CalculatorStrategy
 * @Description:  抽离计算税率的方法
 */
@FunctionalInterface
public interface CalculatorStrategy {

    double calculate(double salary, double bonus);
}
