#include <iostream>
#include <vector>
#include <string>
#include <cstdlib>
#include <iomanip>
#include <climits>
#include <ctime>
#include "Bank.h"
#include "User.h"


#ifdef _WIN32
#define CLEAR_COMMAND "cls"
#endif
#ifndef _WIN32
#define CLEAR_COMMAND "clear"
#endif

void clearScreen() {
	system(CLEAR_COMMAND);
}

void resetCin();
void printMenu();
void pritCategoriesInvestments();
void printSubcategoriesInvestments();
std::string getMonth();
void printDate();

int year{ 2024 };
int month{ 1 };


int main()
{
	srand(time(NULL));
	std::cout << std::fixed << std::setprecision(2);

	Bank firstBank;
	bool end{ false };

	int action1{ 0 }, action2{ 0 }, action3{ 0 }, action4{ 0 };
	double tempMoney{ 0 };
	int periodSkip{ 0 };
	char temp{ 0 };

	std::string user, tempData;
	std::cout << "WELCOME TO THE BANK" << std::endl;
	std::cout << std::endl << "(After entering the data you will be redirected inside the bank)" << std::endl << std::endl;
	std::cout << "Enter the username: ";
	std::cin >> user;
	std::cout << std::endl << "Insert the money that you have in your wallet: ";
	std::cin >> tempMoney;
	firstBank.addUser(user, tempMoney);

	while (end == false) {

		if (periodSkip == 0) {

			do {

				do {
					resetCin();
					clearScreen();
					printMenu();
				} while (!(std::cin >> action1));

				switch (action1) {
				case 1:

					clearScreen();
					std::cout << std::endl << "Balance: " << firstBank.getUserBalance(user) << '$';
					std::cout << std::endl << std::endl << "Enter any character to continue ";
					std::cin >> temp;
					break;

				case 2:

					clearScreen();
					std::cout <<  std::endl << "Money: " << firstBank.getUserUndepositedMoney(user) << '$';
					std::cout << std::endl << std::endl << "Enter any chracter to continue ";
					std::cin >> temp;
					break;

				case 3:

					do {
						resetCin();
						clearScreen();
						std::cout << "Undeposited money: " << firstBank.getUserUndepositedMoney(user) << '$' << std::endl << std::endl;
						std::cout << "How much money do you want do deposit: ";
					} while (!(std::cin >> tempMoney));

					while (firstBank.depositMoney(user, tempMoney) != true) {
						do {
							resetCin();
							clearScreen();
							std::cout << "You don't have that much in you wallet" << std::endl;
							std::cout << "Undeposited money: " << firstBank.getUserUndepositedMoney(user) << '$' << std::endl << std::endl;
							std::cout << "How much money do you want do deposit: ";
						} while (!(std::cin >> tempMoney));
					}
					break;

				case 4:

					do {
						resetCin();
						clearScreen();
						std::cout << "Deposited money: " << firstBank.getUserBalance(user) << '$' << std::endl << std::endl;
						std::cout << "How much money do you want to withdraw: ";
					} while (!(std::cin >> tempMoney));

					while (firstBank.withdrawMoney(user, tempMoney) != true) {
						do {
							resetCin();
							clearScreen();
							std::cout << "You don't have that much in you bank" << std::endl;
							std::cout << "Deposited money: " << firstBank.getUserBalance(user) << '$' << std::endl << std::endl;
							std::cout << "How much money do you want to withdraw: ";
						} while (!(std::cin >> tempMoney));
					}
					break;

				case 5:

					do {
						clearScreen();
						pritCategoriesInvestments();
						std::cin >> action2;
						switch (action2) {
						case 1:
							do {
								do {
									clearScreen();
									printSubcategoriesInvestments();
									std::cout << "Enter the action tha you want to perform: ";
								} while (!(std::cin >> action3));
							} while (action3 != 0 && action3 != 1 && action3 != 2 && action3 != 3);
							if (action3 == 0) {
								break;
							}

							do {

								do {
									resetCin();
									clearScreen();
									std::cout << "Balance: " << firstBank.getUserBalance(user) << '$' << std::endl << std::endl;
									std::cout << "Enter the money you want to invest: ";
								} while (!(std::cin >> tempMoney));

							} while (tempMoney > firstBank.getUserBalance(user));
							tempData = std::to_string(year);
							tempData += '/';
							tempData += std::to_string(month);

							switch (action3) {
							case 1:
								firstBank.makeInvestment(user, tempMoney, rand() % 101, 12, 1, tempData);
								break;
							case 2:
								firstBank.makeInvestment(user, tempMoney, rand() % 101, 12, 2, tempData);
								break;
							case 3:
								firstBank.makeInvestment(user, tempMoney, rand() % 101, 12, 3, tempData);
								break;
							default:
								break;
							}
						
							break;
						case 2:
							do {
								do {
									clearScreen();
									printSubcategoriesInvestments();
									std::cout << "Enter the action tha you want to perform: ";
								} while (!(std::cin >> action3));
							} while (action3 != 0 && action3 != 1 && action3 != 2 && action3 != 3);
							if (action3 == 0) {
								break;
							}
							do {

								do {
									resetCin();
									clearScreen();
									std::cout << "Balance: " << firstBank.getUserBalance(user) << '$' << std::endl << std::endl;
									std::cout << "Enter the money you want to invest: ";
								} while (!(std::cin >> tempMoney));

							} while (tempMoney > firstBank.getUserBalance(user));
							tempData = std::to_string(year);
							tempData += '/';
							tempData += std::to_string(month);

							switch (action3) {
							case 1:
								firstBank.makeInvestment(user, tempMoney, rand() % 101, 60, 1, tempData);
								break;
							case 2:
								firstBank.makeInvestment(user, tempMoney, rand() % 101, 60, 2, tempData);
								break;
							case 3:
								firstBank.makeInvestment(user, tempMoney, rand() % 101, 60, 3, tempData);
								break;
							default:
								break;
							}

							break;
						case 3:
							do {
								do {
									clearScreen();
									printSubcategoriesInvestments();
									std::cout << "Enter the action tha you want to perform: ";
								} while (!(std::cin >> action3));
							} while (action3 != 0 && action3 != 1 && action3 != 2 && action3 != 3);
							if (action3 == 0) {
								break;
							}
							do {

								do {
									resetCin();
									clearScreen();
									std::cout << "Balance: " << firstBank.getUserBalance(user) << '$' << std::endl << std::endl;
									std::cout << "Enter the money you want to invest: ";
								} while (!(std::cin >> tempMoney));

							} while (tempMoney > firstBank.getUserBalance(user));
							tempData = std::to_string(year);
							tempData += '/';
							tempData += std::to_string(month);

							switch (action3) {
							case 1:
								firstBank.makeInvestment(user, tempMoney, rand() % 101, 120, 1, tempData);
								break;
							case 2:
								firstBank.makeInvestment(user, tempMoney, rand() % 101, 120, 2, tempData);
								break;
							case 3:
								firstBank.makeInvestment(user, tempMoney, rand() % 101, 120, 3, tempData);
								break;
							default:
								break;
							}

							break;
						case 4:
							clearScreen();
							printDate();
							std::cout << "INIZIO | STATO | CAPITALE INVESTITO | DURATA IN MESI | AUMENTO MENSILE | RISCHIO | GUADAGNO CORRENTE" << std::endl << std::endl;
							std::cout << "Investments: " << std::endl << std::endl;
							firstBank.showInvestments(user);
							std::cout << std::endl << "Enter any character to continue: ";
							std::cin >> temp;
							break;
						}
					} while (action2 != 0);
					break;
				case 6:

					do {
						do {
							resetCin();
							clearScreen();
							std::cout << "Duration of the time skip: " << std::endl << std::endl;
							std::cout << "  1) ONE MONTH" << std::endl << std::endl;
							std::cout << "  2) ONE YEAR" << std::endl << std::endl;
							std::cout << "  0) GO BACK" << std::endl << std::endl;
							std::cout << "Enter your choice: ";

						} while (!(std::cin >> action4));
						if (action4 != 0 && action4 <= 2) {
							periodSkip = (action4 == 1) ? 1 : 12;
						}
						if (action4 == 0) {
							action1 = 1;
						}

					} while (action4 != 0 && action4 != 1 && action4 != 2);
				}

			} while (action1 != 6);
		}
		firstBank.monthlyMoneyAddition(user);
		if (month == 12) {
			year += 1;
			month = 1;
		}
		else {
			month += 1;
		}
		periodSkip -= 1;
	}
	

	return 0;
}

