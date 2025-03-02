import java.util.Scanner;

public class Main {
    private static int year = 2024;
    private static int month = 1;
    private static String currentUserLoggedIn;

    public static void printMenu() {
        printDate();

        System.out.println();
        System.out.println(" 1) SHOW BALANCE");
        System.out.println(" 2) SHOW MONEY IN WALLET");
        System.out.println(" 3) DEPOSIT MONEY");
        System.out.println(" 4) WITHDRAW MONEY");
        System.out.println(" 5) INVESTMENT");
        System.out.println(" 6) TIME SKIP");
        System.out.println(" 7) SIGN OUT FROM '" + currentUserLoggedIn + "'");
        System.out.println(" 0) CLOSE APP");
        System.out.println("Enter the action you want to perform: ");
    }

    public static void loginMenu() {
        printDate();

        System.out.println();
        System.out.println(" 1) LOGIN");
        System.out.println(" 2) SIGNUP");
        // Delete existing user choice
        // System.out.println(" 3) DELETE EXISTING USER");
        System.out.println(" 0) CLOSE APP");
        System.out.println("Enter the action you want to perform: ");
    }

    public static void printCategoriesInvestments() {
        System.out.println();
        System.out.println("Investment Categories:");
        System.out.println(" 1) SHORT-TERM INVESTMENT");
        System.out.println(" 2) MEDIUM-TERM INVESTMENT");
        System.out.println(" 3) LONG-TERM INVESTMENT");
        System.out.println(" 4) SHOW INVESTMENTS");
        System.out.println(" 5) INFO");
        System.out.println(" 0) GO BACK");
        System.out.println("Enter the action you want to perform: ");
    }

    public static void printSubcategoriesInvestments() {
        System.out.println();
        System.out.println("Investment Subcategories:");
        System.out.println(" 1) LOW RISK - LOW PROFIT");
        System.out.println(" 2) MEDIUM RISK - MEDIUM PROFIT");
        System.out.println(" 3) HIGH RISK - HIGH PROFIT");
        System.out.println(" 0) GO BACK");
        System.out.println("Enter your choice: ");
    }

    public static void timeSkipMenu() {
        System.out.println("Duration of the time skip:");
        System.out.println(" 1) ONE MONTH");
        System.out.println(" 2) ONE YEAR");
        System.out.println(" 0) GO BACK");
        System.out.println("Enter your choice: ");
    }

    public static String getMonth() {
        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return months[month - 1];
    }

    public static void printDate() {
        String dateBorder = "*".repeat(getMonth().length() + String.valueOf(year).length() + 6);
        System.out.println(dateBorder);
        System.out.println("* " + year + " " + getMonth() + " *");
        System.out.println(dateBorder);
    }

    public static void printInfoInvestment() {
        String info = "Gli investimenti hanno una percentuale di aumento mensile ed ogni mese quella percentuale puo cambiare, andando anche in negativo.Una volta investiti tot soldi dal bilancio i soldi verrano sottrati dal bilancio ed alla fine dell'investimento verra aggiunta la somma fatta nel corso dell'investimento.Inizialmente il guadagno corrente sara uguale al capitale investito.";
        System.out.println(info);
    }

