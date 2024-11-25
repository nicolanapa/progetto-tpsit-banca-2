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

