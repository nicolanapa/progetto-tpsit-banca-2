#include "Investment.h"

Investment::Investment(double money, int increaseRate, int period, int profRisk, std::string date0) {
	amount = money;
	this->increaseRate = increaseRate;
	duration = period;
	profit_risk = profRisk;
	currentMonth = 0;
	currentGainMoney = 0;
	status = true;
	startingDate = date0;
}
