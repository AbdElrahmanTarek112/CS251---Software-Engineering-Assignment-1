import bankCommons.Date;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    String transactionID = UUID.randomUUID().toString();
    String senderCardNumber;
    String receiverCardNumber;
    double amount;
    Date date;
    transactionType type;

    Transaction(String senderCardNumber, String receiverCardNumber, double amount) {
        type = transactionType.TRANSFER;
        this.senderCardNumber = senderCardNumber;
        this.receiverCardNumber = receiverCardNumber;
        this.amount = amount;
        LocalDate currentDate = LocalDate.now();
        date = new Date(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth());
    }

    Transaction(String senderCardNumber, String receiverCardNumber, double amount, transactionType type) {
        this.type = type;
        this.senderCardNumber = senderCardNumber;
        this.receiverCardNumber = receiverCardNumber;
        this.amount = amount;
        LocalDate currentDate = LocalDate.now();
        date = new Date(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth());
    }

    Transaction(String receiverCardNumber, double amount, transactionType type) {
        this.receiverCardNumber = receiverCardNumber;
        this.amount = amount;
        this.type = type;
        LocalDate currentDate = LocalDate.now();
        date = new Date(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth());
    }

    enum transactionType {
        WITHDRAW, DEPOSIT, TRANSFER, REVERT
    }

    boolean save() {
        return true;
    }

    public String getReceiverCardNumber() {
        return receiverCardNumber;
    }
    public String getSenderCardNumber() {
        return senderCardNumber;
    }
    public double getAmount() {
        return amount;
    }
    public transactionType getType() {
        return type;
    }
}
