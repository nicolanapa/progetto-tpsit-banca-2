#include <iostream>
#include <vector>
#include <string>
#include <cstdlib>
#include <iomanip>
#include <climits>
#include <limits>
#include <ctime>
#include "Bank.h"
#include "User.h"

#ifdef _WIN32
#define CLEAR_COMMAND "cls"
#else
#define CLEAR_COMMAND "clear"
#endif

static void clearScreen() {
    system(CLEAR_COMMAND);
}

void resetCin();
void printMenu();
void printCategoriesInvestments();
void printSubcategoriesInvestments();
std::string getMonth();
void printDate();
void printInfoInvestment();

int year = 2024;
int month = 1;

int main() {
    srand(static_cast<unsigned>(time(NULL)));
    std::cout << std::fixed << std::setprecision(2);

    Bank firstBank;

    std::string username, tempData;
    double tempMoney{ 0 };
    int periodSkip{ 0 };
    char tempChar;
    int mainAction{ 0 };
    int investmentAction{ 0 };
    int subAction{ 0 };
    int timeSkipChoice{ 0 };


    std::cout << "WELCOME TO THE BANK\n\n";
    std::cout << "(After entering the data, you will be redirected inside the bank)\n\n";
    std::cout << "Enter the username: ";
    std::cin >> username;
    std::cout << "\nInsert the money that you have in your wallet: ";
    std::cin >> tempMoney;
    firstBank.addUser(username, tempMoney);

    while (true) {
        if (periodSkip == 0) {

            do {
                resetCin();
                clearScreen();
                printMenu();
                if (!(std::cin >> mainAction)) continue;

                switch (mainAction) {
                case 1:
                    clearScreen();
                    std::cout << "\nBalance: " << firstBank.getUserBalance(username) << "$\n\n";
                    std::cout << "Enter any character to continue: ";
                    std::cin >> tempChar;
                    break;

                case 2:
                    clearScreen();
                    std::cout << "\nMoney in Wallet: " << firstBank.getUserUndepositedMoney(username) << "$\n\n";
                    std::cout << "Enter any character to continue: ";
                    std::cin >> tempChar;
                    break;

                case 3:
                    do {
                        resetCin();
                        clearScreen();
                        std::cout << "\nUndeposited Money: " << firstBank.getUserUndepositedMoney(username) << "$\n\n";
                        std::cout << "Enter the amount to deposit: ";
                    } while (!(std::cin >> tempMoney) || !firstBank.depositMoney(username, tempMoney));
                    break;

                case 4:
                    do {
                        resetCin();
                        clearScreen();
                        std::cout << "\nDeposited Money: " << firstBank.getUserBalance(username) << "$\n\n";
                        std::cout << "Enter the amount to withdraw: ";
                    } while (!(std::cin >> tempMoney) || !firstBank.withdrawMoney(username, tempMoney));
                    break;

                case 5:
                    
                    do {
                        resetCin();
                        clearScreen();
                        printCategoriesInvestments();
                        if (!(std::cin >> investmentAction)) investmentAction = 6;
                        if (investmentAction > 5) continue;

                        if (investmentAction >= 1 && investmentAction <= 3) {

                            do {
                                resetCin();
                                clearScreen();
                                printSubcategoriesInvestments();
                            } while (!(std::cin >> subAction) || subAction > 3);
                            if (subAction == 0)continue;

                            do {
                                resetCin();
                                clearScreen();
                                std::cout << "\nBalance: " << firstBank.getUserBalance(username) << "$\n\n";
                                std::cout << "Enter the amount to invest: ";
                            } while (!(std::cin >> tempMoney) || tempMoney > firstBank.getUserBalance(username));

                            tempData = std::to_string(month) + "/" + std::to_string(year);
                            firstBank.makeInvestment(username, tempMoney, 0, investmentAction * 12, subAction, tempData);

                        }
                        else if (investmentAction == 4) {
                            clearScreen();
                            printDate();
                            std::cout << "INIZIO | " << "STATO | " << "CAPITALE INVESTITO | " << "DURATA(MESI) | " << "AUMENTO MENSILE | " << "RISCHIO | " << "GUADAGNO CORRENTE" << '\n' << '\n';
                            std::cout << "Investments:\n\n";
                            firstBank.showInvestments(username);
                            std::cout << "\nEnter any character to continue: ";
                            std::cin >> tempChar;
                        }
                        else if (investmentAction == 5) {
                            clearScreen();
                            printInfoInvestment();
                            std::cout << '\n' << '\n' << "Enter any character to continue: ";
                            std::cin >> tempChar;
                        }
                    } while (investmentAction != 0);
                    break;
                case 6:

                    do {
                        resetCin();
                        clearScreen();
                        std::cout << "Duration of the time skip:\n\n";
                        std::cout << " 1) ONE MONTH\n\n";
                        std::cout << " 2) ONE YEAR\n\n";
                        std::cout << " 0) GO BACK\n\n";
                        std::cout << "Enter your choice: ";
                    } while (!(std::cin >> timeSkipChoice) || (timeSkipChoice > 2));
                    if (timeSkipChoice == 0) mainAction = 1;
                    if (timeSkipChoice == 1) {
                        periodSkip = 1;
                    }
                    else if(timeSkipChoice == 2){
                        periodSkip = 12;
                    }
                    break;
                case 0:
                    if (firstBank.checkAllInvestmentsStatus(username) == false) {
                        clearScreen();
                        std::cout << "\n\nThere are investments running, you can't close the app.";
                        std::cout << "\n\nEnter any character to continue: ";
                        std::cin >> tempChar;
                        mainAction = 10;
                    }
                }
            } while (mainAction != 6 && mainAction != 0);
        }
        if (mainAction == 0) {
            clearScreen();
            std::cout << "THANKS FOR USING OUR BANK";
            break;
        }
        firstBank.updateInvestments(username);
        firstBank.monthlyMoneyAddition(username);
        month = (month % 12) + 1;
        if (month == 1) year++;
        periodSkip--;
    }

    return 0;
}

