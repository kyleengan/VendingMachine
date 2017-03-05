
public class CoinManager {

    private int quarterStock;
    private int dimeStock;
    private int nickelStock;

    public CoinManager() {
        this.quarterStock = 3;
        this.dimeStock = 3;
        this.nickelStock = 3;
    }

    public CoinManager(int quarterStock, int dimeStock, int nickelStock) {
        this.quarterStock = quarterStock;
        this.dimeStock = dimeStock;
        this.nickelStock = nickelStock;
    }

    public void addCoin(String type) {
        if (type == null) {
            return;
        }

        if (type.equals(VendingMachine.QUARTER)) {
            quarterStock++;
        } else if (type.equals(VendingMachine.DIME)) {
            dimeStock++;
        } else if (type.equals(VendingMachine.NICKEL)) {
            nickelStock++;
        }
    }

    public boolean returnCoin(String type) {
        boolean returnValue = false;
        if (type == null) {
            return returnValue;
        }

        if (type.equals(VendingMachine.QUARTER)) {
            if (quarterStock > 0) {
                quarterStock--;
                returnValue = true;
            } else {
                returnValue = false;
            }
        } else if (type.equals(VendingMachine.DIME)) {
            if (dimeStock > 0) {
                dimeStock--;
                returnValue = true;
            } else {
                returnValue = false;
            }
        } else if (type.equals(VendingMachine.NICKEL)) {
            if (nickelStock > 0) {
                nickelStock--;
                returnValue = true;
            } else {
                returnValue = false;
            }
        }

        return returnValue;
    }

    public boolean hasChange() {
        // If machine has at least 1 dime and 2 nickels,
        // it can make change for any amount of coins inserted for any product price.
        if (dimeStock > 0 && nickelStock > 1) {
            return true;
        } else {
            return false;
        }
    }

    public int getQuarterStock() {
        return quarterStock;
    }
    public int getDimeStock() {
        return dimeStock;
    }
    public int getNickelStock() {
        return nickelStock;
    }
}
