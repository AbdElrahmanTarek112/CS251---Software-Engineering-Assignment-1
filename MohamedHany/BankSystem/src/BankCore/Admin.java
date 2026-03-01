package BankCore;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    Admin(String username, String password, String email, String firstName, String lastName, String phone,String nationalId) {
        super(username, password, email, firstName, lastName,  phone, nationalId);
    }


    public boolean rollBackTransaction(String transactionID, String password) {
        if (!super.verifyPassword(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        BankManager.getInstance().rollBackTransaction(transactionID, this);
        return true;
    }

}
