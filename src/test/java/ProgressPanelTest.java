import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

public class ProgressPanelTest {

    @BeforeAll
    public static void setupClass() {
        // Initialize the static components for the tests
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        RestaurantKiosk.mainPanel = mainPanel;
        RestaurantKiosk.cardLayout = cardLayout;

        // Add necessary panels for navigation
        mainPanel.add(ProgressPanel.createProgressScreenPanel(), "Progress Screen");
        mainPanel.add(new JPanel(), "Home"); // Placeholder for the Home panel
    }

    @Test
    public void testCreateProgressScreenPanel() {
        JPanel panel = ProgressPanel.createProgressScreenPanel();

        // Check if the panel is not null
        assertNotNull(panel, "Panel should not be null");

        // Check if the panel uses BorderLayout
        assertInstanceOf(BorderLayout.class, panel.getLayout(), "Panel should use BorderLayout");

        // Check if the progress label is added to the NORTH position
        JLabel progressLabel = (JLabel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        assertEquals("Processing your order, please wait...", progressLabel.getText(), "Progress label text should be 'Processing your order, please wait...'");
        assertEquals(SwingConstants.CENTER, progressLabel.getHorizontalAlignment(), "Progress label should be centered");

        // Check if the progress bar is added to the CENTER position
        JProgressBar progressBar = (JProgressBar) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(progressBar, "Progress bar should be added to the CENTER position");
        assertEquals(0, progressBar.getMinimum(), "Progress bar minimum should be 0");
        assertEquals(3, progressBar.getMaximum(), "Progress bar maximum should be 3");
        assertTrue(progressBar.isStringPainted(), "Progress bar string should be painted");
        assertEquals("Sending Order", progressBar.getString(), "Progress bar initial string should be 'Sending Order'");

        // Check if the countdown label is added to the SOUTH position
        JLabel countdownLabel = (JLabel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.SOUTH);
        assertNotNull(countdownLabel, "Countdown label should be added to the SOUTH position");
        assertEquals("", countdownLabel.getText(), "Countdown label text should be empty initially");
        assertEquals(SwingConstants.CENTER, countdownLabel.getHorizontalAlignment(), "Countdown label should be centered");
    }
}
