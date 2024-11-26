#include <iostream>
#include <vector>
#include <string>
#include <cstdlib>
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

void printSubcategoriesInvestments();

int year{ 2024 };
int month{ 1 };

int main()
{
	Bank firstBank;

	int action1{ 0 }, tempMoney{ 0 }, action2{ 0 }, action3{ 0 };
	char temp{ 0 };
	std::string user;
	std::cout << "WELCOME TO THE BANK" << std::endl;
	std::cout << std::endl << "(After entering the data you will be redirected inside the bank)" << std::endl << std::endl;
	std::cout << "Enter the username: ";
	std::cin >> user;
	std::cout << std::endl << "Insert the money that you have in your wallet: ";
	std::cin >> tempMoney;
	firstBank.addUser(user, tempMoney);
	bool check{ true };
	

	do {
		clearScreen();
		std::cout << "  1) SHOW BALANCE" << std::endl << std::endl;
		std::cout << "  2) SHOW MONEY IN YOU WALLET" << std::endl << std::endl;
		std::cout << "  3) DEPOSIT MONEY" << std::endl << std::endl;
		std::cout << "  4) WITHDRAW MONEY" << std::endl << std::endl;
		std::cout << "  5) INVESTMENT" << std::endl << std::endl;
		std::cout << "  0) ESC" << std::endl << std::endl;
		std::cout << "Enter the action that you want to perform: ";
		std::cin >> action1;
		switch (action1) {
		case 1:
			clearScreen();
			std::cout << std::endl << "Your balance is: " << firstBank.getUserBalance(user) << '$';
			std::cout << std::endl << std::endl << "Enter any character to continue ";
			std::cin >> temp;
			break;
		case 2:
			clearScreen();
			std::cout << std::endl << "Your balance is: " << firstBank.getUserUndepositedMoney(user) << '$';
			std::cout << std::endl << std::endl << "Enter any chracter to continue ";
			std::cin >> temp;
			break;
		case 3:
			clearScreen();
			std::cout << "Undeposited money: " << firstBank.getUserUndepositedMoney(user) << '$' << std::endl << std::endl;
			std::cout << "How much money do you want do deposit: ";
			std::cin >> tempMoney;
			while (firstBank.depositMoney(user, tempMoney) != true) {
				clearScreen();
				std::cout << "You don't have that much in you wallet" << std::endl;
				std::cout << "Undeposited money: " << firstBank.getUserUndepositedMoney(user) << '$' << std::endl << std::endl;
				std::cout << "How much money do you want do deposit: ";
				std::cin >> tempMoney;
			}
			break;
		case 4:
			clearScreen();
			std::cout << "Deposited money: " << firstBank.getUserBalance(user) << '$' << std::endl << std::endl;
			std::cout << "How much money do you want to withdraw: ";
			std::cin >> tempMoney;
			while (firstBank.withdrawMoney(user, tempMoney) != true) {
				clearScreen();
				std::cout << "You don't have that much in you bank" << std::endl;
				std::cout << "Deposited money: " << firstBank.getUserBalance(user) << '$' << std::endl << std::endl;
				std::cout << "How much money do you want to withdraw: ";
				std::cin >> tempMoney;
			}
			break;
		case 5:
			do {
				clearScreen();
				std::cout << "Categories of investments" << std::endl << std::endl;
				std::cout << " 1) SHORT-TERM INVESTMENT" << std::endl << std::endl;
				std::cout << " 2) MEDIUM-TERM INVESTMENT" << std::endl << std::endl;
				std::cout << " 3) LONG-TERM INVESTMENT" << std::endl << std::endl;
				std::cout << " 0) GO BACK" << std::endl << std::endl;
				std::cout << "Enter the action tha you want to perform: ";
				std::cin >> action2;
				switch (action2) {
				case 1:
					do {
						printSubcategoriesInvestments();
						std::cout << "Enter the action tha you want to perform: ";
						std::cin >> action3;
					} while (action3 != 0);
					break;
				case 2:
					do {
						printSubcategoriesInvestments();
						std::cout << "Enter the action tha you want to perform: ";
						std::cin >> action3;
					} while (action3 != 0);
					break;
				case 3:
					do {
						printSubcategoriesInvestments();
						std::cout << "Enter the action tha you want to perform: ";
						std::cin >> action3;
					} while (action3 != 0);
					break;
				}
			} while (action2 != 0);
			break;
		}

	} while (action1 != 0);

	return 0;
}

void printSubcategoriesInvestments() {
	clearScreen();
	std::cout << "Subcategories of investments" << std::endl << std::endl;
	std::cout << " 1) LOW RISK - LOW PROFIT" << std::endl << std::endl;
	std::cout << " 2) MEDIUM RISK - MEDIUM PROFIT" << std::endl << std::endl;
	std::cout << " 3) HIGH RISK - HIGH PROFIT" << std::endl << std::endl;
	std::cout << " 0) GO BACK" << std::endl << std::endl;
}
