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
        subject.insertCoin();

        assertEquals(1, subject.getCoinReturnContents().size());
    }
}
