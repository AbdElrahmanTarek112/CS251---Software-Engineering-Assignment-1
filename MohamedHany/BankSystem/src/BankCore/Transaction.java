package BankCore;

import bankCommons.Date;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction implements Serializable {
    String transactionID = UUID.randomUUID().toString();
    String senderCardNumber;
    String receiverCardNumber;
    double amount;
    Date date;
    transactionType type;

    /*BankCore.Transaction(String senderCardNumber, String receiverCardNumber, double amount) {
        type = transactionType.TRANSFER;
        this.senderCardNumber = senderCardNumber;
        this.receiverCardNumber = receiverCardNumber;
        this.amount = amount;
        LocalDate currentDate = LocalDate.now();
        date = new Date(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth());
    }*/

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

    public String getTransactionID() {
        return transactionID;
    }

    public transactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "senderCardNumber='" + senderCardNumber + '\'' +
                ", receiverCardNumber='" + receiverCardNumber + '\'' +
                ", amount=" + amount +
                ", date=" + date.toString() +
                ", type=" + type +
                '}';
    }
}
