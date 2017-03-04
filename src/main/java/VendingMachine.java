import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

    private static final String QUARTER = "QUARTER";
    private static final String NICKEL = "NICKEL";
    private static final String DIME = "DIME";


    private String display;
    private ArrayList<String> coinReturnContents;
    private ArrayList<String> productDispensationContents;
    private double amountInserted;
    private NumberFormat currencyFormat;
    private ProductManager productManager;



    public void start() {
        currencyFormat = NumberFormat.getCurrencyInstance();
        coinReturnContents = new ArrayList<String>();
        productDispensationContents = new ArrayList<String>();
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

        resetDisplay();
    }

    public String readDisplay() {
        String displayValue = display;

        resetDisplay();

        return displayValue;
    }

    private void resetDisplay() {
        if (amountInserted < 0.01) {   // Accounts for Double delta
            display = "INSERT COIN";
        } else {
            display = currencyFormat.format(amountInserted);
        }
    }

    public void pressButton(String product) {
        double price = productManager.getPrice(product);

        if ( ! productManager.checkProduct(product)) {
            display = "SOLD OUT";
            return;
        }

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

    private void returnCoins(double price) {
        while(price + 0.01 < amountInserted) {
            if (price + 0.25 <= amountInserted) {
                coinReturnContents.add(QUARTER);
                amountInserted -= 0.25;
            }
            if (price + 0.10 <= amountInserted) {
                coinReturnContents.add(DIME);
                amountInserted -= 0.10;
            }
            if (price + 0.05 <= amountInserted) {
                coinReturnContents.add(NICKEL);
                amountInserted -= 0.05;
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
