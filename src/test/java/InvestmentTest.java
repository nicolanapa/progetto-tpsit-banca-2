import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.json.simple.JSONObject;

public class InvestmentTest {
    @Test
    public void testInvestmentInitialStatus() {
        Investment inv = new Investment(100, 0, 12, 2, "January/2025");
        Assertions.assertEquals(100, inv.getAmount());
        Assertions.assertEquals(0, inv.getCurrentMonth());
        Assertions.assertEquals(100, inv.getCurrentGainedMoney());
        Assertions.assertTrue(inv.getStatus());
    }

    @Test
    public void testReturnObject() {
        Investment inv = new Investment(100, 0, 12, 2, "January/2025");
        JSONObject obj = inv.returnObject();
        Assertions.assertTrue(obj.containsKey("amount"));
        Assertions.assertTrue(obj.containsKey("increasedRate"));
        Assertions.assertTrue(obj.containsKey("duration"));
        Assertions.assertTrue(obj.containsKey("profitRisk"));
        Assertions.assertTrue(obj.containsKey("currentMonth"));
        Assertions.assertTrue(obj.containsKey("currentGainedMoney"));
        Assertions.assertTrue(obj.containsKey("status"));
        Assertions.assertTrue(obj.containsKey("startingDate"));
    }
}