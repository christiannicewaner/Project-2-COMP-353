import javax.swing.*;
import java.awt.*;

public class RestaurantKiosk {
    public static JPanel mainPanel;
    public static CardLayout cardLayout = null;
    public static Order currentOrder;
    public static String currentItemName;
    public static double currentItemPrice;

    // Create the account label at the class level
    public static final JLabel accountLabel = new JLabel("");

    public RestaurantKiosk() {
        // Setup Main Panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize Order
        currentOrder = new Order();

        // Initialize Panels
        JPanel homePanel = HomePanel.createHomePanel();
        JPanel menuPanel = MenuPanel.createMenuPanel();
        JPanel customizeBurgerPanel = BurgerPanel.createBurgerPanel();
        JPanel customizePizzaPanel = PizzaPanel.createPizzaPanel();
        JPanel customizePastaPanel = PastaPanel.createPastaPanel();
        JPanel customizeSaladPanel = SaladPanel.createSaladPanel();
        JPanel orderSummaryPanel = OrderSummaryPanel.createOrderSummaryPanel();
        JPanel emailLoginPanel = EmailLoginPanel.createEmailLoginPanel();
        JPanel phoneLoginPanel = PhoneLoginPanel.createPhoneLoginPanel();

        // Add Panels to Main Panel
        mainPanel.add(homePanel, "Home");
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(customizeBurgerPanel, "Customize Burger");
        mainPanel.add(customizePizzaPanel, "Customize Pizza");
        mainPanel.add(customizePastaPanel, "Customize Pasta");
        mainPanel.add(customizeSaladPanel, "Customize Salad");
        mainPanel.add(orderSummaryPanel, "Order Summary");
        mainPanel.add(PaymentPanel.createPaymentPanel(), "Payment");
        mainPanel.add(emailLoginPanel, "Email Login");
        mainPanel.add(phoneLoginPanel, "Phone Login");


        // Show Home Panel Initially
        cardLayout.show(mainPanel, "Home");
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Restaurant Kiosk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new RestaurantKiosk();
        frame.setContentPane(mainPanel);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
