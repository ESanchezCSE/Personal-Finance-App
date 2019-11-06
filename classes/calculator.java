// Class Declaration for Calculator
public class Calculator {
    // Calculator variables
    private float netMoney;     //total income - total expense
    private float totalIncome;  //all user incomes added together
    private float totalExpense; //all user expenses added together
    private float netMonth;     //
    private float netYear;      //

    // Constructor Declaration for Calculator
    public Calculator(float netMoney, float totalIncome, float totalExpense, float netMonth, float netYear) {
        this.netMoney = netMoney;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.netMonth = netMonth;
        this.netYear = netYear;
    }

    // Get Functions
    public float getNetMoney() {
        return netMoney;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public float getTotalExpense() {
        return totalExpense;
    }

    public float getNetMonth() { return netMonth; }

    public float getNetYear() { return netYear }

    //Set Functions
    public void setNetMoney(float netMoney) {
        this.netMoney = netMoney;
    }

    public void setTotalIncome(float totalIncome) {
        this.totalIncome = totalIncome;
    }

    public void setTotalExpense(float totalExpense) {
        this.totalExpense = totalExpense;
    }

    public void setNetMonth(float netMonth) { this.netMonth = netMonth; }

    public void setNetYear(float netYear) { this.netYear = netYear; }

    // Set User bracket Function
    public void setBracket(int userId) {

    }

    // Calculates User's net money
    public float calcNetMoney(float totalIncome, float totalExpense) {
        this.netMoney = totalIncome - totalExpense;
        return this.netMoney;
    }

    // Calculates User's net money for the month
    public float calcNetMonth(float netMoney) {
        this.netMonth = (netMoney/7) * 31;
        return this.netMonth;
    }

    // Calculates User's net money for the year
    public float calcNetYeat(float netMoney) {
        this.netYear = (netMoney/7) * 365;
        return this.netYear;
    }
}