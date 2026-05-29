import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== ATM MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Insert Card");
            System.out.println("3. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    insertCard();
                    break;

                case 3:
                    System.out.println("Thank You For Using ATM");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    private static void createAccount() {

        System.out.print("Enter Card Number: ");
        String cardNumber = sc.nextLine();

        for (Account acc : accounts) {
            if (acc.getCardNumber().equals(cardNumber)) {
                System.out.println("Card Number Already Exists!");
                return;
            }
        }

        System.out.print("Enter Account Holder Name: ");
        String holderName = sc.nextLine();

        System.out.print("Set 4-Digit PIN: ");
        String pin = sc.nextLine();

        System.out.print("Enter Initial Deposit Amount: ");
        double balance = sc.nextDouble();
        sc.nextLine();

        Account account =
                new Account(cardNumber,
                        holderName,
                        pin,
                        balance);

        account.addTransaction(
                Transaction.createEntry("ACCOUNT CREATED", balance));

        accounts.add(account);

        System.out.println("Account Created Successfully!");
    }

    private static void insertCard() {

        System.out.print("Enter Card Number: ");
        String cardNumber = sc.nextLine();

        Account account = null;

        for (Account acc : accounts) {
            if (acc.getCardNumber().equals(cardNumber)) {
                account = acc;
                break;
            }
        }

        if (account == null) {
            System.out.println("Card Not Found!");
            return;
        }

        int attempts = 3;

        while (attempts > 0) {

            System.out.print("Enter PIN: ");
            String pin = sc.nextLine();

            if (account.getPin().equals(pin)) {

                System.out.println(
                        "Login Successful. Welcome "
                                + account.getHolderName());

                atmMenu(account);
                return;
            }

            attempts--;

            System.out.println(
                    "Wrong PIN. Attempts Left: " + attempts);
        }

        System.out.println("Card Blocked!");
    }

    private static void atmMenu(Account account) {

        while (true) {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Fast Cash");
            System.out.println("5. Mini Statement");
            System.out.println("6. Change PIN");
            System.out.println("7. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.println(
                            "Current Balance: ₹"
                                    + account.getBalance());
                    break;

                case 2:

                    System.out.print("Enter Amount: ");
                    double deposit = sc.nextDouble();
                    sc.nextLine();

                    account.deposit(deposit);

                    account.addTransaction(
                            Transaction.createEntry(
                                    "DEPOSIT",
                                    deposit));

                    System.out.println(
                            "Deposit Successful");
                    break;

                case 3:

                    System.out.print("Enter Amount: ");
                    double withdraw = sc.nextDouble();
                    sc.nextLine();

                    if (account.withdraw(withdraw)) {

                        account.addTransaction(
                                Transaction.createEntry(
                                        "WITHDRAW",
                                        withdraw));

                        System.out.println(
                                "Withdrawal Successful");

                    } else {

                        System.out.println(
                                "Insufficient Balance");
                    }

                    break;

                case 4:

                    fastCash(account);
                    break;

                case 5:

                    miniStatement(account);
                    break;

                case 6:

                    changePin(account);
                    break;

                case 7:

                    System.out.println(
                            "Card Removed Successfully");
                    return;

                default:

                    System.out.println(
                            "Invalid Choice");
            }
        }
    }

    private static void fastCash(Account account) {

        System.out.println("\nFAST CASH");
        System.out.println("1. ₹500");
        System.out.println("2. ₹1000");
        System.out.println("3. ₹2000");
        System.out.println("4. ₹5000");

        int choice = sc.nextInt();
        sc.nextLine();

        int amount = 0;

        switch (choice) {
            case 1:
                amount = 500;
                break;
            case 2:
                amount = 1000;
                break;
            case 3:
                amount = 2000;
                break;
            case 4:
                amount = 5000;
                break;
            default:
                System.out.println("Invalid Choice");
                return;
        }

        if (account.withdraw(amount)) {

            account.addTransaction(
                    Transaction.createEntry(
                            "FAST CASH",
                            amount));

            System.out.println(
                    "Please Collect Cash: ₹"
                            + amount);

        } else {

            System.out.println(
                    "Insufficient Balance");
        }
    }

    private static void miniStatement(Account account) {

        System.out.println(
                "\n===== MINI STATEMENT =====");

        ArrayList<String> transactions =
                account.getTransactions();

        if (transactions.isEmpty()) {

            System.out.println(
                    "No Transactions Found");

        } else {

            for (String t : transactions) {
                System.out.println(t);
            }
        }
    }

    private static void changePin(Account account) {

        System.out.print("Enter Current PIN: ");
        String oldPin = sc.nextLine();

        if (!account.getPin().equals(oldPin)) {

            System.out.println(
                    "Incorrect Current PIN");
            return;
        }

        System.out.print("Enter New PIN: ");
        String newPin = sc.nextLine();

        account.setPin(newPin);

        System.out.println(
                "PIN Changed Successfully");
    }
}