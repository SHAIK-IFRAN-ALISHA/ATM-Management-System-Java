import java.util.ArrayList;

public class Account {

    private String cardNumber;
    private String holderName;
    private String pin;
    private double balance;

    private ArrayList<String> transactions;

    public Account(String cardNumber,
                   String holderName,
                   String pin,
                   double balance) {

        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.pin = pin;
        this.balance = balance;

        transactions = new ArrayList<>();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {

        if (amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }
}