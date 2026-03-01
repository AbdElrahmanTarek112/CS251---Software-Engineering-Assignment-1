package BankCore;

import bankCommons.Date;

import java.io.Serializable;
import java.util.UUID;

public class Issue implements Serializable {
    String issueID = UUID.randomUUID().toString();
    String message;
    String title;
    String issuerCardNumber;
    Date issueDate;


    public Issue(String message, String title, String issuerCardNumber, Date issueDate) {
        this.message = message;
        this.title = title;
        this.issuerCardNumber = issuerCardNumber;
        this.issueDate = issueDate;
    }

    public String getIssueId() {return issueID;}

    @Override
    public String toString() {
        return "Issue{" +
                "message='" + message + '\'' +
                ", title='" + title + '\'' +
                ", issuerCardNumber='" + issuerCardNumber + '\'' +
                ", issueDate=" + issueDate +
                '}';
    }
}
