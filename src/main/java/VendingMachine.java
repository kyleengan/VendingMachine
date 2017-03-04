import java.util.ArrayList;

public class VendingMachine {

    private String display;
    private ArrayList<String> coinReturnContents;

    public void start() {
        display = "INSERT COIN";
        coinReturnContents = new ArrayList<String>();
    }

    public String readDisplay() {
        return display;
    }

    public void insertCoin() {
        coinReturnContents.add("Coin.");
    }

    public ArrayList<String> getCoinReturnContents() {
        return coinReturnContents;
    }
}
