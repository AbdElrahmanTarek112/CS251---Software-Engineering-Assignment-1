package BankCore;

public class TestAdminAccount {
    public static void main(String[] args) {
        BankIO.startSystem();

        Admin admin = new Admin("admin", "admin445", "admin@gmail.com", "Agent", "47", "01777557629", "9861043100864");
        BankManager.getInstance().addUser(admin);
    }
}
