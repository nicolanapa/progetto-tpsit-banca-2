#pragma once
#include <string>

class Investment
{
private:
	friend class User;
	double amount;
	double increaseRate;
	int duration;
	int profit_risk;
	int currentMonth;
	double currentGainMoney;
	bool status;
	std::string startingDate;
public:
	Investment(double money, int increaseRate, int period, int profRisk, std::string date0);
};

