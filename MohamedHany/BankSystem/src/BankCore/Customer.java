package BankCore;

import java.io.Serializable;
import java.time.LocalDate;
import bankCommons.Date;


import java.util.HashMap;
import java.util.Map;

public class Customer extends User implements Serializable {

    private Map<String, Card> cardsMap;
    public Customer(String username, String email, String password, String firstName, String lastName, String nationalId, String securityQuestion, String securityAnswer, String phone, Card card) {
        super(username, password, email, firstName, lastName, phone, nationalId);
        cardsMap = new HashMap<>();
        if (card != null) {
            this.cardsMap.put(card.getCardNumber(), card);
        }

    }

    public void reportIssue(String message, String title, String cardNumber, String password) {
        if (!super.verifyPassword(password)) {
            throw new SecurityException("Invalid password");
        }

        verifyCard(cardNumber);

        LocalDate issueDate = LocalDate.now();
        Date tempDate = new Date(issueDate.getYear(), issueDate.getMonthValue(), issueDate.getDayOfMonth());
        Issue issue = new Issue(message, title, cardNumber, tempDate);
        BankManager.getInstance().addIssue(issue);
    }

    void addCard(Card card, BankManager bankManager) {
        if (bankManager == null) {
            throw new SecurityException("Not Enough Privileges");
        }
        this.cardsMap.put(String.valueOf(card.getCardNumber()), card);
    }

    public boolean verifyCard(String cardNumber) {
        if (!this.cardsMap.containsKey(cardNumber)) {
            throw new SecurityException("Invalid card number for this customer");
        }
        return true;
    }

    void setCardsMap(Map<String, Card> cardsMap, BankManager bankManager) {
        if  (bankManager == null) {
            throw new SecurityException("Not Enough Privileges");
        }
        this.cardsMap = cardsMap;
    }

    public Map<String, Card> getCardsMap() {
        return cardsMap;
    }

}
