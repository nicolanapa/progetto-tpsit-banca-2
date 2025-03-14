import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.json.simple.JSONObject;

public class UserTest {
    @Test
    public void testCheckPassword() {
        User user = new User("tester", "secret", 100);
        Assertions.assertTrue(user.checkIfPasswordMatches("secret"));
        Assertions.assertFalse(user.checkIfPasswordMatches("wrong"));
    }

    @Test
    public void testReturnObject() {
        User user = new User("tester", "secret", 100, 50);
        JSONObject obj = user.returnObject();
        Assertions.assertTrue(obj.containsKey("username"));
        Assertions.assertTrue(obj.containsKey("hashedPassword"));
        Assertions.assertTrue(obj.containsKey("walletMoney"));
        Assertions.assertTrue(obj.containsKey("bankBalance"));
        Assertions.assertTrue(obj.containsKey("investmentsList"));
    }
}