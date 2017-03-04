import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    @Test
    public void whenNoCoinsAreInserted_theDisplayReadsINSERT_COIN() {
        VendingMachine subject = new VendingMachine();

        subject.start();

        assertEquals("INSERT COIN", subject.readDisplay());
    }
}
