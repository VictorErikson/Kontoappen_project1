
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String accountHolder;
    private double balance;
    private final String code;
    private final List<String> transactionHistory = new ArrayList<>();

    public Account( String accountHolder, int startBalance, String code){
        this.accountHolder = accountHolder;
        this.balance = startBalance;
        this.code = code;
        this.transactionHistory.add("Account created with starting balance: " + startBalance + "$");
    }

    public String getAccountHolder(){
        return accountHolder;
    }

    public boolean checkCode(String enteredCode){
        return code.equals(enteredCode);
    }

    public double getBalance(){
        return balance;
    }

    public String getType(){
        return "Regular";
    }

    protected void addTransactionhistory(String action){
        transactionHistory.add(action);
    }

    public String withdrawal(int amount){
        if(amount <= 0){
            return "Withdrawal denied: amount must be greater than 0.";
        }
        if(amount <= balance){
            balance = balance - amount;
            addTransactionhistory("Withdrawal of " + amount + "$, current balance is: " + balance + "$");
            return "Withdrawal of " + amount + "$ succeeded, current balance is:" + balance + "$";
        }else {
            addTransactionhistory("Withdrawal of " + amount + "$ failed, current balance is: " + balance + "$");
            return "Withdrawal denied: insufficient funds, current balance is: " + balance + "$";
        }
    }

    protected void addToBalance(double amount){
        balance = balance + amount;
    }

    public String deposit(double amount){
        if(amount <= 0){
            return "Deposit denied: amount must be greater than 0.";
        }
        addToBalance(amount);
        addTransactionhistory("Deposit of " + amount + "$, current balance is: " + balance + "$");
        return "Deposit succeeded, current balance is: " + balance;
    }
    public void printTransactionHistory() {
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void printInfo(){
        System.out.println("Account owner: " + accountHolder + ", Balance: " + balance);
    }

}
