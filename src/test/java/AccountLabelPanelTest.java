import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

public class AccountLabelPanelTest {

    private static JLabel accountLabel;

    @BeforeAll
    public static void setup() {
        // Initialize the accountLabel before running tests
        accountLabel = new JLabel("Account: test@example.com");
        RestaurantKiosk.accountLabel = accountLabel;
    }

    @Test
    public void testCreateAccountLabelPanel() {
        JPanel panel = AccountLabelPanel.createAccountLabelPanel();

        // Check if the panel is not null
        assertNotNull(panel, "Panel should not be null");

        // Check if the panel uses BorderLayout
        assertInstanceOf(BorderLayout.class, panel.getLayout(), "Panel should use BorderLayout");

        // Check if the accountLabel is correctly added to the panel
        assertEquals(accountLabel, ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.EAST),
                "accountLabel should be added to the EAST position of the BorderLayout");
    }
}
