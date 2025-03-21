import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Bank {
    private Vector<User> usersList = new Vector<>();
    private Vector<Transaction> transactionsList = new Vector<>();

    private int getUser(String username) {
        return this.getUser(username, false);
    }

    private int getUser(String username, boolean hideError) {
        // System.out.println(usersList);

        for (int i = 0; i < this.usersList.size(); i++) {
            if (Objects.equals(this.usersList.get(i).getUsername(), username)) {
                return i;
            }
        }

        if (!hideError) {
            System.out.println("User doesn't exist");
        }

        return -1;
    }

    public String getUsername(int index) {
        if (index >= 0 && index < this.usersList.size()) {
            return this.usersList.get(index).getUsername();
        } else {
            System.out.println("User doesn't exist");

            return "";
        }
    }

    public void getUserTransactions(String username, String filter) {
        if (this.getUser(username) == -1) {
            return;
        }

        System.out.println();

        for (int i = this.transactionsList.size() - 1; i > 0; i--) {
            if (Objects.equals(this.transactionsList.get(i).getSender(), username) ||
                    Objects.equals(this.transactionsList.get(i).getReceiver(),
                            username)) {
                if (Objects.equals(filter, "all")) {
                    System.out.println(this.transactionsList.get(i));
                } else if (Objects.equals(filter, "last")) {
                    System.out.println(this.transactionsList.get(i));
                    return;
                }
            }
        }
    }

    public String addUser(String username, String password, double moneyInWallet) {
        if (this.getUser(username) != -1) {
            username = String.valueOf(UUID.randomUUID());

            System.out.println("User already exists");
            System.out.println("A random username was automatically " +
                    "given for you:");
            System.out.println(username);
        }

        System.out.println("Creating new User...");
        User temporaryUser = new User(username, password, moneyInWallet);
        this.usersList.addElement(temporaryUser);

        return username;
    }

    public boolean checkUserCredentials(String username, String password) {
        int index = this.getUser(username, true);

        if (index == -1) {
            return false;
        }

        return (this.usersList.get(index).checkIfPasswordMatches(password));
    }

    public double getUserBalance(String username, String typeOfBalance) {
        int index = this.getUser(username);

        if (index == -1) {
            return 0;
        }

        return (Objects.equals(typeOfBalance, "bankBalance") ?
                this.usersList.get(index).getBankBalance() :
                this.usersList.get(index).getWalletMoney());
    }

    public boolean manageUserMoney(String username, String action, double money,
                                   String date) {
        int index = this.getUser(username);

        if (index == -1) {
            return false;
        }

        if (money < 0) {
            return false;
        } else if (money == 0) {
            return true;
        } else {
            if (Objects.equals(action, "depositMoney")) {
                if (money > this.usersList.get(index).getWalletMoney()) {
                    return false;
                }
            } else {
                if (money > this.usersList.get(index).getBankBalance()) {
                    return false;
                }
            }
        }

        if (Objects.equals(action, "depositMoney")) {
            this.usersList.get(index).addMoneyToBalanceFromWallet(money);
            this.transactionsList.addElement(
                    new Transaction(this.usersList.get(index), this.usersList.get(index),
                            money, "deposit", date));
        } else {
            this.usersList.get(index).withdrawMoneyFromBalanceToWallet(money);
            this.transactionsList.addElement(
                    new Transaction(this.usersList.get(index), this.usersList.get(index),
                            money, "withdraw", date));
        }

        return true;
    }

    public void makeInvestment(String username, double money, int increasedRate,
                               int duration, int profitRisk,
                               String startingDate) {
        int index = this.getUser(username);

        if (index == -1) {
            return;
        }

        this.usersList.get(index).addInvestment(money, increasedRate, duration,
                profitRisk, startingDate);
        this.usersList.get(index).removeMoneyBalance(money);
    }

    public void showInvestmentsOfUser(String username) {
        int index = this.getUser(username);

        if (index == -1) {
            return;
        }

        this.usersList.get(index).printInvestments();
    }

    public void updateInvestmentsAndMonthlyMoney() {
        for (int i = 0; i < usersList.size(); i++) {
            this.usersList.get(i).manageInvestments();
            this.usersList.get(i).addMoneyToWallet(100);
        }
    }

    public boolean checkAllInvestmentsStatus(String username) {
        int index = this.getUser(username);

        if (index == -1) {
            return false;
        }

        return this.usersList.get(index).checkStatusInvestments();
    }

    public boolean saveToDisk() {
        JSONObject object = new JSONObject();

        JSONArray users = new JSONArray();
        JSONArray transactions = new JSONArray();

        for (int i = 0; i < this.usersList.size(); i++) {
            users.add(i, this.usersList.get(i).returnObject());
        }

        for (int i = 0; i < this.transactionsList.size(); i++) {
            transactions.add(i, this.transactionsList.get(i).returnObject());
        }

        object.put("usersList", users);
        object.put("transactionsList", transactions);

        try {
            Path dataDirectory = Path.of("./data");
            Path pathOfSave = Path.of(dataDirectory + "/bank.json");

            if (!Files.exists(pathOfSave)) {
                System.out.println("bank.json doesn't exist, creating it...");

                Files.createDirectories(dataDirectory);
                Files.createFile(pathOfSave);
            }

            Files.writeString(pathOfSave, object.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public boolean loadFromDisk() {
        try {
            Path dataDirectory = Path.of("./data");
            Path pathOfSave = Path.of(dataDirectory + "/bank.json");

            if (Files.exists(pathOfSave)) {
                if (Files.readString(pathOfSave).isEmpty()) {
                    return false;
                }

                JSONParser parser = new JSONParser();
                JSONObject object = null;

                try {
                    object = (JSONObject) parser
                            .parse(Files.readString(pathOfSave));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                JSONArray users = (JSONArray) object.get("usersList");
                JSONArray transactions = (JSONArray) object.get("transactionsList");

                for (int i = 0; i < users.size(); i++) {
                    this.usersList.addElement(new User((JSONObject) users.get(i)));
                }

                for (int i = 0; i < transactions.size(); i++) {
                    this.transactionsList.addElement(new Transaction(
                            (JSONObject) transactions.get(i)));
                }
            } else {
                System.out.println("bank.json doesn't exist, creating it...");

                Files.createDirectories(dataDirectory);
                Files.createFile(pathOfSave);

                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
