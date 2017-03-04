import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

    private static final String QUARTER = "QUARTER";
    private static final String NICKEL = "NICKEL";
    private static final String DIME = "DIME";
    private static final String COLA = "COLA";
    private static final String CHIPS = "CHIPS";
    private static final String CANDY = "CANDY";

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
        } else if (coinType.equals(DIME)) {
            amountInserted += 0.1;
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

    public void pressButton(String product) {
        if (product.equals(COLA)) {
            display = "PRICE $1.00";
        } else if (product.equals(CHIPS)) {
            display = "PRICE $0.50";
        } else if (product.equals(CANDY)) {
            display = "PRICE $0.65";
        }
    }
}
