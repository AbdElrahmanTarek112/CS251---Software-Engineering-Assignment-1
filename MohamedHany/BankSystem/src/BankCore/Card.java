package BankCore;//import Date class
import bankCommons.Date;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Card implements Serializable {
    private String cardNumber;
    private Date expirationDate;
    private double balance;
    private int pin;
    private String ownerId;
    public Card(String cardNumber, Date expirationDate, double balance, int pin, String ownerId) {
        this.cardNumber = validateCardNumber(cardNumber);
        this.expirationDate = validateExpirationDate(expirationDate);
        this.balance = validateBalance(balance);
        this.pin = validatePin(pin);
        this.ownerId = ownerId;
    }

    private String validateCardNumber(String cardNumber) {
        //for now, no validation
        return cardNumber;
    }

    private Date validateExpirationDate(Date expirationDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate temp = LocalDate.of(expirationDate.getYear(), expirationDate.getMonth(), expirationDate.getDay());
        long daysBetween = ChronoUnit.DAYS.between(currentDate, temp);

        if  (daysBetween <= 0) {
            throw new IllegalArgumentException("Expiration Date must be in the future");
        }

        return expirationDate;
    }

    private double validateBalance(double balance) {
        if (balance <= 0) {
            throw new IllegalArgumentException("Balance must be positive");
        }
        return balance;
    }

    private int validatePin(int pin) {
        if (pin <= 0) {
            throw new IllegalArgumentException("PIN must be positive");
        }
        String pinString = Integer.toString(pin);
        if (pinString.length() != 4) {
            throw new IllegalArgumentException("PIN must be 4 digits");
        }
        return pin;
    }

    public boolean verifyPin(int pin) {
        if (pin != this.pin) {
            return false;
        }
        return true;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public double getBalance(int PIN) {
        if (PIN != this.pin) {
            throw new IllegalArgumentException("Invalid PIN");
        }
        return balance;
    }

    public String getOwnerId() { return ownerId; }

    int getPin() {
        return pin;
    }

    Date getExpirationDate() {
        return expirationDate;
    }

    double getBalance() {
        return balance;
    }
    public void withdraw(double amount, int PIN) {
        if (PIN != this.pin) {
            throw new IllegalArgumentException("Invalid PIN");
        }
        if  (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient Balance");
        }
        Transaction t = new Transaction(this.cardNumber, amount, Transaction.transactionType.WITHDRAW);
        this.balance -= amount;
        //t.save();
        BankManager.getInstance().addTransacion(t);
    }

    public void deposit(double amount, int PIN) {
        if (PIN != this.pin) {
            throw new IllegalArgumentException("Invalid PIN");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        Transaction t = new Transaction(this.cardNumber, amount, Transaction.transactionType.DEPOSIT);
        this.balance += amount;
        //t.save();
        BankManager.getInstance().addTransacion(t);
    }


    void deposit(double amount) {
        this.balance += amount;
    }

    void withdraw(double amount) {
        this.balance-=amount;
    }

    public void transfer(double amount, String otherCardNumber, int PIN) {
        if  (PIN != this.pin) {
            throw new IllegalArgumentException("Invalid PIN");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient Balance");
        }

        if (!BankManager.getInstance().validate_card_number(otherCardNumber)) {
            throw new IllegalArgumentException("Invalid card number");
        }

        BankManager.getInstance().transfer(this, otherCardNumber, amount, PIN);

    }

}
