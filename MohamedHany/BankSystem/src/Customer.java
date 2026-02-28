public class Customer extends User {
    private Account _account;
    Customer(String username, String email, String password, String firstName, String lastName, String nationalId, String securityQuestion, String securityAnswer, String phone) {
        super(username, password, email, firstName, lastName);
        _account = new Account(firstName + lastName, email, phone, nationalId, securityQuestion, securityAnswer);
    }

    void reportIssue(String message) {

    }
}
