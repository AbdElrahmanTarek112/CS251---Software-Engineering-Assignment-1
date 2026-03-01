package BankCore;

import java.io.Serializable;

public class User implements Serializable {
    protected String username;
    protected String password;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String phone;
    protected String nationalId;



    //BankCore.User() {}

    User(String username, String password, String email, String firstName, String lastName, String phone, String nationalId) {
        this.username = username;
        this.password = validatePassword(password);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.nationalId = nationalId;
    }

    private String validatePassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password too short");
        }
        return password;
    }

    public boolean verifyPassword(String password) {
        return password.equals(this.password);
    }

    public String getUsername() {
        return username;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }

    public boolean resetPassword(String oldPassword, String newPassword) {
        if (password.equals(oldPassword)) {
            password = validatePassword(newPassword);
        } else {
            throw new IllegalArgumentException("Incorrect Password");
        }
        return true;
    }


}


