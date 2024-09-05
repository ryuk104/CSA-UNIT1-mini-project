import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private SimpleBankingSystem bankAccount;

    // Constructor for the User class
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bankAccount = new SimpleBankingSystem();  // Each user has their own bank account
    }

    // Getters for username and password
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Get the bank account associated with the user
    public SimpleBankingSystem getBankAccount() {
        return bankAccount;
    }
}

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
}

class BankApp {
    private HashMap<String, User> users;
    private Scanner scanner;

    // Constructor to initialize users map and scanner
    public BankApp() {
        users = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    // Method to register a new user
    public void register() {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Try again.");
            return;
        }

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        users.put(username, new User(username, password));
        System.out.println("Registration successful!");
    }

    // Method for users to login
    public User login() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            System.out.println("Login successful! Welcome " + username);
            return users.get(username);
        } else {
            System.out.println("Invalid username or password. Try again.");
            return null;
        }
    }

    // Method to start the banking system with login/register options
    public void start() {
        while (true) {
            System.out.println("\n=== Welcome to Simple Bank App ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    User loggedInUser = login();
                    if (loggedInUser != null) {
                        runBankingSystem(loggedInUser.getBankAccount());
                    }
                    break;
                case 3:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to handle the banking system for a logged-in user
    private void runBankingSystem(SimpleBankingSystem bankAccount) {
        while (true) {
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Display Balance");
            System.out.println("4. Display Transactions");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    bankAccount.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    bankAccount.withdraw(withdrawAmount);
                    break;
                case 3:
                    bankAccount.displayBalance();
                    break;
                case 4:
                    bankAccount.displayTransactions();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        BankApp app = new BankApp();
        app.start();
    }
}
