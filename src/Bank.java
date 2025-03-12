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
    private Vector<User> usersList = new Vector<User>();
    // Save transactionsList in bank.json
    private Vector<Transaction> transactionsList = new Vector<Transaction>();

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

    public boolean manageUserMoney(String username, String action, double money) {
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
                            money, "deposit"));
        } else {
            this.usersList.get(index).withdrawMoneyFromBalanceToWallet(money);
            this.transactionsList.addElement(
                    new Transaction(this.usersList.get(index), this.usersList.get(index),
                            money, "withdraw"));
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

        for (int i = 0; i < this.usersList.size(); i++) {
            users.add(i, this.usersList.get(i).returnObject());
        }

        object.put("usersList", users);

        try {
            Path dataDirectory = Path.of("./data");
            Path pathOfSave = Path.of(dataDirectory + "/bank.json");

            if (!Files.exists(pathOfSave)) {
                System.out.println("bank.json doesn't exist, creating it...");

                Path newDirectory = Files.createDirectories(dataDirectory);
                Path newFile = Files.createFile(pathOfSave);
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

                for (int i = 0; i < users.size(); i++) {
                    this.usersList.addElement(new User((JSONObject) users.get(i)));
                }
            } else {
                System.out.println("bank.json doesn't exist, creating it...");

                Path newDirectory = Files.createDirectories(dataDirectory);
                Path newFile = Files.createFile(pathOfSave);

                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void testSavingToDisk() {
        Bank testSaving = new Bank();

        testSaving.addUser("luca", "1234", 200);
        testSaving.addUser("marco", "1234", 25);
        testSaving.addUser("fabio", "5678", 50);

        testSaving.makeInvestment("luca", 100, 0,
                3 * 12, 2, "January/2025");
        testSaving.makeInvestment("luca", 10, 0,
                2 * 12, 1, "January/2028");

        System.out.println(testSaving.saveToDisk());

        System.out.println(testSaving.loadFromDisk());
    }

    public static void main(String[] args) {
        testSavingToDisk();
    }
}
