import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

public class EmailLoginPanelTest {

    @BeforeAll
    public static void setup() {
        // Initialize the components before running tests
        JLabel accountLabel = new JLabel();
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        RestaurantKiosk.accountLabel = accountLabel;
        RestaurantKiosk.cardLayout = cardLayout;
        RestaurantKiosk.mainPanel = mainPanel;
    }

    @Test
    public void testCreateEmailLoginPanel() {
        JPanel panel = EmailLoginPanel.createEmailLoginPanel();

        // Check if the panel is not null
        assertNotNull(panel, "Panel should not be null");

        // Check if the panel uses BorderLayout
        assertInstanceOf(BorderLayout.class, panel.getLayout(), "Panel should use BorderLayout");

        // Check if the title label is added to the NORTH position
        JLabel titleLabel = (JLabel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        assertEquals("Enter your Email Address", titleLabel.getText(), "Title label text should be 'Enter your Email Address'");
        assertEquals(SwingConstants.CENTER, titleLabel.getHorizontalAlignment(), "Title label should be centered");

        // Check if the email field is added to the CENTER position
        JTextField emailField = (JTextField) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(emailField, "Email field should be added to the CENTER position");
        assertEquals(JTextField.CENTER, emailField.getHorizontalAlignment(), "Email field should be centered");
        assertEquals(48, emailField.getFont().getSize(), "Email field font size should be 48");

        // Check if the button panel is added to the SOUTH position
        JPanel buttonPanel = (JPanel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.SOUTH);
        assertNotNull(buttonPanel, "Button panel should be added to the SOUTH position");
        assertInstanceOf(GridBagLayout.class, buttonPanel.getLayout(), "Button panel should use GridBagLayout");

        // Verify buttons in the button panel
        boolean foundLoginButton = false;
        boolean foundBackButton = false;
        for (Component comp : buttonPanel.getComponents()) {
            if (comp instanceof JButton button) {
                if ("Login".equals(button.getText())) {
                    foundLoginButton = true;
                } else if ("Back to Home".equals(button.getText())) {
                    foundBackButton = true;
                }
            }
        }

        assertTrue(foundLoginButton, "Button panel should contain a 'Login' button");
        assertTrue(foundBackButton, "Button panel should contain a 'Back to Home' button");
    }
}
