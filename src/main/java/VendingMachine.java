import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

    public static final String QUARTER = "QUARTER";
    public static final String NICKEL = "NICKEL";
    public static final String DIME = "DIME";


    private String display;
    private ArrayList<String> coinReturnContents;
    private ArrayList<String> productDispensationContents;
    private double amountInserted;
    private NumberFormat currencyFormat;
    private ProductManager productManager;
    private CoinManager coinManager;


    public void start() {
        currencyFormat = NumberFormat.getCurrencyInstance();
        coinReturnContents = new ArrayList<String>();
        productDispensationContents = new ArrayList<String>();
        productManager = new ProductManager();
        coinManager = new CoinManager();

        display = "INSERT COIN";
        amountInserted = 0.0;
    }

    public void insertCoin(String coinType) {
        if (coinType == null) {
            return;
        }

        if (coinType.equals(QUARTER)) {
            amountInserted += 0.25;
            coinManager.addCoin(QUARTER);
        } else if (coinType.equals(DIME)) {
            amountInserted += 0.1;
            coinManager.addCoin(DIME);
        } else if (coinType.equals(NICKEL)) {
            amountInserted += 0.05;
            coinManager.addCoin(NICKEL);
        } else {
            coinReturnContents.add(coinType);
        }

        resetDisplay();
    }

    public String readDisplay() {
        String displayValue = display;

        resetDisplay();

        return displayValue;
    }

    private void resetDisplay() {
        if (amountInserted < 0.01) {   // Accounts for Double delta
            if (coinManager.hasChange()) {
                display = "INSERT COIN";
            } else {
                display = "EXACT CHANGE ONLY";
            }

        } else {
            display = currencyFormat.format(amountInserted);
        }
    }

    public void pressButton(String product) {
        if ( ! productManager.checkProduct(product) || product == null) {
            display = "SOLD OUT";
            return;
        }

        double price = productManager.getPrice(product);

        if (price > amountInserted) {
            display = "PRICE " + currencyFormat.format(price);
        } else {
            if (productManager.dispense(product)) {
                productDispensationContents.add(product);
                display = "THANK YOU";

                returnCoins(price);
            } else {
                display = "SOLD OUT";
            }
        }
    }

    public void pressReturnCoinsButton() {
        returnCoins(0.0);
    }

    public void stockMachine(int colaStock, int candyStock, int chipsStock) {
        productManager = new ProductManager(colaStock, candyStock, chipsStock);
    }

    public void stockCoins(int quarterStock, int dimeStock, int nickelStock) {
        coinManager = new CoinManager(quarterStock, dimeStock, nickelStock);
        resetDisplay();
    }

    private void returnCoins(double price) {
        while(price + 0.01 < amountInserted) {
            if (price + 0.25 <= amountInserted) {
                if (coinManager.returnCoin(QUARTER)) {
                    coinReturnContents.add(QUARTER);
                    amountInserted -= 0.25;
                }
            }
            if (price + 0.10 <= amountInserted) {
                if (coinManager.returnCoin(DIME)) {
                    coinReturnContents.add(DIME);
                    amountInserted -= 0.10;
                }
            }
            if (price + 0.05 <= amountInserted) {
                if (coinManager.returnCoin(NICKEL)) {
                    coinReturnContents.add(NICKEL);
                    amountInserted -= 0.05;
                }
            }
        }

        amountInserted = 0.0;
    }


    public ArrayList<String> getCoinReturnContents() {
        return coinReturnContents;
    }

    public ArrayList<String> getProductDispensationContents() {
        return productDispensationContents;
    }


}
