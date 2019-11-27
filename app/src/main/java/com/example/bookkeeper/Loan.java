package com.example.bookkeeper;

public class Loan {
    double aLoanTotal;
    double interestAmount1;
    double interestRate1;
    double interestAmount2;
    double interestRate2;
    double interestAmount3;
    double interestRate3;
    double interestAmount4;
    double interestRate4;
    double interestAmount5;
    double interestRate5;
    double interestAmount6;
    double interestRate6;

    public Loan(){

    }

    public Loan(double aLoanTotal, double interestAmount1, double interestRate1, double interestAmount2, double interestRate2, double interestAmount3, double interestRate3, double interestAmount4, double interestRate4, double interestAmount5, double interestRate5, double interestAmount6, double interestRate6) {
        this.aLoanTotal = aLoanTotal;
        this.interestAmount1 = interestAmount1;
        this.interestRate1 = interestRate1;
        this.interestAmount2 = interestAmount2;
        this.interestRate2 = interestRate2;
        this.interestAmount3 = interestAmount3;
        this.interestRate3 = interestRate3;
        this.interestAmount4 = interestAmount4;
        this.interestRate4 = interestRate4;
        this.interestAmount5 = interestAmount5;
        this.interestRate5 = interestRate5;
        this.interestAmount6 = interestAmount6;
        this.interestRate6 = interestRate6;
    }

    public double getaLoanTotal() {
        return aLoanTotal;
    }

    public double getInterestAmount1() {
        return interestAmount1;
    }

    public double getInterestRate1() {
        return interestRate1;
    }

    public double getInterestAmount2() {
        return interestAmount2;
    }

    public double getInterestRate2() {
        return interestRate2;
    }

    public double getInterestAmount3() {
        return interestAmount3;
    }

    public double getInterestRate3() {
        return interestRate3;
    }

    public double getInterestAmount4() {
        return interestAmount4;
    }

    public double getInterestRate4() {
        return interestRate4;
    }

    public double getInterestAmount5() {
        return interestAmount5;
    }

    public double getInterestRate5() {
        return interestRate5;
    }

    public double getInterestAmount6() {
        return interestAmount6;
    }

    public double getInterestRate6() {
        return interestRate6;
    }
}
