package BankCore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BankManager implements Serializable {
    private static final long serialVersionUID = 1L;
    //private static List<BankCore.User> users = new ArrayList<>();
    private Map<String, User> users = new HashMap<>();
    //static List<BankCore.Transaction> transactions = new ArrayList<>();
     Map<String, Transaction> transactionsMap = new HashMap<>();
    //private static List<BankCore.Card> cards = new ArrayList<>();
    private Map<String, Card> cardsMap = new HashMap<>();
    private Map<String, Issue> issuesMap = new HashMap<>();
    private boolean systemReady = false;
    private static BankManager bankManager;
    private BankManager(Map<String, User> users, Map<String, Transaction> transactions, Map<String, Card> cards) {
        /*setUsers();
        setTransactions();
        setCards();*/
        this.users = users;
        this.transactionsMap = transactions;
        this.cardsMap = cards;

        systemReady = true;
    }

    private BankManager() {}

    static void initialize(Map<String, User> u, Map<String, Transaction> t, Map<String, Card> c) {
        bankManager = new BankManager(u, t, c);
    }

    /*void setUsers(Map<String, BankCore.User> users) {
        this.users = users;
    }

    void setTransactions(Map<String, BankCore.Transaction> transactions) {
        this.transactionsMap = transactions;
    }
    void setCards(Map<String, BankCore.Card> cards) {
        this.cardsMap = cards;
    }*/

    boolean getSystemReady() {
        return systemReady;
    }

   public boolean validate_card_number(String card_number) {
       return cardsMap.containsKey(card_number);
   }


    public Customer registerCustomer(String username, String email, String password,
                                     String firstName, String lastName, String nationalId,
                                     String securityQuestion, String securityAnswer,
                                     String phone, Card card) {

        // Check if username already exists
        for (User u : users.values()) {
            if (u.getUsername().equals(username)) {
                throw new IllegalArgumentException("Username already exists");
            }
        }

        // Check if nationalId already exists
        if (users.containsKey(nationalId)) {
            throw new IllegalArgumentException("National ID already registered");
        }

        // Create new customer
        Customer newCustomer = new Customer(username, email, password, firstName, lastName,
                nationalId, securityQuestion, securityAnswer,
                phone, card);

        // Add to system
        addUser(newCustomer);
        if (card != null) {
            addCard(card);
        }

        return newCustomer;
    }

   boolean transfer(Card sender, String receiverCardNumber, double amount, int sPIN) {
       /*if (!systemReady) {
          throw new IllegalStateException("Bank Manager has not been initialized");
       }*/
        if (!sender.verifyPin(sPIN)) {
            throw new IllegalArgumentException("invalid PIN");
        }
        if (sender.getCardNumber().equals(receiverCardNumber)) {
            throw new IllegalArgumentException("can't transfer to the same card number");
        }
        if (!validate_card_number(sender.getCardNumber()) ||  !validate_card_number(receiverCardNumber)) {
            throw new IllegalArgumentException("invalid card number");
        }

        Card recieverCard = cardsMap.get(receiverCardNumber);
        sender.withdraw(amount);
        recieverCard.deposit(amount);

        Transaction t = new Transaction(sender.getCardNumber(), receiverCardNumber, amount, Transaction.transactionType.TRANSFER);
        //t.save();
       addTransacion(t);
        return true;
    }

    public boolean rollBackTransaction(String transactionID, Admin admin) {
        if (admin == null) {
            throw new SecurityException("Not Enough Privileges: BankCore.Admin is null");
        }

        /*if (!systemReady) {
            throw new IllegalStateException("Bank Manager has not been initialized");
        }*/

        Transaction t = transactionsMap.get(transactionID);
        if (t.getType() == Transaction.transactionType.TRANSFER) {
           Card recieverCard = cardsMap.get(t.getReceiverCardNumber());
           Card senderCard =  cardsMap.get(t.getSenderCardNumber());

           recieverCard.withdraw(t.getAmount());
           senderCard.deposit(t.getAmount());

           Transaction revertedTransaction = new Transaction(t.getReceiverCardNumber(), t.getSenderCardNumber(), t.getAmount(), Transaction.transactionType.REVERT);
           //revertedTransaction.save();
            addTransacion(revertedTransaction);

        }

        if (t.getType() == Transaction.transactionType.DEPOSIT) {
            Card recieverCard = cardsMap.get(t.getReceiverCardNumber());

            recieverCard.withdraw(t.getAmount());

            Transaction revertedTransaction = new Transaction(t.getSenderCardNumber(), null, t.getAmount(), Transaction.transactionType.REVERT);
            //revertedTransaction.save();
            addTransacion(revertedTransaction);
        }

        if (t.getType() == Transaction.transactionType.WITHDRAW) {
            Card recieverCard = cardsMap.get(t.getReceiverCardNumber());

            recieverCard.deposit(t.getAmount());

            Transaction revertedTransaction = new Transaction(null, t.getReceiverCardNumber(), t.getAmount(), Transaction.transactionType.REVERT);
            //revertedTransaction.save();
            addTransacion(revertedTransaction);
        }

        return true;
    }

    public static BankManager getInstance() {
        if (bankManager == null) {
            bankManager = new BankManager();
        }
        return bankManager;
    }

    static void setInstance(BankManager instance) {
        bankManager = instance;
    }

    public void addTransacion(Transaction t) {
        bankManager.transactionsMap.put(t.getTransactionID(), t);
        //BankIO.saveTransaction(t);
        sync();
    }

    public void addUser(User u) {
        bankManager.users.put(u.nationalId, u);
        //BankIO.saveUser(u);
        sync();
    }
    public void addCard(Card c) {
        bankManager.cardsMap.put(c.getCardNumber(), c);
        //BankIO.saveCard(c);
        sync();
    }

    public void addIssue(Issue i) {
        bankManager.issuesMap.put(i.getIssueId(), i);
        sync();
    }


    public User login(String username, String password) {
        for (User u : users.values()) {
            if (u.getUsername().equals(username) && u.verifyPassword(password)) {
                return u;
            }
        }
        return null;
    }

    public Map<String, Transaction> getTransactionsMap() {
        return transactionsMap;
    }

    public Map<String, Issue> getIssuesMap() {
        return issuesMap;
    }

    public void sync() { BankIO.saveSystem(); }


    public String generateCardNumber() {
        String cardNumber;
        do {
            // random 16 digit number
            cardNumber = String.format("%04d%04d%04d%04d",
                    (int)(Math.random() * 10000),
                    (int)(Math.random() * 10000),
                    (int)(Math.random() * 10000),
                    (int)(Math.random() * 10000));
        } while (cardsMap.containsKey(cardNumber));

        return cardNumber;
    }
}
