
public class ProductManager {

    public static final String COLA = "COLA";
    public static final String CHIPS = "CHIPS";
    public static final String CANDY = "CANDY";

    private static final double COLA_PRICE = 1.00;
    private static final double CANDY_PRICE = 0.65;
    private static final double CHIPS_PRICE = 0.50;

    private int colaStock;
    private int candyStock;
    private int chipsStock;

    public ProductManager() {
        colaStock = 3;
        candyStock = 3;
        chipsStock = 3;
    }

    public ProductManager(int colaStock, int candyStock, int chipsStock) {
        this.colaStock = colaStock;
        this.candyStock = candyStock;
        this.chipsStock = chipsStock;
    }

    public double getPrice(String product) {
        double returnValue = 0.0;

        if (product == null) {
            return returnValue;
        }

        if (product.equals(COLA)) {
            returnValue = COLA_PRICE;
        } else if (product.equals(CANDY)) {
            returnValue = CANDY_PRICE;
        } else if (product.equals(CHIPS)) {
            returnValue = CHIPS_PRICE;
        }

        return returnValue;
    }

    public boolean dispense(String product) {
        boolean returnValue = false;

        if (product == null) {
            return returnValue;
        }

        if (product.equals(COLA)) {
            if (colaStock > 0) {
                colaStock--;
                returnValue = true;
            } else {
                returnValue = false;
            }
        } else if (product.equals(CANDY)) {
            if (candyStock > 0) {
                candyStock--;
                returnValue = true;
            } else {
                returnValue = false;
            }
        } else if (product.equals(CHIPS)) {
            if (chipsStock > 0) {
                chipsStock--;
                returnValue = true;
            } else {
                returnValue = false;
            }
        }

        return returnValue;
    }

    public boolean checkProduct(String product) {
        boolean returnValue = false;

        if (product == null) {
            return returnValue;
        }

        if (product.equals(COLA)) {
            returnValue = colaStock > 0;
        } else if (product.equals(CANDY)) {
            returnValue = candyStock > 0;
        } else if (product.equals(CHIPS)) {
            returnValue = chipsStock > 0;
        }

        return returnValue;
    }

    public int getColaStock() {
        return colaStock;
    }
    public int getCandyStock() {
        return candyStock;
    }
    public int getChipsStock() {
        return chipsStock;
    }
}
