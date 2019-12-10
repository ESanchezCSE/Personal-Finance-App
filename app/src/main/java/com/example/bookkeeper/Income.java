package com.example.bookkeeper;

public class Income {
    double incomeMonthly;
    double savingsAmount;
    double rothIRAAmount;
    double match401Amount;

    public Income(){

    }

    public Income(double incomeMonthly, double savingsAmount, double rothIRAAmount, double match401Amount) {
        this.incomeMonthly = incomeMonthly;
        this.savingsAmount = savingsAmount;
        this.rothIRAAmount = rothIRAAmount;
        this.match401Amount = match401Amount;
    }

    public double getIncomeMonthly() {
        return incomeMonthly;
    }

    public double getSavingsAmount() {
        return savingsAmount;
    }

    public double getRothIRAAmount() {
        return rothIRAAmount;
    }

    public double getMatch401Amount() {
        return match401Amount;
    }
}
