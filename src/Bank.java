import java.util.Objects;
import java.util.UUID;
import java.util.Vector;

public class Bank {
    private Vector<User> usersList = new Vector<User>();

    private int getUser(String username) {
        for (int i = 0; i < this.usersList.size(); i++) {
            if (Objects.equals(this.usersList.get(i).getUsername(), username)) {
                return i;
            }
        }

        System.out.println("User doesn't exist");

        return -1;
    }

    public void addUser(String username, String password, double moneyInWallet) {
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
    }

    public boolean checkUsername(String username) {
        // This method will be used to check and authenticate users

       /*for (int i = 0; i < this.usersList.size(); i++) {
            if (Objects.equals(this.usersList.get(i).getUsername(), username)) {
                return false;
            }
        }*/

        return true;
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

        if (money <= 0) {
            return false;
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
        } else {
            this.usersList.get(index).withdrawMoneyFromBalanceToWallet(money);
        }

        return true;
    }

    public void makeInvestment(String username, double money, int increasedRate,
                               int duration, int profitRisk,
                               String startingDate) {
        // There's lots of samey index code
        int index = this.getUser(username);

        if (index == -1) {
            return;
        }

        this.usersList.get(index).addInvestment(money, increasedRate, duration,
                profitRisk, startingDate);
        this.usersList.get(index).removeMoneyBalance(money);
    }

    public void monthlyMoneyAddition(String username) {
        int index = this.getUser(username);

        if (index == -1) {
            return;
        }

        this.usersList.get(index).addMoneyToWallet(100);
    }

    public void showInvestmentsOfUser(String username) {
        int index = this.getUser(username);

        if (index == -1) {
            return;
        }

        this.usersList.get(index).printInvestments();
    }

    public void updateInvestments(String username) {
        int index = this.getUser(username);

        if (index == -1) {
            return;
        }

        this.usersList.get(index).manageInvestments();
    }

    public boolean checkAllInvestmentsStatus(String username) {
        int index = this.getUser(username);

        if (index == -1) {
            return false;
        }

        return this.usersList.get(index).checkStatusInvestments();
    }
}
