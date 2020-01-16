package com.Test2;

/**
 * @Author TR
 * @Create 2020年01月06日
 * @Title: TaxCalculator
 * @Description:
 */
public class TaxCalculator {

    private double salary;
    private double bonus;


    private CalculatorStrategy calculatorStrategy;

    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public TaxCalculator(double salary, double bonus){
        this.bonus = bonus;
        this.salary = salary;
    }

    protected double calculateTax(){
        return calculatorStrategy.calculate(salary, bonus);
    }

    public double calculate() {
        return this.calculateTax();
    }
}
