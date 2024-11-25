#pragma once
#include "User.h"
#include <iostream>
#include <vector>


class Bank
{
private:
	std::vector <User> usersList;
public:
	void addUser(std::string name, double moneyInWallet);
	double getUserBalance(std::string user);
};

