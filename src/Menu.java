import java.util.Scanner;

public class Menu {
    private Account loggedinAccount = null;

    public Account showLoginMenu(Scanner scanner, AccountRegister accountRegister){
        int loginOption = 0;
        loggedinAccount = null;

        while (loggedinAccount == null && loginOption != 4)  {
            System.out.println("1: Create Account, 2: Login, 3: Admin Login, 4: Exit");
            loginOption = scanner.nextInt();
            scanner.nextLine();

            if (loginOption == 1) {
                int accountOption = 0;

                while (accountOption != 3) {
                    System.out.println("1: Account, 2: Savings Account, 3: Back");
                    accountOption = scanner.nextInt();
                    scanner.nextLine();
                    String name;
                    String code = null;
                    int startBalance;
                    int interestRate;

                    if (accountOption == 1 || accountOption == 2) {
                        System.out.println("Name: ");
                        name = scanner.nextLine();
                        while (accountRegister.findAccount(name) != null) {
                            System.out.println("Name already taken, please choose another: ");
                            name = scanner.nextLine();
                        }
                        boolean codeValid = false;
                        while (!codeValid) {
                            System.out.println("Code (4 digits): ");
                            String enteredCode = scanner.nextLine();
                            if (enteredCode.matches("\\d{4}")
                            ) {
                                code = enteredCode;
                                codeValid = true;
                                System.out.println("Code Valid.");
                            } else {
                                System.out.println("Must be exactly 4 digits");
                            }
                        }
                        System.out.println("Start value: ");
                        startBalance = scanner.nextInt();
                        scanner.nextLine();

                        while (startBalance < 0){
                            System.out.println("Please enter a valid number.");
                            startBalance = scanner.nextInt();
                            scanner.nextLine();
                        }

                        if (accountOption == 2) {
                            System.out.println("Interest rate (%): ");
                            interestRate = scanner.nextInt();
                            scanner.nextLine();

                            while (interestRate < 0) {
                                System.out.println("Interest rate can't be negative, please try again: ");
                                interestRate = scanner.nextInt();
                                scanner.nextLine();
                            }
                            loggedinAccount = accountRegister.createSavingsAccount(name, startBalance, code, interestRate);
                        } else {
                            loggedinAccount = accountRegister.createAccount(name, startBalance, code);
                        }

                        System.out.println("Account successfully created.");
                        return loggedinAccount;

                    } else if (accountOption != 3) {
                        System.out.println("Invalid input, try again.");
                    }
                }

            } else if (loginOption == 2) {
                System.out.println("Name: ");
                String enteredName = scanner.nextLine();
                System.out.println("Code (4 digits): ");
                String enteredCode = scanner.nextLine();
                Account account = accountRegister.findAccount(enteredName);

                if (account != null && account.getCode().equals(enteredCode)) {
                    loggedinAccount = account;
                    return loggedinAccount;
                } else {
                    System.out.println("Invalid name or code. Please try again.");
                }

            } else if (loginOption == 3) {
                System.out.println("Admin Name: ");
                String enteredName = scanner.nextLine();
                System.out.println("Admin Code (4 digits): ");
                String enteredCode = scanner.nextLine();
                AdminAccount adminAccount = new AdminAccount();

                if (adminAccount.checkLogin(enteredName, enteredCode)) {
                    System.out.println("Admin login successful.");
                     showAdminMenu(scanner, accountRegister);
                } else {
                    System.out.println("Invalid admin name or code. Please try again.");
                }

            } else if (loginOption == 4) {
                System.out.println("Terminating...");
            } else {
                System.out.println("Invalid input, try again.");
            }
        }
        return null;
    }
    public void showAccountMenu(Scanner scanner, AccountRegister accountRegister) {
        int menuOption;

        while (true) {
            if (loggedinAccount instanceof SavingsAccount) {
                System.out.println("1: Show account history, 2: Show Balance 3: Deposit, 4: Withdraw, 5: Apply Interest, 6: Logout");
            } else {
                System.out.println("1: Show account history, 2: Show Balance 3: Deposit, 4: Withdraw, 5: Logout");
            }
            menuOption = scanner.nextInt();
            scanner.nextLine();

            if (menuOption == 1) {
                System.out.println("Transaction history for " + loggedinAccount.getAccountHolder() + ":");
                loggedinAccount.printTransactionHistory();

            } else if(menuOption == 2) {
                System.out.println("Current balance: " + loggedinAccount.getBalance() + "$");

            }else if (menuOption == 3) {
                System.out.println("Amount: ");
                int amount = scanner.nextInt();
                scanner.nextLine();

                while (amount <= 0) {
                    System.out.println("Amount not valid, please try again: ");
                    amount = scanner.nextInt();
                    scanner.nextLine();
                }

                System.out.println(loggedinAccount.deposit(amount));
            } else if (menuOption == 4) {
                System.out.println("Amount: ");
                int amount = scanner.nextInt();
                scanner.nextLine();
                System.out.println(loggedinAccount.withdrawal(amount));
            } else if (menuOption == 5) {
                if (loggedinAccount instanceof SavingsAccount savingsAccount) {
                    savingsAccount.applyInterest();
                } else {
                    return;
                }
            } else if (menuOption == 6 &&
                    loggedinAccount instanceof SavingsAccount) {
                return;
            } else {
                System.out.println("Invalid input, try again.");
            }
        }

    }

    public void showAdminMenu(Scanner scanner, AccountRegister accountRegister) {
        while (true) {
            System.out.println("1: Show All Accounts, 2: Logout");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                accountRegister.printAccounts();
            } else if (option == 2) {
                return;
            } else {
                System.out.println("Invalid input, try again.");
            }
        }
    }
}
