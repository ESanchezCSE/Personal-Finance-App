// Class Declaration for Advice
public class Advice {
    // Advice variables
    private int bracket;
    private String bracketAdvice;

    // Constructor Declaration for Advice
    public Advice(int bracket, String bracketAdvice){
        this.bracket = bracket;
        this.bracketAdvice = bracketAdvice
    }

    // Get Functions
    public int getBracket() {
        return bracket;
    }

    public String getBracketAdvice() {
        return bracketAdvice;
    }

    // Set Functions
    public void setBracket(int bracket) {
        this.bracket = bracket;
    }

    public void setBracketAdvice(String bracketAdvice) {
        this.bracketAdvice = bracketAdvice;
    }

    // Change Bracket Function
    public void changeBracket(int bracket) {

    }
}