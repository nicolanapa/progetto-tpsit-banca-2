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
	for (int c{ static_cast<int>(investmentsList.size() - 1) }; c >= 0; c--) {
		tempInves.clear();
		tempInves += investmentsList.at(c).startingDate;
		tempInves += " | ";
		tempInves += (investmentsList.at(c).status == true)? "IN CORSO" : "FINITO";
		tempInves += " | ";
		tempInves += std::to_string(investmentsList.at(c).amount);
		tempInves.erase(tempInves.length() - 4, 4);
		tempInves += '$';
		tempInves += " | ";
		tempInves += std::to_string(investmentsList.at(c).duration);
		tempInves += " | ";
		tempInves += std::to_string(investmentsList.at(c).increaseRate);
		tempInves.erase(tempInves.length() - 4, 4);
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
		tempInves.erase(tempInves.length() - 4, 4);
		tempInves += '$';
		std::cout << tempInves << std::endl << std::endl;
	}
}

void User::manageInvestments() {
	double money{ 0 };
	for (int c{ 0 }; c < investmentsList.size(); c++) {
		if (investmentsList.at(c).status == true) {
			money = investmentsList.at(c).currentGainMoney;
			switch (investmentsList.at(c).profit_risk) {
			case 1:
				investmentsList.at(c).increaseRate = rand() % 5 + 1;
				if (rand() % 11 > 8)investmentsList.at(c).increaseRate *= -1;
				break;
			case 2:
				investmentsList.at(c).increaseRate = rand() % 10 + 6;
				if (rand() % 11 > 6)investmentsList.at(c).increaseRate *= -1;
				break;
			case 3:
				investmentsList.at(c).increaseRate = rand() % 10 + 16;
				if (rand() % 7 == 4) {
					if (rand() % 11 > 4) {
						investmentsList.at(c).increaseRate = (rand() % 50 + 101);
					}
					else {
						investmentsList.at(c).increaseRate = (rand() % 50 + 101) * (-1);
					}
				}
				else {
					investmentsList.at(c).increaseRate = rand() % 10 + 16;
					if (rand() % 11 > 4)investmentsList.at(c).increaseRate *= -1;
				}
				break;
			}
			money = investmentsList.at(c).increaseRate / 100 * abs(money);
			investmentsList.at(c).currentGainMoney += money;
			investmentsList.at(c).currentMonth += 1;
			if (investmentsList.at(c).currentMonth == investmentsList.at(c).duration) {
				bankBalance += investmentsList.at(c).currentGainMoney;
				investmentsList.at(c).status = false;
			}
		}
	}
}