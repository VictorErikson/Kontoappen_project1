import java.util.ArrayList;
import java.util.List;

public class AccountRegister {
    private List<Account> accounts = new ArrayList<>();

    public void createAccount(String name, int startBalance){
        Account account = new Account(name, startBalance);
        accounts.add(account);
    }

    public void getAccounts() {

    }
}
