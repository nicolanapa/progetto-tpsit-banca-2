import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import java.util.Vector;

public class User {
    private final String username;
    private final String hashedPassword;
    private double walletMoney;
    private double bankBalance;
    private Vector<Investment> investmentsList = new Vector<Investment>();
    private static final Argon2PasswordEncoder argon2 = new Argon2PasswordEncoder(16, 32, 1, 65536, 10);

    public User(String username, String password, double walletMoney) {
        this(username, password, walletMoney, 0);
    }

    public User(String username, String password,
                double walletMoney, double bankBalance) {
        this.username = username;
        this.hashedPassword = argon2.encode(password);
        this.walletMoney = walletMoney;
        this.bankBalance = bankBalance;
    }

    public boolean checkIfPasswordMatches(String password) {
        return argon2.matches(password, this.hashedPassword);
    }

    // Getters

    public String getUsername() {
        return this.username;
    }

    public double getBankBalance() {
        return this.bankBalance;
    }

    public double getWalletMoney() {
        return this.walletMoney;
    }

    // Managing Money

    public void addMoneyToBalanceFromWallet(double money) {
        this.bankBalance += money;
        this.walletMoney -= money;
    }

    public void addMoneyToWallet(double money) {
        this.walletMoney += money;
    }

    public void withdrawMoneyFromBalanceToWallet(double money) {
        this.bankBalance -= money;
        this.walletMoney += money;
    }

    public void removeMoneyBalance(double money) {
        this.bankBalance -= money;
    }

    // Investments related

    public void addInvestment(double amount, int increasedRate, int duration, int profitRisk, String startingDate) {
        Investment tempInvestment = new Investment(amount, increasedRate, duration, profitRisk, startingDate);
        investmentsList.addElement(tempInvestment);
    }

    public void printInvestments() {
        StringBuilder tempInvestment = new StringBuilder();
        for (int i = investmentsList.size() - 1; i >= 0; i--) {
            // tempInvestment.clear();

            tempInvestment.append(investmentsList.get(i).getStartingDate());
            tempInvestment.append(" | ");
            tempInvestment.append((investmentsList.get(i).getStatus()) ? "IN CORSO" : "FINITO");
            tempInvestment.append(" | ");
            tempInvestment.append(investmentsList.get(i).getAmount());

            // ?
            // tempInvestment.toString().erase(tempInvestment.length() - 4, 4);

            tempInvestment.append('$');
            tempInvestment.append(" | ");
            tempInvestment.append(investmentsList.get(i).getDuration());
            tempInvestment.append(" | ");
            tempInvestment.append(investmentsList.get(i).getIncreasedRate());

            // ?
            // tempInvestment.toString().erase(tempInvestment.length() - 4, 4);

            tempInvestment.append('%');
            tempInvestment.append(" | ");

            if (investmentsList.get(i).getProfitRisk() == 1) {
                tempInvestment.append("BASSO RISCHIO");
            } else if (investmentsList.get(i).getProfitRisk() == 2) {
                tempInvestment.append("MEDIO RISCHIO");
            } else {
                tempInvestment.append("ALTO RISCHIO");
            }

            tempInvestment.append(" | ");
            tempInvestment.append(investmentsList.get(i).getCurrentGainedMoney());

            // ?
            // tempInvestment.toString().erase(tempInvestment.length() - 4, 4);

            tempInvestment.append('$');

            System.out.println(tempInvestment.toString());
            System.out.println();
        }
    }

    public void manageInvestments() {
        double money;

        for (int i = 0; i < investmentsList.size(); i++) {
            if (investmentsList.get(i).getStatus()) {
                money = investmentsList.get(i).getCurrentGainedMoney();

                switch (investmentsList.get(i).getProfitRisk()) {
                    case 1:
                        investmentsList.get(i).setIncreasedRate(Math.random() % 5 + 1);

                        if (Math.random() % 11 > 8) {
                            investmentsList.get(i).setIncreasedRate(investmentsList.get(i).getIncreasedRate() * -1);
                        }
                        break;
                    case 2:
                        investmentsList.get(i).setIncreasedRate(Math.random() % 10 + 6);

                        if (Math.random() % 11 > 6) {
                            investmentsList.get(i).setIncreasedRate(investmentsList.get(i).getIncreasedRate() * -1);
                        }

                        break;
                    case 3:
                        investmentsList.get(i).setIncreasedRate(Math.random() % 10 + 16);
                        if (Math.random() % 7 == 4) {
                            if (Math.random() % 11 > 4) {
                                investmentsList.get(i).setIncreasedRate(Math.random() % 50 + 101);
                            } else {
                                investmentsList.get(i).setIncreasedRate((Math.random() % 50 + 101) * -1);
                            }
                        } else {
                            investmentsList.get(i).setIncreasedRate(Math.random() % 10 + 16);

                            if (Math.random() % 11 > 4) {
                                investmentsList.get(i).setIncreasedRate(investmentsList.get(i).getIncreasedRate() * -1);
                            }
                        }

                        break;
                }

                money = investmentsList.get(i).getIncreasedRate() / 100 * Math.abs(money);

                investmentsList.get(i).setCurrentGainedMoney(investmentsList.get(i).getCurrentGainedMoney() + money);
                investmentsList.get(i).setCurrentMonth(investmentsList.get(i).getCurrentMonth() + 1);
                if (investmentsList.get(i).getCurrentMonth() == investmentsList.get(i).getDuration()) {
                    bankBalance += investmentsList.get(i).getCurrentGainedMoney();
                    investmentsList.get(i).setStatus(false);
                }
            }
        }
    }

    public boolean checkStatusInvestments() {
        for (int i = 0; i < investmentsList.size(); i++) {
            if (investmentsList.get(i).getStatus()) {
                return false;
            }
        }

        return true;
    }

    public static void testArgon2() {
        Argon2PasswordEncoder argon2 = new Argon2PasswordEncoder(16, 32, 1, 65536, 10);
        String test = "1234";
        String test2 = "5678";
        String hash = argon2.encode(test);

        System.out.println(hash);
        System.out.println(true + " " + argon2.matches(test, hash));
        System.out.println(false + " " + argon2.matches(test2, hash));
    }

    public static void main(String[] args) {
        testArgon2();
    }
}
