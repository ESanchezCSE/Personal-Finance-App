//Class Declartion for Income
public class Income {
    // Income variables
    private String incomeName;
    private float incomeAmount;
    private int incomeFrequency;
    private int incomeId;

    // Constructor Declaration of Income
    public Income(String incomeName, float incomeAmount, int incomeFrequency, int incomeId) {
        this.incomeName = incomeName;
        this.incomeAmount = incomeAmount;
        this.incomeFrequency = incomeFrequency;
        this.incomeId = incomeId;
    }

    // Get Functions
    public String getIncomeName() {
        return incomeName;
    }

    public float getIncomeAmount() {
        return incomeAmount;
    }

    public int getIncomeFrequency() {
        return incomeFrequency;
    }

    public int getIncomeId() {
        return incomeId;
    }

    // Set Functions
    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public void setIncomeAmount(float incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public void setIncomeFrequency(int incomeFrequency) {
        this.incomeFrequency = incomeFrequency;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }
}
