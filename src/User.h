#ifndef USER_H
#define USER_H

#include "Investment.h"
#include <string>
#include <vector>
#include <iostream>

class User
{
private:
	std::string username;
	double walletMoney;
	double bankBalance;
	std::vector <Investment> investmentsList;
public:
	User(std::string name, double moneyInWallet);
	std::string getUsername() const;
	double getBankBalance() const;
	double getWalletMoney() const;
	void addMoneyToBalance(double money);
	void withdrawMoneyFromBalance(double money);
	void addMoneyWallet(double money);
	void addInvestment(double money, int increaseRate, int period, int profRisk, std::string date0);
	void removeMoneyBalance(double money);
	void printInvestments();
	void manageInvestments();
	bool checkStatusInvestments();
};

#endif

