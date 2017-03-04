import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    @Test
    public void whenNoCoinsAreInserted_theDisplayReadsINSERT_COIN() {
        VendingMachine subject = new VendingMachine();

        subject.start();

        assertEquals("INSERT COIN", subject.readDisplay());
    }

    @Test
    public void whenAnInvalidCoinIsInserted_theCoinIsSentToCoinReturn() {
        VendingMachine subject = new VendingMachine();
        subject.start();

        subject.insertCoin();

        assertEquals(1, subject.getCoinReturnContents().size());
    }
}
