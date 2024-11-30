import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

public class HomePanelTest {

    @BeforeAll
    public static void setup() {
        // Initialize the components before running tests
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        JLabel accountLabel = new JLabel();
        RestaurantKiosk.currentOrder = new Order();
        RestaurantKiosk.cardLayout = cardLayout;
        RestaurantKiosk.mainPanel = mainPanel;
        RestaurantKiosk.accountLabel = accountLabel;
    }

    @Test
    public void testCreateHomePanel() {
        JPanel panel = HomePanel.createHomePanel();

        // Check if the panel is not null
        assertNotNull(panel, "Panel should not be null");

        // Check if the panel uses BorderLayout
        assertInstanceOf(BorderLayout.class, panel.getLayout(), "Panel should use BorderLayout");

        // Check if the title label is added to the NORTH position
        JLabel titleLabel = (JLabel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        assertEquals("Welcome to McBurger!", titleLabel.getText(), "Title label text should be 'Welcome to McBurger!'");
        assertEquals(SwingConstants.CENTER, titleLabel.getHorizontalAlignment(), "Title label should be centered");

        // Check if the button panel is added to the CENTER position
        JPanel buttonPanel = (JPanel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(buttonPanel, "Button panel should be added to the CENTER position");
        assertInstanceOf(BoxLayout.class, buttonPanel.getLayout(), "Button panel should use BoxLayout");

        // Verify buttons in the button panel
        assertInstanceOf(JButton.class, buttonPanel.getComponent(0), "First component should be a JButton");
        JButton guestButton = (JButton) buttonPanel.getComponent(0);
        assertEquals("Continue as Guest", guestButton.getText(), "First button should be 'Continue as Guest'");

        assertInstanceOf(JButton.class, buttonPanel.getComponent(1), "Second component should be a JButton");
        JButton emailLoginButton = (JButton) buttonPanel.getComponent(1);
        assertEquals("Login with Email", emailLoginButton.getText(), "Second button should be 'Login with Email'");

        assertInstanceOf(JButton.class, buttonPanel.getComponent(2), "Third component should be a JButton");
        JButton phoneLoginButton = (JButton) buttonPanel.getComponent(2);
        assertEquals("Login with Phone", phoneLoginButton.getText(), "Third button should be 'Login with Phone'");

        // Check if the image panel is added to the PAGE_START position
        JPanel imagePanel = (JPanel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.PAGE_START);
        assertNotNull(imagePanel, "Image panel should be added to the PAGE_START position");
        assertInstanceOf(JLabel.class, imagePanel.getComponent(0), "First component in image panel should be a JLabel");

        // Check if the bottom panel is added to the SOUTH position
        JPanel bottomPanel = (JPanel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.SOUTH);
        assertNotNull(bottomPanel, "Bottom panel should be added to the SOUTH position");

        // Verify the help button in the bottom panel
        JButton helpButton = (JButton) ((BorderLayout) bottomPanel.getLayout()).getLayoutComponent(BorderLayout.EAST);
        assertEquals("Help", helpButton.getText(), "Help button should be 'Help'");
    }
}
