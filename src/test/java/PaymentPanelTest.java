import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

public class PaymentPanelTest {

    @BeforeAll
    public static void setupClass() {
        // Initialize the static components for the tests
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        JLabel accountLabel = new JLabel();
        Order currentOrder = new Order();
        RestaurantKiosk.mainPanel = mainPanel;
        RestaurantKiosk.cardLayout = cardLayout;
        RestaurantKiosk.accountLabel = accountLabel;
        RestaurantKiosk.currentOrder = currentOrder;

        // Add necessary panels for navigation
        mainPanel.add(OrderSummaryPanel.createOrderSummaryPanel(), "Order Summary");
        mainPanel.add(ProgressPanel.createProgressScreenPanel(), "Progress Screen");
    }

    @Test
    public void testCreatePaymentPanel() {
        JPanel panel = PaymentPanel.createPaymentPanel();

        // Check if the panel is not null
        assertNotNull(panel, "Panel should not be null");

        // Check if the panel uses BorderLayout
        assertInstanceOf(BorderLayout.class, panel.getLayout(), "Panel should use BorderLayout");

        // Check if the title label is added to the NORTH position
        JPanel northPanel = (JPanel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        JLabel titleLabel = (JLabel) ((BorderLayout) northPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        assertEquals("Enter Card Information", titleLabel.getText(), "Title label text should be 'Enter Card Information'");
        assertEquals(SwingConstants.CENTER, titleLabel.getHorizontalAlignment(), "Title label should be centered");

        // Check if the input panel is added to the CENTER position
        JPanel inputPanel = (JPanel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(inputPanel, "Input panel should be added to the CENTER position");
        assertInstanceOf(GridBagLayout.class, inputPanel.getLayout(), "Input panel should use GridBagLayout");

        // Verify components in the input panel
        assertEquals(4, inputPanel.getComponentCount(), "Input panel should contain 4 components (label, field, and 2 buttons)");

        JLabel cardNumberLabel = (JLabel) inputPanel.getComponent(0);
        assertEquals("Card Number:", cardNumberLabel.getText(), "First component should be 'Card Number:' label");

        JTextField cardNumberField = (JTextField) inputPanel.getComponent(1);
        assertEquals(20, cardNumberField.getColumns(), "Card number field should have 20 columns");

        JButton payButton = (JButton) inputPanel.getComponent(2);
        assertEquals("Pay", payButton.getText(), "Third component should be 'Pay' button");

        JButton backButton = (JButton) inputPanel.getComponent(3);
        assertEquals("Back to Order Summary", backButton.getText(), "Fourth component should be 'Back to Order Summary' button");
    }

    @Test
    public void testIsValidCardNumber() {
        assertTrue(PaymentPanel.isValidCardNumber("1234-5678-9876-5432"), "Valid card number with dashes should pass");
        assertTrue(PaymentPanel.isValidCardNumber("1234567898765432"), "Valid card number without dashes should pass");
        assertFalse(PaymentPanel.isValidCardNumber("1234-5678-9876-543"), "Invalid card number with dashes should fail");
        assertFalse(PaymentPanel.isValidCardNumber("123456789876543"), "Invalid card number without dashes should fail");
    }

}