void resetCin() {
	std::cin.clear();
	std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
}

void printMenu() {
	printDate();
	std::cout << " 1) SHOW BALANCE" << std::endl << std::endl;
	std::cout << " 2) SHOW MONEY IN YOU WALLET" << std::endl << std::endl;
	std::cout << " 3) DEPOSIT MONEY" << std::endl << std::endl;
	std::cout << " 4) WITHDRAW MONEY" << std::endl << std::endl;
	std::cout << " 5) INVESTMENT" << std::endl << std::endl;
	std::cout << " 6) TIME SKIP" << std::endl << std::endl;
	std::cout << "Enter the action that you want to perform: ";
}

void pritCategoriesInvestments() {
	std::cout << "Categories of investments" << std::endl << std::endl;
	std::cout << " 1) SHORT-TERM INVESTMENT" << std::endl << std::endl;
	std::cout << " 2) MEDIUM-TERM INVESTMENT" << std::endl << std::endl;
	std::cout << " 3) LONG-TERM INVESTMENT" << std::endl << std::endl;
	std::cout << " 4) SHOW INVESTMENTS" << std::endl << std::endl;
	std::cout << " 0) GO BACK" << std::endl << std::endl;
	std::cout << "Enter the action tha you want to perform: ";
}

void printSubcategoriesInvestments() {
	std::cout << "Subcategories of investments" << std::endl << std::endl;
	std::cout << " 1) LOW RISK - LOW PROFIT" << std::endl << std::endl;
	std::cout << " 2) MEDIUM RISK - MEDIUM PROFIT" << std::endl << std::endl;
	std::cout << " 3) HIGH RISK - HIGH PROFIT" << std::endl << std::endl;
	std::cout << " 0) GO BACK" << std::endl << std::endl;
}

std::string getMonth() {
	switch (month) {
	case 1:
		return "January";
	case 2:
		return "February";
	case 3:
		return "March";
	case 4:
		return "April";
	case 5:
		return "May";
	case 6:
		return "June";
	case 7:
		return "July";
	case 8:
		return "August";
	case 9:
		return "September";
	case 10:
		return "October";
	case 11:
		return "November";
	case 12:
		return "Dicember";
	}
	return "";
}

void printDate() {
	size_t size{ 0 };
	std::string date, temp;
	size = std::to_string(year).length() + getMonth().length() + 6;
	for (size_t c{ 0 }; c < size; c++) {
		date += '*';
	}
	temp = date;
	date += '\n';
	date += "* ";
	date += std::to_string(year);
	date += "  ";
	date += getMonth();
	date += " *";
	date += '\n';
	date += temp;
	std::cout << date << std::endl << std::endl << std::endl;
}
