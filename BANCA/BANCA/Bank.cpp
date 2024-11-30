#include "Bank.h"


void Bank::addUser(std::string name, double moneyInWallet) {
	User temporaryUser(name, moneyInWallet);
	usersList.push_back(temporaryUser);
}

bool Bank::checkUsername(std::string name) {
	for (size_t c{ 0 }; c < usersList.size(); c++) {
		if (usersList.at(c).getUsername() == name) {
			return false;
		}
	}
	return true;
}

int Bank::getUser(std::string name) const {
	int index{ -1 };
	for (size_t c{ 0 }; c < usersList.size(); c++) {
		if (usersList.at(c).getUsername() == name) {
			index = c;
			break;
		}
	}
	return index;
}

double Bank::getUserBalance(std::string user) {
	double balance{ 0.0 };
	int pos{ getUser(user) };
	if (pos != -1) {
		balance = usersList.at(pos).getBankBalance();
	}
	else {
		std::cout << "Utente non presente nell'elenco bancario.";
		return 0;
	}
	return balance;
}

double Bank::getUserUndepositedMoney(std::string user) {
	double balance{ 0.0 };
	int pos{ getUser(user) };
	if (pos != -1) {
		balance = usersList.at(pos).getWalletMoney();
	}
	else {
		std::cout << "Utente non presente nell'elenco bancario.";
		return 0;
	}
	return balance;
}

bool Bank::depositMoney(std::string user, double money) {
	int pos{ getUser(user) };
	if (money > usersList.at(pos).getWalletMoney()) {
		return false;
	}
	usersList.at(pos).addMoneyToBalance(money);
	return true;
}

bool Bank::withdrawMoney(std::string user, double money) {
	int pos{ getUser(user) };
	if (money == 0) return true;
	if (money > usersList.at(pos).getBankBalance()) {
		return false;
	}
	usersList.at(pos).withdrawMoneyFromBalance(money);
	return true;
}

void Bank::monthlyMoneyAddition(std::string user) {
	int pos{ getUser(user) };
	usersList.at(pos).addMoneyWallet(100);
}

void Bank::makeInvestment(std::string user, double money, int increaseRate, int period, int profRisk, std::string date0) {
	int pos{ getUser(user) };
	usersList.at(pos).addInvestment(money, increaseRate, period, profRisk, date0);
	usersList.at(pos).removeMoneyBalance(money);
}

void Bank::showInvestments(std::string user) {
	int pos{ getUser(user) };
	usersList.at(pos).printInvestments();
}

void Bank::updateInvestments(std::string user) {
	int pos{ getUser(user) };
	usersList.at(pos).manageInvestments();
}