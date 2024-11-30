import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

public class OrderSummaryPanelTest {

    private static Order currentOrder;

    @BeforeAll
    public static void setupClass() {
        // Initialize the static components for the tests
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        JLabel accountLabel = new JLabel();
        currentOrder = new Order();
        RestaurantKiosk.mainPanel = mainPanel;
        RestaurantKiosk.cardLayout = cardLayout;
        RestaurantKiosk.accountLabel = accountLabel;
        RestaurantKiosk.currentOrder = currentOrder;

        // Add an order summary panel to the main panel for testing purposes
        mainPanel.add(OrderSummaryPanel.createOrderSummaryPanel(), "Order Summary");
    }

    @BeforeEach
    public void setup() {
        // Clear the order before each test
        currentOrder.clear();
    }

    @Test
    public void testCreateOrderSummaryPanel() {
        JPanel panel = OrderSummaryPanel.createOrderSummaryPanel();

        // Check if the panel is not null
        assertNotNull(panel, "Panel should not be null");

        // Check if the panel uses BorderLayout
        assertInstanceOf(BorderLayout.class, panel.getLayout(), "Panel should use BorderLayout");

        // Check if the summary text area is added to the CENTER position
        JScrollPane scrollPane = (JScrollPane) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(scrollPane, "Scroll pane should be added to the CENTER position");
        JTextArea summaryTextArea = (JTextArea) scrollPane.getViewport().getView();
        assertFalse(summaryTextArea.isEditable(), "Summary text area should be non-editable");

        // Check if the account label panel is added to the NORTH position
        JPanel accountLabelPanel = (JPanel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        assertNotNull(accountLabelPanel, "Account label panel should be added to the NORTH position");

        // Check if the buttons panel is added to the SOUTH position
        JPanel buttonsPanel = (JPanel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.SOUTH);
        assertNotNull(buttonsPanel, "Buttons panel should be added to the SOUTH position");
        assertInstanceOf(BoxLayout.class, buttonsPanel.getLayout(), "Buttons panel should use BoxLayout");

        // Verify the buttons in the buttons panel
        JButton cancelButton = (JButton) buttonsPanel.getComponent(0);
        assertEquals("Cancel Order", cancelButton.getText(), "First button should be 'Cancel Order'");

        JButton backToMenuButton = (JButton) buttonsPanel.getComponent(2);
        assertEquals("Back to Menu", backToMenuButton.getText(), "Second button should be 'Back to Menu'");

        JButton proceedButton = (JButton) buttonsPanel.getComponent(4);
        assertEquals("Proceed to Payment", proceedButton.getText(), "Third button should be 'Proceed to Payment'");
    }
}
