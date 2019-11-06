// Class Declaration for User
public class User {
    // User Variables
    private String email;
    private String password;
    private int userId;
    private int financial_bracket;

    //Constructor Declaration of User
    public User(String email, String password, int userId, int financial_bracket)
    {
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.financial_bracket = financial_bracket;
    }

    // Get Functions
    public String getEmail() {
        return email;
   }

    public String getPassword() {
        return password;
    }

    public int getUid() {
        return userId;
    }

    public int getFinancial_bracket() {
        return financial_bracket;
    }

    // Set Functions
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUid(int userId) {
        this.userId = userId;
    }

    public void setFinancial_bracket(int financial_bracket) {
        this.financial_bracket = financial_bracket;
    }

    // Add Income Function
    public void addIncome() {

    }

    // Add Expense Function
    public void addExpense() {

    }

    // Delete Income Function
    public void deleteIncome(int incomeId) {

    }

    // Delete Expense Function
    public void deleteExpense(int expenseId) {

    }

    // Modify Income Function
    public void modifyIncome(int incomeId) {

    }

    //Modify Expense Function
    public void modifyExpense(int expenseId) {

    }

    // View Advice Function
    public void viewAdvice() {

    }
}