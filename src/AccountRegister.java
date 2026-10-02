import java.util.ArrayList;
import java.util.List;

public class AccountRegister {
    private List<Account> accounts = new ArrayList<>();

    public void createAccount(String name, int startBalance){
        Account account = new Account(name, startBalance);
        accounts.add(account);
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
}
