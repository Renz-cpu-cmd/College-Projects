import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.locks.*;

public class ATMData {
    private static final Map<String, Double> accounts = new HashMap<>();
    private static final Map<String, Integer> failedAttempts = new HashMap<>();
    private static final Map<String, List<String>> transactionHistory = new HashMap<>();
    private static final Lock lock = new ReentrantLock();

    private static final int MAX_ATTEMPTS = 3;
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("₱#,##0.00");

    private static String currentPIN;

    // Static initializer
    static {
        initializeAccount("1234", 1000.0);
        initializeAccount("5678", 500.0);
        initializeAccount("0000", 1500.0);
    }

    // Helper to initialize an account
    private static void initializeAccount(String pin, double balance) {
        accounts.put(pin, balance);
        failedAttempts.put(pin, 0);
        transactionHistory.put(pin, new ArrayList<>());
    }

    /** Validate PIN and set as current user */
    public static boolean validatePIN(String pin) {
        lock.lock();
        try {
            if (failedAttempts.getOrDefault(pin, 0) >= MAX_ATTEMPTS) {
                System.out.println("Account locked due to too many failed attempts.");
                return false;
            }

            if (accounts.containsKey(pin)) {
                currentPIN = pin;
                failedAttempts.put(pin, 0);  // reset failed attempts
                return true;
            } else {
                failedAttempts.put(pin, failedAttempts.getOrDefault(pin, 0) + 1);
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    /** Return current balance */
    public static double getBalance() {
        lock.lock();
        try {
            return getCurrentAccountBalance();
        } finally {
            lock.unlock();
        }
    }

    /** Deposit amount */
    public static void deposit(double amount) {
        lock.lock();
        try {
            if (amount <= 0) {
                addTransaction("Failed deposit attempt: Invalid amount.");
                return;
            }
            double newBalance = getCurrentAccountBalance() + amount;
            accounts.put(currentPIN, newBalance);
            addTransaction("Deposited " + MONEY_FORMAT.format(amount) + ". New Balance: " + MONEY_FORMAT.format(newBalance));
        } finally {
            lock.unlock();
        }
    }

    /** Withdraw amount */
    public static boolean withdraw(double amount) {
        lock.lock();
        try {
            if (amount <= 0) {
                addTransaction("Failed withdrawal attempt: Invalid amount.");
                return false;
            }

            double currentBalance = getCurrentAccountBalance();
            if (amount <= currentBalance) {
                double newBalance = currentBalance - amount;
                accounts.put(currentPIN, newBalance);
                addTransaction("Withdrew " + MONEY_FORMAT.format(amount) + ". New Balance: " + MONEY_FORMAT.format(newBalance));
                return true;
            } else {
                addTransaction("Failed withdrawal attempt: Insufficient funds.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    /** Create a new account */
    public static boolean createAccount(String pin, double initialBalance) {
        lock.lock();
        try {
            if (pin == null || pin.isEmpty() || accounts.containsKey(pin)) {
                return false;  // Invalid or already existing PIN
            }
            initializeAccount(pin, Math.max(initialBalance, 0));
            return true;
        } finally {
            lock.unlock();
        }
    }

    /** Get current user's PIN */
    public static String getCurrentPIN() {
        return currentPIN;
    }

    /** Get transaction history */
    public static List<String> getTransactionHistory() {
        return transactionHistory.getOrDefault(currentPIN, new ArrayList<>());
    }

    /** Display transaction history to console */
    public static void displayTransactionHistory() {
        List<String> history = getTransactionHistory();
        if (history.isEmpty()) {
            System.out.println("No transaction history available for PIN " + currentPIN + ".");
        } else {
            System.out.println("Transaction History for PIN " + currentPIN + ":");
            history.forEach(System.out::println);
        }
    }

    /** Add a transaction log */
    private static void addTransaction(String transactionDetails) {
        String timestamp = new Date().toString();
        transactionHistory
                .computeIfAbsent(currentPIN, k -> new ArrayList<>())
                .add("[" + timestamp + "] " + transactionDetails);
    }

    /** Helper to get current balance */
    private static double getCurrentAccountBalance() {
        return accounts.getOrDefault(currentPIN, 0.0);
    }

    /** Lock account manually (optional feature) */
    public static void lockCurrentAccount() {
        failedAttempts.put(currentPIN, MAX_ATTEMPTS);
    }

    /** Reset ATM data - for testing purposes only */
    public static void resetData() {
        lock.lock();
        try {
            accounts.clear();
            failedAttempts.clear();
            transactionHistory.clear();
            currentPIN = null;
            initializeAccount("1234", 1000.0);
            initializeAccount("5678", 500.0);
            initializeAccount("0000", 1500.0);
        } finally {
            lock.unlock();
        }
    }
}
