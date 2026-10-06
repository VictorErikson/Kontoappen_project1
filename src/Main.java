import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AccountRegister accountRegister = new AccountRegister();
        Menu menu = new Menu();
        boolean running = true;

        while (running) {
            Account loggedInAccount = menu.showLoginMenu(scanner, accountRegister);

            if (loggedInAccount != null) {
                menu.showAccountMenu(scanner, accountRegister);
            } else {
                running = false;
            }
        }

    }
}