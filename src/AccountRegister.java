import java.util.ArrayList;
import java.util.List;

public class AccountRegister {
    private List<Account> accounts = new ArrayList<>();

    public Account createAccount(String name, int startBalance){
        Account account = new Account(name, startBalance);
        accounts.add(account);
        return account;
    }

    public SavingsAccount createSavingsAccount(String name, int startBalance, double interestRate){
        SavingsAccount savingsAccount = new SavingsAccount(name, startBalance, interestRate);
        accounts.add(savingsAccount);
        return savingsAccount;
    }

    public void printAccounts() {
        if (accounts.size() > 0){
            for (Account account : accounts) {
                System.out.println("Account owner: " + account.getAccountHolder() + "Balance: " + account.getBalance());
            }
        } else {
            System.out.println("No registered accounts available.");
        }
    }

    public Account findAccount(String name) {
        for(Account account: accounts) {
            if (account.getAccountHolder().equals(name)) {
                return account;
            }
        }
        return null;
    }
}
