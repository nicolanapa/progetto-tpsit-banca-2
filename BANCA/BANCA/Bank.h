#ifndef BANK_H
#define BANK_H

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
	bool checkUsername(std::string);
	double getUserBalance(std::string user);
	double getUserUndepositedMoney(std::string user);
	bool depositMoney(std::string user, double money);
	bool withdrawMoney(std::string user, double money);
	void makeInvestment(std::string user, double money, int increaseRate, int period, int profRisk, std::string date0);
	void monthlyMoneyAddition(std::string user);
	void showInvestments(std::string user);
	void updateInvestments(std::string user);
	bool checkAllInvestmentsStatus(std::string user);
};

#endif