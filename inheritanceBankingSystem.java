class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    // Method 1
    void createAccount(String name, int accNo, double bal) {
        accountHolder = name;
        accountNumber = accNo;
        balance = bal;
    }

    // Method 2
    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    // Method 3
    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    // Method 4
    void checkBalance() {
        System.out.println("Balance: " + balance);
    }

    // Method 5
    void displayDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
    }
}

// Child Class
class SavingsAccount extends BankAccount {

    void addInterest() {
        double interest = balance * 0.05;
        balance += interest;
        System.out.println("Interest Added: " + interest);
    }
}

public class inheritanceBankingSystem {
    public static void main(String[] args) {

        SavingsAccount s1 = new SavingsAccount();

        s1.createAccount("Muskan", 101, 10000);

        s1.displayDetails();
        s1.checkBalance();

        s1.deposit(5000);
        s1.withdraw(2000);

        s1.addInterest();

        s1.checkBalance();
    }
}