    public static void main(String[] args) {
        Bank firstBank = new Bank();

        Scanner scanner = new Scanner(System.in);

        String username, password, tempData;
        double tempMoney;
        int periodSkip = 0, mainAction = 0;
        int investmentAction, subAction, timeSkipChoice, loginChoice;
        // Redundant ?
        char tempChar;

        System.out.println("WELCOME TO THE BANK");
        System.out.println("(After entering the data, you will be redirected " + "inside the bank)");

        // Move this into a login method, to be reused when logging in
        // TBD, Create a signup or one single method to handle both
        // the login and signup of users
        System.out.println("Enter your username: ");
        username = scanner.next();
        currentUserLoggedIn = username;

        System.out.println("Enter your password: ");
        password = scanner.next();
        System.out.println("Insert the money that you have in your wallet: ");
        tempMoney = scanner.nextDouble();

        firstBank.addUser(username, password, tempMoney);

        while (true) {
            if (periodSkip == 0) {
                do {
                    printMenu();
                    mainAction = scanner.nextInt();

                    if (mainAction < 0 || mainAction > 7) {
                        continue;
                    }

                    switch (mainAction) {
                        case 0:
                            if (!firstBank.checkAllInvestmentsStatus(username)) {
                                System.out.println("There are investments running, you can't close the app.");
                                System.out.println("Enter any character to continue: ");
                                tempChar = scanner.next().charAt(0);
                                mainAction = 10;
                            }

                            break;
                        case 1:
                            System.out.println("Balance: " + firstBank.getUserBalance(username, "bankBalance") + "$");
                            System.out.println("Enter any character to continue: ");

                            tempChar = scanner.next().charAt(0);
                            break;
                        case 2:
                            System.out.println("Money in Wallet: " + firstBank.getUserBalance(username, "walletBalance") + "$");
                            System.out.println("Enter any character to continue: ");

                            tempChar = scanner.next().charAt(0);
                            break;
                        case 3:
                            do {
                                System.out.println("Wallet Money: " + firstBank.getUserBalance(username, "walletBalance") + "$");
                                System.out.println("Enter the amount to deposit: ");

                                tempMoney = scanner.nextDouble();
                            } while (!(firstBank.manageUserMoney(username, "depositMoney", tempMoney)));

                            break;
                        case 4:
                            do {
                                System.out.println("Deposited Money: " + firstBank.getUserBalance(username, "bankBalance") + "$");
                                System.out.println("Enter the amount to withdraw: ");

                                tempMoney = scanner.nextDouble();
                            } while (!(firstBank.manageUserMoney(username, "withdrawMoney", tempMoney)));

                            break;
                        case 5:
                            do {
                                printCategoriesInvestments();
                                investmentAction = scanner.nextInt();

                                if (investmentAction < 0 || investmentAction > 5) {
                                    continue;
                                }

                                if (investmentAction >= 1 && investmentAction <= 3) {
                                    do {
                                        printSubcategoriesInvestments();

                                        subAction = scanner.nextInt();
                                    } while (subAction > 3);

                                    if (subAction == 0) {
                                        continue;
                                    }

                                    do {
                                        System.out.println("Balance: " + firstBank.getUserBalance(username, "bankBalance") + "$");
                                        System.out.println("Enter the amount to invest: ");

                                        tempMoney = scanner.nextDouble();
                                    } while (tempMoney > firstBank.getUserBalance(username, "bankBalance"));

                                    tempData = month + "/" + year;
                                    firstBank.makeInvestment(username, tempMoney, 0, investmentAction * 12, subAction, tempData);
                                } else if (investmentAction == 4) {
                                    printDate();
                                    System.out.println("INIZIO | " + "STATO | " + "CAPITALE INVESTITO | " + "DURATA(MESI) | " + "AUMENTO MENSILE | " + "RISCHIO | " + "GUADAGNO CORRENTE");
                                    System.out.println("Investments:");
                                    firstBank.showInvestmentsOfUser(username);
                                    System.out.println("Enter any character to continue: ");

                                    tempChar = scanner.next().charAt(0);
                                } else if (investmentAction == 5) {
                                    printInfoInvestment();
                                    System.out.println("Enter any character to continue: ");

                                    tempChar = scanner.next().charAt(0);
                                }
                            } while (investmentAction != 0);
                            break;
                        case 6:
                            do {
                                timeSkipMenu();

                                timeSkipChoice = scanner.nextInt();
                            } while (timeSkipChoice > 2);

                            if (timeSkipChoice == 0) {
                                mainAction = 1;
                            }
                            if (timeSkipChoice == 1) {
                                periodSkip = 1;
                            } else if (timeSkipChoice == 2) {
                                periodSkip = 12;
                            }

                            break;
                        case 7:
                            do {
                                loginMenu();

                                loginChoice = scanner.nextInt();
                            } while (loginChoice > 2);

                            if (loginChoice == 0) {
                                mainAction = 0;
                            }

                            break;
                    }
                } while (mainAction != 8 && mainAction != 0);
            }

            if (mainAction == 0) {
                System.out.println("THANKS FOR USING OUR BANK");

                break;
            }

            firstBank.updateInvestments(username);
            firstBank.monthlyMoneyAddition(username);

            month = (month % 12) + 1;

            if (month == 1) {
                year++;
            }

            periodSkip--;
        }
    }
}
