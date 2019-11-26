package com.example.bookkeeper;

public class Expenses {
    double electricityBill;
    double waterBill;
    double garbageBill;
    double insuranceBill;
    double groceriesBill;
    double childCareBill;
    double phoneBill;
    double internetBill;
    double gymBill;
    double streamingBill;
    double subscriptionBill;
    double totalCostOfBills;

    public Expenses(){

    }

    public Expenses(double electricityBill, double waterBill, double garbageBill, double insuranceBill, double groceriesBill, double childCareBill, double phoneBill, double internetBill, double gymBill, double streamingBill, double subscriptionBill, double totalCostOfBills) {
        this.electricityBill = electricityBill;
        this.waterBill = waterBill;
        this.garbageBill = garbageBill;
        this.insuranceBill = insuranceBill;
        this.groceriesBill = groceriesBill;
        this.childCareBill = childCareBill;
        this.phoneBill = phoneBill;
        this.internetBill = internetBill;
        this.gymBill = gymBill;
        this.streamingBill = streamingBill;
        this.subscriptionBill = subscriptionBill;
        this.totalCostOfBills = totalCostOfBills;
    }

    public double getElectricityBill() {
        return electricityBill;
    }

    public double getWaterBill() {
        return waterBill;
    }

    public double getGarbageBill() {
        return garbageBill;
    }

    public double getInsuranceBill() {
        return insuranceBill;
    }

    public double getGroceriesBill() {
        return groceriesBill;
    }

    public double getChildCareBill() {
        return childCareBill;
    }

    public double getPhoneBill() {
        return phoneBill;
    }

    public double getInternetBill() {
        return internetBill;
    }

    public double getGymBill() {
        return gymBill;
    }

    public double getStreamingBill() {
        return streamingBill;
    }

    public double getSubscriptionBill() {
        return subscriptionBill;
    }

    public double getTotalCostOfBills() {
        return totalCostOfBills;
    }
}
