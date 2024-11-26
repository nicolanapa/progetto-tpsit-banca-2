#pragma once
#include "Investment.h"
#include <string>
#include <vector>

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
};