void resetCin() {
    std::cin.clear();
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
}

void printMenu() {
    printDate();
    std::cout << " 1) SHOW BALANCE\n\n";
    std::cout << " 2) SHOW MONEY IN WALLET\n\n";
    std::cout << " 3) DEPOSIT MONEY\n\n";
    std::cout << " 4) WITHDRAW MONEY\n\n";
    std::cout << " 5) INVESTMENT\n\n";
    std::cout << " 6) TIME SKIP\n\n";
    std::cout << " 0) CLOSE APP\n\n";
    std::cout << "Enter the action you want to perform: ";
}

void printCategoriesInvestments() {
    std::cout << "Investment Categories:\n\n";
    std::cout << " 1) SHORT-TERM INVESTMENT\n\n";
    std::cout << " 2) MEDIUM-TERM INVESTMENT\n\n";
    std::cout << " 3) LONG-TERM INVESTMENT\n\n";
    std::cout << " 4) SHOW INVESTMENTS\n\n";
    std::cout << " 5) INFO\n\n";
    std::cout << " 0) GO BACK\n\n";
    std::cout << "Enter the action you want to perform: ";
}

void printSubcategoriesInvestments() {
    std::cout << "Investment Subcategories:\n\n";
    std::cout << " 1) LOW RISK - LOW PROFIT\n\n";
    std::cout << " 2) MEDIUM RISK - MEDIUM PROFIT\n\n";
    std::cout << " 3) HIGH RISK - HIGH PROFIT\n\n";
    std::cout << " 0) GO BACK\n\n";
    std::cout << "Enter your choice: ";
}

std::string getMonth() {
    const std::string months[]{"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"};
    return months[month - 1];
}

void printDate() {
    std::string dateBorder(getMonth().length() + std::to_string(year).length() + 6, '*');
    std::cout << dateBorder << "\n";
    std::cout << "* " << year << "  " << getMonth() << " *\n";
    std::cout << dateBorder << "\n\n";
}

void printInfoInvestment() {
    std::string info{ "Gli investimenti hanno una percentuale di aumento mensile ed ogni mese quella percentuale puo cambiare, andando anche in negativo.Una volta investiti tot soldi dal bilancio i soldi verrano sottrati dal bilancio ed alla fine dell'investimento verra aggiunta la somma fatta nel corso dell'investimento.Inizialmente il guadagno corrente sara uguale al capitale investito." };
    std::cout << info;
}