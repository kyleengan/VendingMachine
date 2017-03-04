import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

    private static final String QUARTER = "QUARTER";
    private static final String NICKEL = "NICKEL";

    private String display;
    private ArrayList<String> coinReturnContents;
    private double amountInserted;


    public void start() {
        display = "INSERT COIN";
        coinReturnContents = new ArrayList<String>();
        amountInserted = 0.0;
    }

    public void insertCoin(String coinType) {
        if (coinType.equals(QUARTER)) {
            amountInserted += 0.25;
        } else if (coinType.equals(NICKEL)) {
            amountInserted += 0.05;
        } else {
            coinReturnContents.add(coinType);
        }

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        display = currencyFormat.format(amountInserted);
    }

    public ArrayList<String> getCoinReturnContents() {
        return coinReturnContents;
    }

    public String readDisplay() {
        return display;
    }
}
