import java.util.ArrayList;
import java.util.List;

public class Order {
    // Order class to keep track of items and total price
    public static List<String> items;
    public static double totalPrice;

    public Order() {
        items = new ArrayList<>();
        totalPrice = 0.0;
    }

    public void addItem(String name, double price) {
        items.add(name);
        totalPrice += price;
    }

    public List<String> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void clear() {
        items.clear();
        totalPrice = 0.0;
    }
}
