#include "Bank.h"


void Bank::addUser(std::string name, double moneyInWallet) {
	User temporaryUser(name, moneyInWallet);
	usersList.push_back(temporaryUser);
}

double Bank::getUserBalance(std::string user) {
	bool check{ false };
	double balance{ 0.0 };
	for (size_t c{ 0 }; c < usersList.size(); c++) {
		if (usersList.at(c).getUsername() == user) {
			balance = usersList.at(c).getBankBalance();
			check = true;
		}
	}
	if (check == false) {
		std::cout << "Utente non presente nell'elenco bancario.";
		return 0;
	}
	return balance;
}
