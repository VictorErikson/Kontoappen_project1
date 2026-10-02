public class Account {
    private final String accountHolder;
    private double balance;

    public Account( String accountHolder, int startBalance){
        this.accountHolder = accountHolder;
        this.balance = startBalance;
    }

    public String getAccountHolder(){
        return accountHolder;
    }

    public double getBalance(){
        return balance;
    }

    public String withdrawal(int amount){
        if(amount <= balance){
            balance = balance - amount;
            return "Withdrawal of " + amount + "$ succeeded, current balance is:" + balance + "$";
        }else {
            return "Withdrawal denied.";
        }
    }

    public String deposit(int amount){
        balance = balance + amount;
        return "Deposit secceeded, current balance is: " + balance;
    }
}
