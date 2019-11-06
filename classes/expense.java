//Class Declartion for Expense
public class Expense {
    // Income variables
    private String expenseName;
    private float expenseAmount;
    private int expenseFrequency;
    private int expenseId;

    // Constructor Declaration of Income
    public Expense(String expenseName, float expenseAmount, int expenseFrequency, int expenseId) {
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.expenseFrequency = expenseFrequency;
        this.expenseId = expenseId;
    }

    // Get Functions
    public String getExpenseName() {
        return expenseName;
    }

    public float getExpenseAmount() {
        return expenseAmount;
    }

    public int getExpenseFrequency() {
        return expenseFrequency;
    }

    public int getExpenseId() {
        return expenseId;
    }

    // Set Functions
    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public void setExpenseAmount(float expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public void setExpenseFrequency(int expenseFrequency) { this.expenseFrequency = expenseFrequency; }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }
}