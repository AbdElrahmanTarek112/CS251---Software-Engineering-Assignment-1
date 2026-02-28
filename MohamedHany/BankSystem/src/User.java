public class User {
    protected String username;
    protected String password;
    protected String email;
    protected String firstName;
    protected String lastName;

    //User() {}

    User(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = validatePassword(password);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private String validatePassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password too short");
        }
        return password;
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
