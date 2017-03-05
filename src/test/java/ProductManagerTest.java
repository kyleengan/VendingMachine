import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductManagerTest {

    private ProductManager subject;

    @Before
    public void setup() {
        subject = new ProductManager();
    }

    @Test
    public void getPrice_givenCOLA_shouldReturnPriceOfCola() {
        double price = subject.getPrice("COLA");

        assertEquals(1.0, price, 0.00001);
    }

    @Test
    public void getPrice_givenCANDY_shouldReturnPriceOfCandy() {
        double price = subject.getPrice("CANDY");

        assertEquals(0.65, price, 0.00001);
    }

    @Test
    public void getPrice_givenCHIPS_shouldReturnPriceOfChips() {
        double price = subject.getPrice("CHIPS");

        assertEquals(0.5, price, 0.00001);
    }

    @Test
    public void dispense_givenCOLA_whenThereIsAtLeastOneInStock_shouldReturnTrue() {
        subject = new ProductManager(1, 1, 1);

        assertTrue(subject.dispense("COLA"));
    }

    @Test
    public void dispense_givenCOLA_whenThereIsAtLeastOneInStock_shouldReduceStockByOne() {
        subject = new ProductManager(1, 1, 1);

        subject.dispense("COLA");

        assertEquals(0, subject.getColaStock());
    }

    @Test
    public void dispense_givenCOLA_whenThereIsNoneInStock_shouldReturnFalse() {
        subject = new ProductManager(0, 0, 0);

        assertFalse(subject.dispense("COLA"));
    }

    @Test
    public void dispense_givenCOLA_whenThereIsNoneInStock_shouldKeepStockAtZero() {
        subject = new ProductManager(0, 0, 0);

        subject.dispense("CANDY");

        assertEquals(0, subject.getCandyStock());
    }

    @Test
    public void dispense_givenCANDY_whenThereIsAtLeastOneInStock_shouldReturnTrue() {
        subject = new ProductManager(1, 1, 1);

        assertTrue(subject.dispense("CANDY"));
    }

    @Test
    public void dispense_givenCANDY_whenThereIsAtLeastOneInStock_shouldReduceStockByOne() {
        subject = new ProductManager(1, 1, 1);

        subject.dispense("CANDY");

        assertEquals(0, subject.getCandyStock());
    }

    @Test
    public void dispense_givenCANDY_whenThereIsNoneInStock_shouldReturnFalse() {
        subject = new ProductManager(0, 0, 0);

        assertFalse(subject.dispense("CANDY"));
    }

    @Test
    public void dispense_givenCANDY_whenThereIsNoneInStock_shouldKeepStockAtZero() {
        subject = new ProductManager(0, 0, 0);

        subject.dispense("COLA");

        assertEquals(0, subject.getColaStock());
    }

    @Test
    public void dispense_givenCHIPS_whenThereIsAtLeastOneInStock_shouldReturnTrue() {
        subject = new ProductManager(1, 1, 1);

        assertTrue(subject.dispense("CHIPS"));
    }

    @Test
    public void dispense_givenCHIPS_whenThereIsAtLeastOneInStock_shouldReduceStockByOne() {
        subject = new ProductManager(1, 1, 1);

        subject.dispense("CHIPS");

        assertEquals(0, subject.getChipsStock());
    }

    @Test
    public void dispense_givenCHIPS_whenThereIsNoneInStock_shouldReturnFalse() {
        subject = new ProductManager(0, 0, 0);

        assertFalse(subject.dispense("CHIPS"));
    }

    @Test
    public void dispense_givenCHIPS_whenThereIsNoneInStock_shouldKeepStockAtZero() {
        subject = new ProductManager(0, 0, 0);

        subject.dispense("CHIPS");

        assertEquals(0, subject.getChipsStock());
    }

    @Test
    public void checkProduct_shouldReturnTrueIfStockGreaterThanZero() {
        subject = new ProductManager(1, 1, 1);

        assertTrue(subject.checkProduct("COLA"));
        assertTrue(subject.checkProduct("CANDY"));
        assertTrue(subject.checkProduct("CHIPS"));
    }

    @Test
    public void checkProduct_shouldReturnFalseIfStockEqualToZero() {
        subject = new ProductManager(0, 0, 0);

        assertFalse(subject.checkProduct("COLA"));
        assertFalse(subject.checkProduct("CANDY"));
        assertFalse(subject.checkProduct("CHIPS"));
    }
}
