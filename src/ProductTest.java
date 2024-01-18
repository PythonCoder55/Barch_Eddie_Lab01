import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void testToCSVDataRecord() {
        Product product = new Product("Laptop", "High-performance laptop", "P123", 999.99);
        assertEquals("P123, Laptop, High-performance laptop, 999.99", product.toCSVDataRecord());
    }
}
