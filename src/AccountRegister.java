import java.util.ArrayList;
import java.util.List;

public class AccountRegister {
    private final List<Account> accounts = new ArrayList<>();

    public Account createAccount(String name, int startBalance, String code){
        Account account = new Account(name, startBalance, code);
        accounts.add(account);
        return account;
    }

    public SavingsAccount createSavingsAccount(String name, int startBalance,String code, int interestRate){
        SavingsAccount savingsAccount = new SavingsAccount(name, startBalance, code, interestRate);
        accounts.add(savingsAccount);
        return savingsAccount;
    }

    public void printAccounts() {
        if (accounts.size() > 0){
            for (Account account : accounts) {
                System.out.println("Account owner: " + account.getAccountHolder() + ", Account type: " + account.getType() + ", Balance: " + account.getBalance() + "$");
            }
        } else {
            System.out.println("No registered accounts available.");
        }
    }

    public Account findAccount(String name) {
        for(Account account: accounts) {
            if (account.getAccountHolder().equalsIgnoreCase(name)) {
                return account;
            }
        }
        return null;
    }
}
