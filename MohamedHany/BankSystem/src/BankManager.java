import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankManager {
    private static List<User> users = new ArrayList<>();
    //static List<Transaction> transactions = new ArrayList<>();
    static Map<String, Transaction> transactionsMap = new HashMap<>();
    //private static List<Card> cards = new ArrayList<>();
    private static Map<String, Card> cardsMap = new HashMap<>();
    private static boolean systemReady = false;
    BankManager() {
        loadUsers();
        loadTransactions();
        loadCards();
        systemReady = true;
    }

    private void loadUsers() {

    }

    private void loadTransactions() {
    }
    private void loadCards() {
    }

   public static boolean validate_card_number(String card_number) {
       return cardsMap.containsKey(card_number);
   }

   static boolean transfer(Card sender, String receiverCardNumber, double amount, int sPIN) {
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

        Transaction t = new Transaction(receiverCardNumber, sender.getCardNumber(), amount);
        t.save();
        return true;
    }

    public static boolean rollBackTransaction(String transactionID, Admin admin) {
        if (admin == null) {
            throw new SecurityException("Not Enough Privileges: Admin is null");
        }

        Transaction t = transactionsMap.get(transactionID);
        if (t.getType() == Transaction.transactionType.TRANSFER) {
           Card recieverCard = cardsMap.get(t.getReceiverCardNumber());
           Card senderCard =  cardsMap.get(t.getSenderCardNumber());

           recieverCard.withdraw(t.getAmount());
           senderCard.deposit(t.getAmount());
           Transaction revertedTransaction = new Transaction(t.getReceiverCardNumber(), t.getSenderCardNumber(), t.getAmount(), Transaction.transactionType.REVERT);
           revertedTransaction.save();

        }

        return true;
    }


}
