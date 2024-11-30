package Bank;

public class Card {
    private String cardNumber;
    private String pin;
    private double balance;
    private int failedAttempts;
    private boolean locked;

    public Card(String cardNumber, String pin, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.failedAttempts = 0;
        this.locked = false;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isLocked() {
        return locked;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void incrementFailedAttempts() {
        this.failedAttempts++;
    }

    public void resetFailedAttempts() {
        this.failedAttempts = 0;
    }

    public void lock() {
        this.locked = true;
    }

    public boolean checkPin(String pin) {
        return this.pin.equals(pin);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount <= 1000000) {
            balance += amount;
            return true;
        }
        return false;
    }

    public String toDataString() {
        return cardNumber + " " + pin + " " + balance + " " + failedAttempts + " " + locked;
    }

    public static Card fromDataString(String dataString) {
        String[] parts = dataString.split(" ");
        String cardNumber = parts[0];
        String pin = parts[1];
        double balance = Double.parseDouble(parts[2]);
        int failedAttempts = Integer.parseInt(parts[3]);
        boolean locked = Boolean.parseBoolean(parts[4]);

        Card card = new Card(cardNumber, pin, balance);
        card.failedAttempts = failedAttempts;
        card.locked = locked;

        return card;
    }
}

