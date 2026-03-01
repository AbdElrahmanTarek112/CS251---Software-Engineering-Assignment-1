package ui;

import BankCore.*;
import bankCommons.Date;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class main {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    private static Customer currentCustomer = null;
    private static Admin currentAdmin = null;

    public static void main(String[] args) {

        BankIO.startSystem();

        System.out.println("=================================");
        System.out.println("    WELCOME TO BANKING SYSTEM    ");
        System.out.println("=================================");

        mainMenu();
    }


    private static void mainMenu() {
        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Login");
            System.out.println("2. Register as Customer");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    registerCustomer();
                    break;
                case 3:
                    System.out.println("Thank you for using our banking system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentUser = BankManager.getInstance().login(username, password);

        if (currentUser == null) {
            System.out.println("Invalid username or password.");
            return;
        }

        // Check if user is admin or customer
        if (currentUser instanceof Admin) {
            currentAdmin = (Admin) currentUser;
            adminMenu();
        } else if (currentUser instanceof Customer) {
            currentCustomer = (Customer) currentUser;
            customerMenu();
        }
    }

    private static void adminMenu() {
        System.out.println("\n=== ADMIN MENU ===");
        System.out.println("Welcome, " + currentAdmin.getFirstName() + " " + currentAdmin.getLastName());

        while (true) {
            System.out.println("\n--- ADMIN OPTIONS ---");
            System.out.println("1. Rollback Transaction");
            System.out.println("2. View All Transactions");
            System.out.println("3. View All Users");
            System.out.println("4. View All Cards");
            System.out.println("5. View All Issues");
            System.out.println("6. Logout");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    rollbackTransaction();
                    break;
                case 2:
                    viewAllTransactions();
                    break;
                case 3:
                    viewAllUsers();
                    break;
                case 4:
                    viewAllCards();
                    break;

                case 5:
                    viewAllIssues();
                    break;
                case 6:
                    logout();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void customerMenu() {
        System.out.println("\n=== CUSTOMER MENU ===");
        System.out.println("Welcome, " + currentCustomer.getFirstName() + " " + currentCustomer.getLastName());

        while (true) {
            System.out.println("\n--- CUSTOMER OPTIONS ---");
            System.out.println("1. View My Cards");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Money");
            System.out.println("6. Report Issue");
            System.out.println("7. Change Password");
            System.out.println("8. Logout");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    viewMyCards();
                    break;
                case 2:
                    checkBalance();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    transferMoney();
                    break;
                case 6:
                    reportIssue();
                    break;
                case 7:
                    changePassword();
                    break;
                case 8:
                    logout();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // ==================== ADMIN METHODS ====================

    private static void rollbackTransaction() {
        System.out.println("\n--- ROLLBACK TRANSACTION ---");
        System.out.print("Enter Transaction ID to rollback: ");
        String transactionId = scanner.nextLine();
        System.out.print("Enter your password to confirm: ");
        String password = scanner.nextLine();

        try {
            currentAdmin.rollBackTransaction(transactionId, password);
            System.out.println("Transaction rolled back successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllTransactions() {
        System.out.println("\n--- ALL TRANSACTIONS ---");


        Map<String, Transaction> ts = BankManager.getInstance().getTransactionsMap();
        for (Map.Entry<String, Transaction> entry : ts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
        }
        //System.out.println("This feature is coming soon!");
    }

    private static void viewAllUsers() {
        System.out.println("\n--- ALL USERS ---");

        System.out.println("This feature is coming soon!");
    }

    private static void viewAllCards() {
        System.out.println("\n--- ALL CARDS ---");

        System.out.println("This feature is coming soon!");
    }

    private static void viewAllIssues() {
        System.out.println("\n--- ALL ISSUES ---");

        Map<String, Issue> ts = BankManager.getInstance().getIssuesMap();
        for (Map.Entry<String, Issue> entry : ts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
        }

    }

    // ==================== CUSTOMER METHODS ====================

    // Add this method to BankingTUI.java
    private static void registerCustomer() {
        System.out.println("\n=== CUSTOMER REGISTRATION ===");

        // Get user information
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password (min 6 characters): ");
        String password = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("National ID: ");
        String nationalId = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Security Question (e.g., 'What is your mother's maiden name?'): ");
        String securityQuestion = scanner.nextLine();

        System.out.print("Security Answer: ");
        String securityAnswer = scanner.nextLine();

        // Card creation
        System.out.println("\n--- Create Your First Card ---");
        System.out.println("1. Create a new card");
        System.out.println("2. Skip for now");
        System.out.print("Choose option: ");

        Card newCard = null;
        int cardChoice = getIntInput();

        if (cardChoice == 1) {
            newCard = createNewCard(nationalId);
        }

        try {
            Customer registeredCustomer = BankManager.getInstance().registerCustomer(
                    username, email, password, firstName, lastName,
                    nationalId, securityQuestion, securityAnswer, phone, newCard
            );

            System.out.println("\n✅ Registration successful! You can now login.");

            if (newCard != null) {
                System.out.println("Your card number: " + newCard.getCardNumber());
                System.out.println("⚠️  IMPORTANT: Please remember your PIN!");
            }

        } catch (Exception e) {
            System.out.println("\n❌ Registration failed: " + e.getMessage());
        }
    }

    // Helper method to create a new card
    private static Card createNewCard( String ownerId) {
        System.out.println("\n--- Card Creation ---");


        String cardNumber = BankManager.getInstance().generateCardNumber();
        System.out.println("Your card number will be: " + cardNumber);


        System.out.print("Set a 4-digit PIN: ");
        int pin = getIntInput();


        LocalDate now = LocalDate.now();
        LocalDate expiry = now.plusYears(5);
        Date expirationDate = new Date(expiry.getYear(), expiry.getMonthValue(), expiry.getDayOfMonth());


        System.out.print("Initial deposit amount (minimum $10): $");
        double initialBalance = getDoubleInput();

        if (initialBalance < 10) {
            System.out.println("Minimum initial deposit is $10. Setting to $10.");
            initialBalance = 10;
        }

        return new Card(cardNumber, expirationDate, initialBalance, pin, ownerId);
    }
    private static void viewMyCards() {
        System.out.println("\n--- MY CARDS ---");
        Map<String, Card> cards = currentCustomer.getCardsMap();

        if (cards == null || cards.isEmpty()) {
            System.out.println("You don't have any cards yet.");
            return;
        }

        for (Map.Entry<String, Card> entry : cards.entrySet()) {
            Card card = entry.getValue();
            System.out.println("Card Number: " + card.getCardNumber());

            System.out.println("  ---");
        }
    }

    private static Card selectCard() {
        Map<String, Card> cards = currentCustomer.getCardsMap();

        if (cards == null || cards.isEmpty()) {
            System.out.println("You don't have any cards.");
            return null;
        }

        System.out.println("Select a card:");
        Card[] cardArray = cards.values().toArray(new Card[0]);
        for (int i = 0; i < cardArray.length; i++) {
            System.out.println((i + 1) + ". Card ending in ..." +
                    cardArray[i].getCardNumber().substring(Math.max(0, cardArray[i].getCardNumber().length() - 4)));
        }

        System.out.print("Choose card (1-" + cardArray.length + "): ");
        int choice = getIntInput();

        if (choice < 1 || choice > cardArray.length) {
            System.out.println("Invalid selection.");
            return null;
        }

        return cardArray[choice - 1];
    }

    private static void checkBalance() {
        System.out.println("\n--- CHECK BALANCE ---");

        Card selectedCard = selectCard();
        if (selectedCard == null) return;

        System.out.print("Enter PIN: ");
        int pin = getIntInput();

        try {
            double balance = selectedCard.getBalance(pin);
            System.out.println("Current balance: $" + balance);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void depositMoney() {
        System.out.println("\n--- DEPOSIT MONEY ---");

        Card selectedCard = selectCard();
        if (selectedCard == null) return;

        System.out.print("Enter amount to deposit: $");
        double amount = getDoubleInput();

        System.out.print("Enter PIN: ");
        int pin = getIntInput();

        try {
            selectedCard.deposit(amount, pin);
            System.out.println("Deposit successful! New balance: $" + selectedCard.getBalance(pin));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void withdrawMoney() {
        System.out.println("\n--- WITHDRAW MONEY ---");

        Card selectedCard = selectCard();
        if (selectedCard == null) return;

        System.out.print("Enter amount to withdraw: $");
        double amount = getDoubleInput();

        System.out.print("Enter PIN: ");
        int pin = getIntInput();

        try {
            selectedCard.withdraw(amount, pin);
            System.out.println("Withdrawal successful! New balance: $" + selectedCard.getBalance(pin));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void transferMoney() {
        System.out.println("\n--- TRANSFER MONEY ---");

        Card senderCard = selectCard();
        if (senderCard == null) return;

        System.out.print("Enter recipient card number: ");
        String recipientCard = scanner.nextLine();

        System.out.print("Enter amount to transfer: $");
        double amount = getDoubleInput();

        System.out.print("Enter PIN: ");
        int pin = getIntInput();

        try {
            senderCard.transfer(amount, recipientCard, pin);
            System.out.println("Transfer successful!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void reportIssue() {
        System.out.println("\n--- REPORT ISSUE ---");

        Card selectedCard = selectCard();
        if (selectedCard == null) return;

        System.out.print("Enter issue title: ");
        String title = scanner.nextLine();

        System.out.print("Enter issue description: ");
        String description = scanner.nextLine();

        System.out.print("Enter your password to confirm: ");
        String password = scanner.nextLine();

        try {
            currentCustomer.reportIssue(description, title, selectedCard.getCardNumber(), password);
            System.out.println("Issue reported successfully. A support representative will contact you soon.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void changePassword() {
        System.out.println("\n--- CHANGE PASSWORD ---");

        System.out.print("Enter old password: ");
        String oldPassword = scanner.nextLine();

        System.out.print("Enter new password (min 6 characters): ");
        String newPassword = scanner.nextLine();

        try {
            currentUser.resetPassword(oldPassword, newPassword);
            System.out.println("Password changed successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void logout() {
        System.out.println("Logging out...");
        currentUser = null;
        currentCustomer = null;
        currentAdmin = null;

    }

    // ==================== UTILITY METHODS ====================

    private static int getIntInput() {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private static double getDoubleInput() {
        while (true) {
            try {
                double input = Double.parseDouble(scanner.nextLine());
                if (input <= 0) {
                    System.out.print("Amount must be positive. Please try again: ");
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}