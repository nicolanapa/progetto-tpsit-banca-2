#include "User.h"

User::User(std::string name, double moneyInWallet)
	:username{ name }, walletMoney{ moneyInWallet }, bankBalance{ 10 } {};

std::string User::getUsername() const {
	return username;
}

double User::getBankBalance() const {
	return bankBalance;
}

double User::getWalletMoney() const {
	return walletMoney;
}

void User::addMoneyToBalance(double money) {
	bankBalance += money;
	walletMoney -= money;
}

void User::withdrawMoneyFromBalance(double money) {
	bankBalance -= money;
	walletMoney += money;
}

void User::addMoneyWallet(double money) {
	walletMoney += money;
}

void User::addInvestment(double money, int increaseRate, int period, int profRisk, std::string date0) {
	Investment tempInv(  money, increaseRate, period, profRisk, date0 );
	investmentsList.push_back(tempInv);
}

void User::removeMoneyBalance(double money) {
	bankBalance -= money;
}

void User::printInvestments() {
	std::string tempInves;
	for (size_t c{ 0 }; c < investmentsList.size(); c++) {
		tempInves.clear();
		tempInves += investmentsList.at(c).startingDate;
		tempInves += " | ";
		tempInves += (investmentsList.at(c).status == true)? "IN CORSO" : "FINITO";
		tempInves += " | ";
		tempInves += std::to_string(investmentsList.at(c).amount);
		tempInves += '$';
		tempInves += " | ";
		tempInves += std::to_string(investmentsList.at(c).duration);
		tempInves += " | ";
		tempInves += std::to_string(investmentsList.at(c).increaseRate);
		tempInves += '%';
		tempInves += " | ";
		if (investmentsList.at(c).profit_risk == 1) {
			tempInves += "BASSO RISCHIO";
		}
		else if (investmentsList.at(c).profit_risk == 2) {
			tempInves += "MEDIO RISCHIO";
		}
		else {
			tempInves += "ALTO RISCHIO";
		}
		tempInves += " | ";
		tempInves += std::to_string(investmentsList.at(c).currentGainMoney);
		tempInves += '$';
		std::cout << tempInves << std::endl << std::endl;
	}
}