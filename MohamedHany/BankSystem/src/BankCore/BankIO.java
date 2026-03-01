package BankCore;

import java.io.*;


public class BankIO {

    private static final String FILE_NAME = "bank_state.ser";

    public static void startSystem() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            BankManager loaded = (BankManager) in.readObject();
            BankManager.setInstance(loaded);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("New system detected. Initializing empty database.");
        }
    }

    public static void saveSystem() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(BankManager.getInstance());
        } catch (IOException e) {
            System.err.println("CRITICAL ERROR: Could not save data!" + e.getMessage());
        }
    }
}