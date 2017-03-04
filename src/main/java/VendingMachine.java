import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

    private static final String QUARTER = "QUARTER";
    private static final String NICKEL = "NICKEL";
    private static final String DIME = "DIME";


    private String display;
    private ArrayList<String> coinReturnContents;
    private double amountInserted;
    private NumberFormat currencyFormat;
    private ProductManager productManager;


    public void start() {
        currencyFormat = NumberFormat.getCurrencyInstance();
        coinReturnContents = new ArrayList<String>();
        productManager = new ProductManager();

        display = "INSERT COIN";
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


        display = currencyFormat.format(amountInserted);
    }

    public ArrayList<String> getCoinReturnContents() {
        return coinReturnContents;
    }

    public String readDisplay() {
        return display;
    }

    public void pressButton(String product) {
        display = "PRICE " + currencyFormat.format(productManager.getPrice(product));
    }
}
