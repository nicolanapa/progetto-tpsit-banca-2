#pragma once
#include <string>

class User
{
private:
	std::string username;
	double walletMoney;
	double bankBalance;
public:
	User(std::string name, double moneyInWallet);
	std::string getUsername() const;
	double getBankBalance() const;
};

