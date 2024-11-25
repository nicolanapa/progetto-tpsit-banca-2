#include <iostream>
#include <vector>
#include <string>
#include "Bank.h"
#include "User.h"

int main()
{
	Bank firstBank;
	firstBank.addUser("Nicolas", 140.55);
	firstBank.addUser("Giorgio", 140.55);
	firstBank.addUser("Giovanni", 140.55);
	firstBank.addUser("Paolo", 140.55);
	firstBank.getUserBalance("Paolo");
	return 0;
}