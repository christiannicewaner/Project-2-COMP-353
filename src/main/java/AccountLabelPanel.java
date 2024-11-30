import javax.swing.*;
import java.awt.*;

public class AccountLabelPanel {
    // Method to create the label panel
    protected static JPanel createAccountLabelPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(RestaurantKiosk.accountLabel, BorderLayout.EAST);
        return panel;
    }
}
