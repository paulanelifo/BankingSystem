
/*
when creating the hashmap
HashMap<String, HashMap<String, Object>> accounts = createAccountsHashMap();

when adding account
addAccount("pauljava", "Paul April", "PASSWORD", 500);

retrieving details using username as argument
System.out.println("Name for pauljava: " + getName("pauljava"));
System.out.println("Password for pauljava: " + getPassword("pauljava"));
System.out.println("Balance for pauljava: " + getBalance("pauljava"));
*/



package main;
import java.util.HashMap;

public class Accounts {
    // Define accountsHash as a static field to maintain its state
    private static HashMap<String, HashMap<String, Object>> accountsHash = new HashMap<>();

    // Initialize accountsHash if needed
    public static HashMap<String, HashMap<String, Object>> createAccountsHashMap() {
        // Add predetermined account
        HashMap<String, Object> adminData = new HashMap<>();
        adminData.put("acc_name", "admin");
        adminData.put("password", "admin");
        adminData.put("balance", 0.0);
        accountsHash.put("admin", adminData);
        return accountsHash;
        //sample HashMap<String, HashMap<String, Object>> accounts = createAccountsHashMap();
    }
    
    // Method to retrieve account details for a specific username
    public static HashMap<String, Object> getAccount(String username) {
        return accountsHash.get(username);
    }
    
    // Getter method to retrieve account name
    public static String getName(String username) {
        HashMap<String, Object> account = getAccount(username);
        if (account != null) {
            return (String) account.get("acc_name");
        }
        return null;
    }
    
    // Getter method to retrieve account password
    public static String getPassword(String username) {
        HashMap<String, Object> account = getAccount(username);
        if (account != null) {
            return (String) account.get("password");
        }
        return null;
    }
    
    // Getter method to retrieve account balance
    public static double getBalance(String username) {
        HashMap<String, Object> account = getAccount(username);
        if (account != null) {
            return (double) account.get("balance");
        }
        return 0.0;
    }
    
    //add account
    public static void addAccount(String username, String accName, String password, double balance) {
        HashMap<String, Object> userData = new HashMap<>();
        userData.put("acc_name", accName);
        userData.put("password", password);
        userData.put("balance", balance);
        accountsHash.put(username, userData);
        //sample:        addAccount("pauljava", "Paul April", "PASSWORD", 500);

    }
}
