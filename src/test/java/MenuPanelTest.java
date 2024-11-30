import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

public class MenuPanelTest {

    @BeforeAll
    public static void setup() {
        // Initialize the components before running tests
        JLabel accountLabel = new JLabel("test@example.com");
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        RestaurantKiosk.accountLabel = accountLabel;
        RestaurantKiosk.cardLayout = cardLayout;
        RestaurantKiosk.mainPanel = mainPanel;
        RestaurantKiosk.currentOrder = new Order();
    }

    @Test
    public void testCreateMenuPanel() {
        JPanel panel = MenuPanel.createMenuPanel();

        // Check if the panel is not null
        assertNotNull(panel, "Panel should not be null");

        // Check if the panel uses BorderLayout
        assertInstanceOf(BorderLayout.class, panel.getLayout(), "Panel should use BorderLayout");

        // Check if the top panel is added to the NORTH position
        JPanel topPanel = (JPanel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        assertNotNull(topPanel, "Top panel should be added to the NORTH position");

        // Check if the account label panel is added to the EAST position in the top panel
        JPanel accountLabelPanel = (JPanel) ((BorderLayout) topPanel.getLayout()).getLayoutComponent(BorderLayout.EAST);
        assertNotNull(accountLabelPanel, "Account label panel should be added to the EAST position in the top panel");

        // Check if the grid panel is added to the CENTER position
        JPanel gridPanel = (JPanel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(gridPanel, "Grid panel should be added to the CENTER position");
        assertInstanceOf(GridBagLayout.class, gridPanel.getLayout(), "Grid panel should use GridBagLayout");

        // Verify menu item buttons in the grid panel
        Component[] components = gridPanel.getComponents();
        assertEquals(7, components.length, "Grid panel should contain 7 components (4 items + 3 buttons)");

        JButton burgerButton = (JButton) components[0];
        assertTrue(burgerButton.getText().contains("Burger"), "First button should be 'Burger'");

        JButton pizzaButton = (JButton) components[1];
        assertTrue(pizzaButton.getText().contains("Pizza"), "Second button should be 'Pizza'");

        JButton pastaButton = (JButton) components[2];
        assertTrue(pastaButton.getText().contains("Pasta"), "Third button should be 'Pasta'");

        JButton saladButton = (JButton) components[3];
        assertTrue(saladButton.getText().contains("Salad"), "Fourth button should be 'Salad'");

        JButton orderHistoryButton = (JButton) components[4];
        assertEquals("Order History", orderHistoryButton.getText(), "Fifth button should be 'Order History'");

        JButton orderSummaryButton = (JButton) components[5];
        assertEquals("Order Summary", orderSummaryButton.getText(), "Sixth button should be 'Order Summary'");

        JButton backToHomeButton = (JButton) components[6];
        assertEquals("Back to Home", backToHomeButton.getText(), "Seventh button should be 'Back to Home'");
    }
}
