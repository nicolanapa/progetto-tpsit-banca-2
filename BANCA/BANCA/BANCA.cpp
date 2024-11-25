#include <iostream>
#include <vector>
#include <string>
#include "Bank.h"
#include "User.h"

int year{ 2024 };
int month{ 1 };
int day{ 1 };

int main()
{
	Bank firstBank;
	firstBank.addUser("Nicolas", 140.55);
	firstBank.addUser("Giorgio", 140.55);
	firstBank.addUser("Giovanni", 140.55);
	firstBank.addUser("Paolo", 140.55);

	for (year; year < year + 20; year++) {
		month = 1;
		for (month; month <= 12; month++) {
			day = 1;
			for (day; day < 31; day++) {

			}
		}
	}

	return 0;
}