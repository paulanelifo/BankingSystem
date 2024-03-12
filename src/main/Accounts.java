//addAccount(3, "John Doe", "123456", 1000.0);


package main;
import java.util.HashMap;

public class Accounts {
    // Define accountsHash as a static field to maintain its state
    private static HashMap<Integer, HashMap<String, Object>> accountsHash = new HashMap<>();
    private static int nextUserId = 10000; // Starting point for user ID generation

    // Initialize accountsHash if needed
    public static HashMap<Integer, HashMap<String, Object>> createAccountsHashMap() {
        // Add predetermined admin account
        HashMap<String, Object> adminData = new HashMap<>();
        adminData.put("acc_name", "admin");
        adminData.put("password", "admin");
        adminData.put("balance", 0.0);
        accountsHash.put(1, adminData); // Special ID for admin
        
        // Add predetermined admin account
        HashMap<String, Object> adminData1 = new HashMap<>();
        adminData1.put("acc_name", "Paul April");
        adminData1.put("password", "wasd");
        adminData1.put("balance", 50000.0);
        accountsHash.put(2, adminData1); // Special ID for admin

        return accountsHash;
    }

    // Method to retrieve account details for a specific user ID
    public static HashMap<String, Object> getAccount(int userId) {
        return accountsHash.get(userId);
    }

    // Getter method to retrieve account name
    public static String getName(int userId) {
        HashMap<String, Object> account = getAccount(userId);
        if (account != null) {
            return (String) account.get("acc_name");
        }
        return null;
    }

    // Getter method to retrieve account password
    public static String getPassword(int userId) {
        HashMap<String, Object> account = getAccount(userId);
        if (account != null) {
            return (String) account.get("password");
        }
        return null;
    }

    // Getter method to retrieve account balance
    public static double getBalance(int userId) {
        HashMap<String, Object> account = getAccount(userId);
        if (account != null) {
            return (double) account.get("balance");
        }
        return 0.0;
    }

    // Method to generate a new user ID
    private static int generateUserId() {
        return nextUserId++;
    }

    public static void addAccount(int userId, String accName, String password, double balance) {
        HashMap<String, Object> userData = new HashMap<>();
        userData.put("acc_name", accName);
        userData.put("password", password);
        userData.put("balance", balance);
        accountsHash.put(userId, userData);
    }


    // Method to deposit money into an account
    public static void deposit(int userId, double amount) {
        HashMap<String, Object> account = getAccount(userId);
        if (account != null) {
            double currentBalance = (double) account.get("balance");
            account.put("balance", currentBalance + amount);
        }
    }

    // Method to withdraw money from an account
    public static void withdraw(int userId, double amount) {
        HashMap<String, Object> account = getAccount(userId);
        if (account != null) {
            double currentBalance = (double) account.get("balance");
            if (currentBalance >= amount) {
                account.put("balance", currentBalance - amount);
            }
        }
    }

    // Method to transfer money from one account to another
    public static void transfer(int senderUserId, int receiverUserId, double amount) {
        HashMap<String, Object> senderAccount = getAccount(senderUserId);
        HashMap<String, Object> receiverAccount = getAccount(receiverUserId);
        if (senderAccount != null && receiverAccount != null) {
            double senderBalance = (double) senderAccount.get("balance");
            if (senderBalance >= amount) {
                double receiverBalance = (double) receiverAccount.get("balance");
                senderAccount.put("balance", senderBalance - amount);
                receiverAccount.put("balance", receiverBalance + amount);
            }
        }
    }
    
    public static boolean userIdExists(int userId) {
        return accountsHash.containsKey(userId);
    }
    
    public static boolean changePassword(int userId, String currentPassword, String newPassword) {
    HashMap<String, Object> account = getAccount(userId);
    if (account != null) {
        String storedPassword = (String) account.get("password");
        if (storedPassword.equals(currentPassword)) {
            account.put("password", newPassword);
            return true; // Password changed successfully
        }
    }
    return false; // Password change failed
}

    
}
