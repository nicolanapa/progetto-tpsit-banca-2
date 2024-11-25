#pragma once
#include "User.h"
#include <iostream>
#include <vector>
#include <string>


class Bank
{
private:
	std::vector <User> usersList;
	int getUser(std::string name) const;
public:
	void addUser(std::string name, double moneyInWallet);
	double getUserBalance(std::string user);
	double getUserUndepositedMoney(std::string user);
	void depositMoney(std::string user, double money);
	void withdrawMoney(std::string user, double money);
};

