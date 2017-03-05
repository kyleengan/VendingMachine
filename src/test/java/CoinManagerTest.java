import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoinManagerTest {

    private CoinManager subject;

    @Before
    public void setup() {
        subject = new CoinManager();
    }

    @Test
    public void whenAQuarterIsAdded_quarterStockIncreasesByOne() {
        assertEquals(3, subject.getQuarterStock());

        subject.addCoin("QUARTER");

        assertEquals(4, subject.getQuarterStock());
    }

    @Test
    public void whenADimeIsAdded_dimeStockIncreasesByOne() {
        assertEquals(3, subject.getDimeStock());

        subject.addCoin("DIME");

        assertEquals(4, subject.getDimeStock());
    }

    @Test
    public void whenANickelIsAdded_nickelStockIncreasesByOne() {
        assertEquals(3, subject.getNickelStock());

        subject.addCoin("NICKEL");

        assertEquals(4, subject.getNickelStock());
    }

    @Test
    public void whenAQuarterIsReturned_quarterStockDecreasesByOne() {
        assertEquals(3, subject.getQuarterStock());

        assertTrue(subject.returnCoin("QUARTER"));

        assertEquals(2, subject.getQuarterStock());
    }

    @Test
    public void whenADimeIsReturned_dimeStockDecreasesByOne() {
        assertEquals(3, subject.getDimeStock());

        assertTrue(subject.returnCoin("DIME"));

        assertEquals(2, subject.getDimeStock());
    }

    @Test
    public void whenANickelIsReturned_nickelStockDecreasesByOne() {
        assertEquals(3, subject.getNickelStock());

        assertTrue(subject.returnCoin("NICKEL"));

        assertEquals(2, subject.getNickelStock());
    }

    @Test
    public void whenAQuarterIsReturned_andThereAreNoQuartersToReturn_quarterStockStays0_andReturnCoinReturnsFalse() {
        subject = new CoinManager(0,0,0);

        assertFalse(subject.returnCoin("QUARTER"));

        assertEquals(0, subject.getQuarterStock());
    }

    @Test
    public void whenADimeIsReturned_andThereAreNoDimesToReturn_dimeStockStays0_andReturnCoinReturnsFalse() {
        subject = new CoinManager(0,0,0);

        assertFalse(subject.returnCoin("DIME"));

        assertEquals(0, subject.getDimeStock());
    }

    @Test
    public void whenANickelIsReturned_andThereAreNoNickelsToReturn_nickelStockStays0_andReturnCoinReturnsFalse() {
        subject = new CoinManager(0,0,0);

        assertFalse(subject.returnCoin("NICKEL"));

        assertEquals(0, subject.getNickelStock());
    }

    @Test
    public void whenMachineHasAtLeastOneDimeAndTwoNickels_hasChangeReturnsTrue() {
        subject = new CoinManager(0, 1, 2);

        assertTrue(subject.hasChange());
    }

    @Test
    public void whenMachineDoesNotContainADimeAndTwoNickels_hasChangeReturnsFalse() {
        subject = new CoinManager(0, 0, 2);
        assertFalse(subject.hasChange());

        subject = new CoinManager(0, 1, 0);
        assertFalse(subject.hasChange());
    }
}
