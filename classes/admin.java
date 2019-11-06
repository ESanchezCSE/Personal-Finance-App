// Class Declaration for Admin
public class Admin {
    // Admin variables
    private String email;
    private String password;
    private int adminId;

    // Constructor Declaration of Admin
    public Admin(String email, String password, int adminId) {
        this.email = email;
        this.password = password;
        this.adminId = adminId;
    }

    // Get Functions
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAdminId() {
        return adminId;
    }

    // Set Functions
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    // User Functions
    // Get User's net value
    public float getUserNet(int userId) {

    }

    // Get User's income
    public float getUserIncome(int userId) {

    }

    // Get User's expense
    public float getUserExpense(int userId) {

    }

    // Set User's advice
    public void setAdvice(int userId) {

    }

    // Add new User
    public void addUser() {

    }

    // Delete a User
    public void deleteUser(int userId) {

    }
}