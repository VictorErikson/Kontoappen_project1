public class SavingsAccount extends Account{
    // private double interest = 0.02;
    private double interestRate;

    public SavingsAccount(String accountHolder, int startBalance, double interestRate) {
        super(accountHolder, startBalance);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double extra = getBalance() * interestRate;
        deposit(extra);
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Interest rate: " + interestRate);
    }
    /*
    public void depositSavings (int amount){
        savingsBalance = savingsBalance + amount
    }

    public double getSavingsBalance(){
        return savingsBalance;
    }

    public boolean transferFromSavings (int amount){
        if (amount <= savingsBalance){
            savingsBalance = savingsBalance + amount;
            return true;
        } else {
            return false;
        }
    }
    */
}
