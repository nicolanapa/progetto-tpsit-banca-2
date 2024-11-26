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
	if (money > usersList.at(pos).getBankBalance()) {
		return false;
	}
	usersList.at(pos).withdrawMoneyFromBalance(money);
	return true;
}