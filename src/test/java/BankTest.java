import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {
    @Test
    public void testManageUserMoneyNegative() {
        Bank bank = new Bank();
        bank.addUser("testUser", "pass", 100);
        Assertions.assertFalse(bank.manageUserMoney("testUser",
                "depositMoney", -50, "2025-03-14"));
    }

    @Test
    public void testManageUserMoneyZero() {
        Bank bank = new Bank();
        bank.addUser("testUser", "pass", 100);
        Assertions.assertTrue(bank.manageUserMoney("testUser",
                "depositMoney", 0, "2025-03-14"));
    }

    @Test
    public void testManageUserMoneyTooMuchDeposit() {
        Bank bank = new Bank();
        bank.addUser("testUser", "pass", 100);
        Assertions.assertFalse(bank.manageUserMoney("testUser",
                "depositMoney", 150, "2025-03-14"));
    }

    @Test
    public void testManageUserMoneyDepositSuccess() {
        Bank bank = new Bank();
        bank.addUser("testUser", "pass", 100);
        Assertions.assertTrue(bank.manageUserMoney("testUser",
                "depositMoney", 50, "2025-03-14"));
    }

    @Test
    public void testManageUserMoneyTooMuchWithdraw() {
        Bank bank = new Bank();
        bank.addUser("testUser", "pass", 100);
        Assertions.assertFalse(bank.manageUserMoney("testUser",
                "withdraw", 10, "2025-03-14"));
    }

    @Test
    public void testManageUserMoneyWithdrawSuccess() {
        Bank bank = new Bank();
        bank.addUser("testUser", "pass", 100);
        bank.manageUserMoney("testUser", "depositMoney", 50, "2025-03-14");
        Assertions.assertTrue(bank.manageUserMoney("testUser",
                "withdraw", 30, "2025-03-14"));
    }
}