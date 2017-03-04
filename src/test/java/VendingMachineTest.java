import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

        assertEquals("PENNY", subject.getCoinReturnContents().get(0));
    }
}
