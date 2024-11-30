import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class OrderTest {

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
    }

    @Test
    public void testAddItem() {
        order.addItem("Burger", 9.99);
        List<String> items = order.getItems();
        assertEquals(1, items.size(), "Order should contain 1 item");
        assertEquals("Burger", items.getFirst(), "The item should be 'Burger'");
        assertEquals(9.99, order.getTotalPrice(), 0.001, "Total price should be 9.99");
    }

    @Test
    public void testGetItems() {
        order.addItem("Burger", 9.99);
        order.addItem("Pizza", 11.99);
        List<String> items = order.getItems();
        assertEquals(2, items.size(), "Order should contain 2 items");
        assertEquals("Burger", items.get(0), "First item should be 'Burger'");
        assertEquals("Pizza", items.get(1), "Second item should be 'Pizza'");
    }

    @Test
    public void testGetTotalPrice() {
        order.addItem("Burger", 9.99);
        order.addItem("Pizza", 11.99);
        assertEquals(21.98, order.getTotalPrice(), 0.001, "Total price should be 21.98");
    }

    @Test
    public void testClear() {
        order.addItem("Burger", 9.99);
        order.addItem("Pizza", 11.99);
        order.clear();
        List<String> items = order.getItems();
        assertEquals(0, items.size(), "Order should contain 0 items after clearing");
        assertEquals(0.0, order.getTotalPrice(), 0.001, "Total price should be 0.0 after clearing");
    }
}
