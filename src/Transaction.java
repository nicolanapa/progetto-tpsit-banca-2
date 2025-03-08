public class Transaction {
    private final User sender;
    private final User receiver;
    private final double amount;
    private final String action;

    public Transaction(User sender, User receiver, double amount) {
        this(sender, receiver, amount, "");
    }

    public Transaction(User sender, User receiver, double amount, String action) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.action = action;
    }

    public String getSender() {
        return this.sender.getUsername();
    }

    public String getReceiver() {
        return this.receiver.getUsername();
    }

    public double getAmount() {
        return this.amount;
    }

    public String getAction() {
        return this.action;
    }

    @Override
    public String toString() {
        return (this.getSender() + " --> " + this.getReceiver() + "\n"
                + this.getAction() + "   " + this.getAmount() + " €");
    }
}
