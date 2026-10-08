public class SavingsAccount extends Account{
    private int interestRate;

    public SavingsAccount(String accountHolder, int startBalance, String code, int interestRate) {
        super(accountHolder, startBalance, code);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double extra = getBalance() * (interestRate / 100.0);
        addToBalance(extra); //Kan inte använda deposit eftersom det lägger till en transaktion i historiken, vilket inte är önskvärt för ränta.
        String interestTransaction = "Interest of " + extra + "$, added, new balance: " + getBalance() + "$";
        System.out.println(interestTransaction);
        this.addTransactionhistory(interestTransaction);
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Interest rate: " + interestRate);
    }

    public String getType() {
        return "Savings";
    }
}
