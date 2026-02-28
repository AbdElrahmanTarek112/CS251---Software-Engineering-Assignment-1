public class Admin extends User {
    Admin(String username, String password, String email, String firstName, String lastName) {
        super(username, password, email, firstName, lastName);
    }

    private void rollBackTransaction(String transactionID) {

        BankManager.rollBackTransaction(transactionID, this);
    }

}
