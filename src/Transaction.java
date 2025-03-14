import org.json.simple.JSONObject;

public class Transaction {
    private final User sender;
    private final User receiver;
    private final double amount;
    private final String action;

    public Transaction(User sender, User receiver, double amount) {
        this(sender, receiver, amount, "");
    }

    public Transaction(User sender, User receiver, double amount, String action) {
        // A User gets deep copied at the moment of a Transaction to be able to see
        // and access values that might have changed such as walletMoney or bankBalance
        this.sender = new User(sender.returnObject());
        this.receiver = new User(receiver.returnObject());
        this.amount = amount;
        this.action = action;
    }

    public Transaction(JSONObject object) {
        this.sender = new User((JSONObject) object.get("sender"));
        this.receiver = new User((JSONObject) object.get("sender"));
        this.amount = (double) object.get("amount");
        this.action = object.get("action").toString();
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

    public JSONObject returnObject() {
        JSONObject transaction = new JSONObject();

        transaction.put("sender", this.sender.returnObject());
        transaction.put("receiver", this.receiver.returnObject());
        transaction.put("amount", this.amount);
        transaction.put("action", this.action);

        return transaction;
    }

    @Override
    public String toString() {
        return (this.getSender() + " --> " + this.getReceiver() + "\n"
                + this.getAction() + "   " + this.getAmount() + " €");
    }
}
