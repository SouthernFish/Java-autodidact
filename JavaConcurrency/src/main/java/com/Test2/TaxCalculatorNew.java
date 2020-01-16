package com.Test2;

/**
 * @Author TR
 * @Create 2020年01月06日
 * @Title: TaxCalculatorNew
 * @Description:  使用了java8 的新特性
 */
public class TaxCalculatorNew {
    private double salary;
    private double bonus;
    private CalculatorStrategy calculatorStrategy;

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public TaxCalculatorNew(double salary, double bonus, CalculatorStrategy calculatorStrategy){
        this.bonus = bonus;
        this.salary = salary;
        this.calculatorStrategy = calculatorStrategy;
    }

    protected double calculateTax(){
        return calculatorStrategy.calculate(salary, bonus);
    }

    public double calculate() {
        return this.calculateTax();
    }
}
