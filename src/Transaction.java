import org.json.simple.JSONObject;

public class Transaction {
    private final User sender;
    private final User receiver;
    private final double amount;
    private final String action;
    private final String date;

    public Transaction(User sender, User receiver, double amount, String date) {
        this(sender, receiver, amount, "", date);
    }

    public Transaction(User sender, User receiver, double amount, String action,
                       String date) {
        // A User gets deep copied at the moment of a Transaction to be able to see
        // and access values that might have changed such as walletMoney or bankBalance
        this.sender = new User(sender.returnObject());
        this.receiver = new User(receiver.returnObject());
        this.amount = amount;
        this.action = action;
        this.date = date;
    }

    public Transaction(JSONObject object) {
        this.sender = new User((JSONObject) object.get("sender"));
        this.receiver = new User((JSONObject) object.get("sender"));
        this.amount = (double) object.get("amount");
        this.action = object.get("action").toString();
        this.date = object.get("date").toString();
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

    public String getDate() {
        return this.date;
    }

    public JSONObject returnObject() {
        JSONObject transaction = new JSONObject();

        transaction.put("sender", this.sender.returnObject());
        transaction.put("receiver", this.receiver.returnObject());
        transaction.put("amount", this.amount);
        transaction.put("action", this.action);
        transaction.put("date", this.date);

        return transaction;
    }

    @Override
    public String toString() {
        return (this.getDate() + "\n"
                + " " + this.getSender() + " --> " + this.getReceiver() + "\n"
                + " " + this.getAction() + "   " + this.getAmount() + " €\n");
    }
}
