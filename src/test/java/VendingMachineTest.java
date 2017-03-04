import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VendingMachineTest {

    private VendingMachine subject;

    @Before
    public void setup() {
        subject = new VendingMachine();
        subject.start();
    }

    @Test
    public void whenNoCoinsAreInserted_theDisplayReadsINSERT_COIN() {
        assertEquals("INSERT COIN", subject.readDisplay());
    }

    @Test
    public void whenAnInvalidCoinIsInserted_theCoinIsSentToCoinReturn() {
        subject.insertCoin("CHUCK E. CHEESE ARCADE TOKEN");

        assertEquals(1, subject.getCoinReturnContents().size());
    }

    @Test
    public void whenAQuarterIsInserted_theDisplayReads25Cents() {
        subject.insertCoin("QUARTER");

        assertEquals("$0.25", subject.readDisplay());
    }

    @Test
    public void whenADimeIsInserted_theDisplayReads10Cents() {
        subject.insertCoin("DIME");

        assertEquals("$0.10", subject.readDisplay());
    }

    @Test
    public void whenANickelIsInserted_theDisplayReads5Cents() {
        subject.insertCoin("NICKEL");

        assertEquals("$0.05", subject.readDisplay());
    }

    @Test
    public void whenAPennyIsInserted_itIsSentToTheCoinReturn() {
        subject.insertCoin("PENNY");

        assertEquals(1, subject.getCoinReturnContents().size());
        assertEquals("PENNY", subject.getCoinReturnContents().get(0));
    }

    @Test
    public void whenAQuarterAndANickelAndADimeAreInserted_theDisplayReads40Cents() {
        subject.insertCoin("QUARTER");
        subject.insertCoin("NICKEL");
        subject.insertCoin("DIME");

        assertEquals("$0.40", subject.readDisplay());
    }

    @Test
    public void whenTheButtonForColaIsPressed_andThereIsNotEnoughMoneyInserted_theDisplayShowsTheCorrectPrice() {
        subject.pressButton("COLA");

        assertEquals("PRICE $1.00", subject.readDisplay());
    }

    @Test
    public void whenTheButtonForChipsIsPressed_andThereIsNotEnoughMoneyInserted_theDisplayShowsTheCorrectPrice() {
        subject.pressButton("CHIPS");

        assertEquals("PRICE $0.50", subject.readDisplay());
    }

    @Test
    public void whenTheButtonForCandyIsPressed_andThereIsNotEnoughMoneyInserted_theDisplayShowsTheCorrectPrice() {
        subject.pressButton("CANDY");

        assertEquals("PRICE $0.65", subject.readDisplay());
    }

    @Test
    public void whenTheDisplayIsCheckedASecondTime_afterDisplayingThePriceOfAnItem_withNoMoneyInserted_itReadsINSERT_COIN() {
        subject.pressButton("CANDY");

        assertEquals("PRICE $0.65", subject.readDisplay());
        assertEquals("INSERT COIN", subject.readDisplay());
    }

    @Test
    public void whenTheDisplayIsCheckedASecondTime_afterDisplayingThePriceOfAnItem_withMoneyInserted_itReadsTheAmountInserted() {
        subject.insertCoin("QUARTER");
        subject.pressButton("CANDY");

        assertEquals("PRICE $0.65", subject.readDisplay());
        assertEquals("$0.25", subject.readDisplay());
    }

    @Test
    public void whenTheButtonForAProductIsPressed_andThereIsEnoughMoneyInserted_theProductIsDispensed() {
        subject.insertCoin("QUARTER");
        subject.insertCoin("QUARTER");

        subject.pressButton("CHIPS");

        assertEquals(1, subject.getProductDispensationContents().size());
        assertEquals("CHIPS", subject.getProductDispensationContents().get(0));
    }

    @Test
    public void whenTheButtonForAProductIsPressed_andThereIsEnoughMoneyInserted_theDisplayReadsTheCorrectValuesOnSubsequentChecks() {
        subject.insertCoin("QUARTER");
        subject.insertCoin("QUARTER");

        subject.pressButton("CHIPS");

        assertEquals("THANK YOU", subject.readDisplay());
        assertEquals("INSERT COIN", subject.readDisplay());
    }

    @Test
    public void whenTheButtonForAProductIsPressed_andThereIsTooMuchMoneyInserted_correctChangeIsDispensed() {
        subject.insertCoin("QUARTER");
        subject.insertCoin("QUARTER");
        subject.insertCoin("QUARTER");
        subject.insertCoin("QUARTER");
        subject.insertCoin("NICKEL");

        subject.pressButton("CANDY");

        assertEquals(3, subject.getCoinReturnContents().size());
        assertTrue(subject.getCoinReturnContents().contains("QUARTER"));
        assertTrue(subject.getCoinReturnContents().contains("DIME"));
        assertTrue(subject.getCoinReturnContents().contains("NICKEL"));

        assertEquals(1, subject.getProductDispensationContents().size());
        assertEquals("CANDY", subject.getProductDispensationContents().get(0));
    }

    @Test
    public void whenTheReturnCoinButtonIsPressed_theAmountInsertedSoFarIsReturned() {
        subject.insertCoin("DIME");
        subject.insertCoin("DIME");
        subject.insertCoin("DIME");
        subject.insertCoin("DIME");

        subject.pressReturnCoinsButton();

        assertEquals(3, subject.getCoinReturnContents().size());
        assertTrue(subject.getCoinReturnContents().contains("QUARTER"));
        assertTrue(subject.getCoinReturnContents().contains("DIME"));
        assertTrue(subject.getCoinReturnContents().contains("NICKEL"));
    }

    @Test
    public void whenTheMachineIsStocked_theCorrectNumberOfColaIsPlacedInProductManagerStock() {
        subject.stockMachine(1, 0, 0);

        subject.insertCoin("QUARTER");
        subject.insertCoin("QUARTER");
        subject.insertCoin("QUARTER");
        subject.insertCoin("QUARTER");

        subject.pressButton("COLA");
        assertEquals("THANK YOU", subject.readDisplay());

        subject.pressButton("COLA");
        assertEquals("SOLD OUT", subject.readDisplay());
    }

    @Test
    public void whenTheMachineIsStocked_theCorrectNumberOfCandyIsPlacedInProductManagerStock() {
        subject.stockMachine(0, 1, 0);

        subject.insertCoin("QUARTER");
        subject.insertCoin("QUARTER");
        subject.insertCoin("QUARTER");

        subject.pressButton("CANDY");
        assertEquals("THANK YOU", subject.readDisplay());

        subject.pressButton("CANDY");
        assertEquals("SOLD OUT", subject.readDisplay());
    }

    @Test
    public void whenTheMachineIsStocked_theCorrectNumberOfChipsIsPlacedInProductManagerStock() {
        subject.stockMachine(0, 0, 1);

        subject.insertCoin("QUARTER");
        subject.insertCoin("QUARTER");

        subject.pressButton("CHIPS");
        assertEquals("THANK YOU", subject.readDisplay());

        subject.pressButton("CHIPS");
        assertEquals("SOLD OUT", subject.readDisplay());
    }

    @Test
    public void whenAProductButtonIsPressed_andTheProductIsSoldOut_theDisplayReadsSOLD_OUT() {
        subject.stockMachine(0, 0, 0);

        subject.pressButton("COLA");

        assertEquals("SOLD OUT", subject.readDisplay());
        assertEquals("INSERT COIN", subject.readDisplay());
    }
}
