import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionTest {
    @Test
    public void testTransactionCreation() {
        User user = new User("sender", "pass", 100, 200);
        Transaction transaction = new Transaction(user, user, 50,
                "deposit", "2025-03-14");
        Assertions.assertEquals(50, transaction.getAmount());
        Assertions.assertEquals("sender", transaction.getSender());
        Assertions.assertEquals("sender", transaction.getReceiver());
        Assertions.assertEquals("deposit", transaction.getAction());
        Assertions.assertEquals("2025-03-14", transaction.getDate());
    }
}