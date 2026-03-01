package BankCore;

import java.io.*;
import java.util.*;
import bankCommons.Date;

public class BankIO {
    /*private static final String USER_FILE = "users.csv";
    private static final String CARD_FILE = "cards.csv";
    private static final String TRANS_FILE = "transactions.csv";

    public static void startSystem() {
        Map<String, User> users = loadUsersFromFile();
        Map<String, Card> cards = loadCardsFromFile();
        Map<String, Transaction> transactions = loadTransactionsFromFile();

        // Push them into the Singleton
        BankManager.initialize(users, transactions, cards);

        for (Card c : cards.values()) {
            String ownerId = c.getOwnerId();
            User user = users.get(ownerId);
            if (user instanceof Customer) {
                ((Customer) user).addCard(c, BankManager.getInstance()); //
            }
        }
    }

    // --- SAVE METHODS (APPENDING) ---

    static void saveTransaction(Transaction t) {
        try (PrintWriter out = new PrintWriter(new FileWriter(TRANS_FILE, true))) {
            out.println(t.getTransactionID() + "," +
                    t.getSenderCardNumber() + "," +
                    t.getReceiverCardNumber() + "," +
                    t.getAmount() + "," +
                    t.getType());
        } catch (IOException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }

    static void saveCard(Card c) {
        try (PrintWriter out = new PrintWriter(new FileWriter(CARD_FILE, true))) {
            // Saving: Number, Balance, Pin, ExpYear, ExpMonth, ExpDay
            out.println(c.getCardNumber() + "," +
                    c.getBalance() + "," +
                    c.getPin() + "," +
                    c.getExpirationDate().getYear() + "," +
                    c.getExpirationDate().getMonth() + "," +
                    c.getExpirationDate().getDay() + "," +
                    c.getOwnerId());
        } catch (IOException e) {
            System.err.println("Error saving card: " + e.getMessage());
        }
    }

    static void saveUser(User u) {
        try (PrintWriter out = new PrintWriter(new FileWriter(USER_FILE, true))) {
            out.println(u.getUsername() + "," +
                    u.password + "," +
                    u.getEmail() + "," +
                    u.getFirstName() + "," +
                    u.getLastName() + "," +
                    u.phone + "," +
                    u.nationalId);
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    // --- LOAD METHODS ---

    private static Map<String, User> loadUsersFromFile() {
        Map<String, User> map = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(USER_FILE))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 7) {
                    User u = new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
                    map.put(u.nationalId, u);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("BankCore.User file not found. Starting with empty list.");
        }
        return map;
    }

    private static Map<String, Card> loadCardsFromFile() {
        Map<String, Card> map = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(CARD_FILE))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 7) {
                    Date exp = new Date(Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                    Card c = new Card(parts[0], exp, Double.parseDouble(parts[1]), Integer.parseInt(parts[2]), parts[6]);
                    map.put(c.getCardNumber(), c);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("BankCore.Card file not found. Starting with empty list.");
        }
        return map;
    }

    private static Map<String, Transaction> loadTransactionsFromFile() {
        Map<String, Transaction> map = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(TRANS_FILE))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 5) {
                    // Reconstructing BankCore.Transaction from CSV
                    Transaction.transactionType type = Transaction.transactionType.valueOf(parts[4]);
                    Transaction t;

                    if (type == Transaction.transactionType.TRANSFER || type == Transaction.transactionType.REVERT) {
                        t = new Transaction(parts[1], parts[2], Double.parseDouble(parts[3]), type);
                    } else {
                        t = new Transaction(parts[2], Double.parseDouble(parts[3]), type);
                    }
                    t.transactionID = parts[0]; // Preserve the original UUID
                    map.put(t.transactionID, t);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("BankCore.Transaction file not found. Starting with empty list.");
        }
        return map;
    }*/

    private static final String FILE_NAME = "bank_state.ser";

    public static void startSystem() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            BankManager loaded = (BankManager) in.readObject();
            BankManager.setInstance(loaded);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("New system detected. Initializing empty database.");
        }
    }

    public static void saveSystem() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(BankManager.getInstance());
        } catch (IOException e) {
            System.err.println("CRITICAL ERROR: Could not save data!" + e.getMessage());
        }
    }
}