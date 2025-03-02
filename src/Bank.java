import java.util.Objects;
import java.util.Vector;

public class Bank {
    private Vector<User> usersList = new Vector<User>();

    private int getUser(String username) {
        int index = -1;

        for (int i = 0; i < this.usersList.size(); i++) {
            if (Objects.equals(this.usersList.get(i).getUsername(), username)) {
                index = i;

                break;
            }
        }

        return index;
    }

    public void addUser(String username, double moneyInWallet) {
        User temporaryUser = new User(username, moneyInWallet);
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

        if (index != -1) {
            return (Objects.equals(typeOfBalance, "bankBalance") ?
                    this.usersList.get(index).getBankBalance() :
                    this.usersList.get(index).getWalletMoney());
        } else {
            System.out.println("User doesn't exist");

            return 0;
        }
    }

    public boolean manageUserMoney(String username, String action, double money) {
        int index = this.getUser(username);

        if (index == -1) {
            System.out.println("User doesn't exist");

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

        // Add condition if index === -1
        this.usersList.get(index).addInvestment(money, increasedRate, duration,
                profitRisk, startingDate);
        this.usersList.get(index).removeMoneyBalance(money);
    }

    public void monthlyMoneyAddition(String username) {
        int index = this.getUser(username);
        this.usersList.get(index).addMoneyToWallet(100);
    }

    public void showInvestmentsOfUser(String username) {
        int index = this.getUser(username);
        this.usersList.get(index).printInvestments();
    }

    public void updateInvestments(String username) {
        int index = this.getUser(username);
        this.usersList.get(index).manageInvestments();
    }

    public boolean checkAllInvestmentsStatus(String username) {
        int index = this.getUser(username);
        return this.usersList.get(index).checkStatusInvestments();
    }
}
