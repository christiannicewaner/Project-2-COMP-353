import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

public class PhoneLoginPanelTest {

    @BeforeAll
    public static void setupClass() {
        // Initialize the static components for the tests
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        JLabel accountLabel = new JLabel();
        RestaurantKiosk.mainPanel = mainPanel;
        RestaurantKiosk.cardLayout = cardLayout;
        RestaurantKiosk.accountLabel = accountLabel;

        // Add necessary panels for navigation
        mainPanel.add(PhoneLoginPanel.createPhoneLoginPanel(), "Phone Login");
        mainPanel.add(new JPanel(), "Menu"); // Placeholder for the Menu panel
        mainPanel.add(new JPanel(), "Home"); // Placeholder for the Home panel
    }

    @Test
    public void testCreatePhoneLoginPanel() {
        JPanel panel = PhoneLoginPanel.createPhoneLoginPanel();

        // Check if the panel is not null
        assertNotNull(panel, "Panel should not be null");

        // Check if the panel uses BorderLayout
        assertInstanceOf(BorderLayout.class, panel.getLayout(), "Panel should use BorderLayout");

        // Check if the title label is added to the NORTH position
        JLabel titleLabel = (JLabel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        assertEquals("Enter your Phone Number (XXX-XXX-XXXX)", titleLabel.getText(), "Title label text should be 'Enter your Phone Number (XXX-XXX-XXXX)'");
        assertEquals(SwingConstants.CENTER, titleLabel.getHorizontalAlignment(), "Title label should be centered");

        // Check if the phone field is added to the CENTER position
        JTextField phoneField = (JTextField) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(phoneField, "Phone field should be added to the CENTER position");
        assertEquals(JTextField.CENTER, phoneField.getHorizontalAlignment(), "Phone field should be centered");
        assertEquals(48, phoneField.getFont().getSize(), "Phone field font size should be 48");

        // Check if the button panel is added to the SOUTH position
        JPanel buttonPanel = (JPanel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.SOUTH);
        assertNotNull(buttonPanel, "Button panel should be added to the SOUTH position");
        assertInstanceOf(GridBagLayout.class, buttonPanel.getLayout(), "Button panel should use GridBagLayout");

        // Verify buttons in the button panel
        Component[] components = buttonPanel.getComponents();
        assertEquals(2, components.length, "Button panel should contain 2 components (2 buttons)");

        JButton loginButton = (JButton) components[0];
        assertEquals("Login", loginButton.getText(), "First button should be 'Login'");

        JButton backButton = (JButton) components[1];
        assertEquals("Back to Home", backButton.getText(), "Second button should be 'Back to Home'");
    }
}
