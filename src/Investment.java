import org.json.simple.JSONObject;

public class Investment {
    private double amount;
    private double increasedRate;
    private int duration;
    private int profitRisk;
    private int currentMonth;
    private double currentGainedMoney;
    private boolean status;
    private String startingDate;

    public Investment(JSONObject object) {
        System.out.println("Imported Investment: " + object);

        this.amount = (double) object.get("amount");
        this.increasedRate = (double) object.get("increasedRate");
        this.duration = ((Long) object.get("duration")).intValue();
        this.profitRisk = ((Long) object.get("profitRisk")).intValue();
        this.currentMonth = ((Long) object.get("currentMonth")).intValue();
        this.currentGainedMoney = (double) object.get("currentGainedMoney");
        this.status = (boolean) object.get("status");
        this.startingDate = object.get("startingDate").toString();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getIncreasedRate() {
        return increasedRate;
    }

    public void setIncreasedRate(double increasedRate) {
        this.increasedRate = increasedRate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getProfitRisk() {
        return profitRisk;
    }

    public void setProfitRisk(int profitRisk) {
        this.profitRisk = profitRisk;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public double getCurrentGainedMoney() {
        return currentGainedMoney;
    }

    public void setCurrentGainedMoney(double currentGainedMoney) {
        this.currentGainedMoney = currentGainedMoney;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public Investment(double amount, double increasedRate, int duration, int profitRisk,
                      String startingDate) {
        this.amount = amount;
        this.increasedRate = increasedRate;
        this.duration = duration;
        this.profitRisk = profitRisk;
        this.startingDate = startingDate;

        this.currentGainedMoney = amount;
        this.currentMonth = 0;
        this.status = true;
    }

    public JSONObject returnObject() {
        JSONObject investment = new JSONObject();

        investment.put("amount", this.amount);
        investment.put("increasedRate", this.increasedRate);
        investment.put("duration", this.duration);
        investment.put("profitRisk", this.profitRisk);
        investment.put("currentMonth", this.currentMonth);
        investment.put("currentGainedMoney", this.currentGainedMoney);
        investment.put("status", this.status);
        investment.put("startingDate", this.startingDate);

        return investment;
    }
}
