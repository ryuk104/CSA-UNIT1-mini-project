import java.util.ArrayList;
import java.util.Scanner;

class SimpleBankingSystem {
    private double balance;
    private ArrayList<String> transactions;

    // Constructor to initialize balance and transactions list
    public SimpleBankingSystem() {
        balance = 0.0;
        transactions = new ArrayList<>();
    }

    // Method to deposit money and add transaction
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: $" + amount);
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money and add transaction
    public void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            transactions.add("Withdrew: $" + amount);
            System.out.println("Successfully withdrew: $" + amount);
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    // Method to display the current balance
    public void displayBalance() {
        System.out.println("Current Balance: $" + balance);
    }

    // Method to display all transactions using a for loop
    public void displayTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions to display.");
        } else {
            System.out.println("Transaction History:");
            for (int i = 0; i < transactions.size(); i++) {
                System.out.println((i + 1) + ". " + transactions.get(i));  // Using a for loop
            }
        }
    }



    // Main method to run the banking system
    public static void main(String[] args) {
        SimpleBankingSystem bank = new SimpleBankingSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Using a while loop to keep the program running until the user chooses to exit
        while (true) {
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Display Balance");
            System.out.println("4. Display Transactions");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    bank.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    bank.withdraw(withdrawAmount);
                    break;
                case 3:
                    bank.displayBalance();
                    break;
                case 4:
                    bank.displayTransactions();
                    break;
                case 5:
                    System.out.println("Exiting the banking system...");
                    scanner.close();
                    return;  // Exit the program
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
