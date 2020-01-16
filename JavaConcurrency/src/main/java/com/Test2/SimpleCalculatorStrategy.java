package com.Test2;

/**
 * @Author TR
 * @Create 2020年01月06日
 * @Title:
 * @Description:
 */
public class SimpleCalculatorStrategy implements CalculatorStrategy {

    private final double SALARY_RATE = 0.1;
    private final double BONUS_RATE = 0.15;
    @Override
    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }
}
