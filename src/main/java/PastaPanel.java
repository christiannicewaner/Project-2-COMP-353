import javax.swing.*;
import java.awt.*;

public class PastaPanel {
    // Customization Panel for Pasta
    public static JPanel createPastaPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        panel.add(AccountLabelPanel.createAccountLabelPanel(), BorderLayout.NORTH);
        JLabel titleLabel = new JLabel("Customize your Pasta", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font size and style
        panel.add(titleLabel, BorderLayout.NORTH);

        // Add checkboxes for customization
        JCheckBox sauceCheckBox = new JCheckBox("Extra Sauce");
        JCheckBox cheeseCheckBox = new JCheckBox("Extra Cheese");
        JCheckBox garlicCheckBox = new JCheckBox("Garlic");
        JCheckBox mushroomsCheckBox = new JCheckBox("Mushrooms");
        checkBoxPanel.add(sauceCheckBox);
        checkBoxPanel.add(cheeseCheckBox);
        checkBoxPanel.add(garlicCheckBox);
        checkBoxPanel.add(mushroomsCheckBox);

        // Nutritional Information Panel
        JPanel nutritionPanel = createPastaInfoPanel();

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Add to Order button
        JButton addButton = new JButton("Add to Order");
        addButton.addActionListener(e -> {
            StringBuilder customizations = new StringBuilder(RestaurantKiosk.currentItemName);
            if (sauceCheckBox.isSelected()) customizations.append(" + Extra Sauce");
            if (cheeseCheckBox.isSelected()) customizations.append(" + Extra Cheese");
            if (garlicCheckBox.isSelected()) customizations.append(" + Garlic");
            if (mushroomsCheckBox.isSelected()) customizations.append(" + Mushrooms");

            RestaurantKiosk.currentOrder.addItem(customizations.toString(), RestaurantKiosk.currentItemPrice);
            sauceCheckBox.setSelected(false);
            cheeseCheckBox.setSelected(false);
            garlicCheckBox.setSelected(false);
            mushroomsCheckBox.setSelected(false);
            RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Menu");
            RestaurantKiosk.mainPanel.revalidate();
            RestaurantKiosk.mainPanel.repaint();
        });
        buttonPanel.add(addButton);

        // Add some space between buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Back to Menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Menu");
            RestaurantKiosk.mainPanel.revalidate();
            RestaurantKiosk.mainPanel.repaint();
        });
        buttonPanel.add(backButton);

        panel.add(checkBoxPanel, BorderLayout.CENTER);
        panel.add(nutritionPanel, BorderLayout.EAST); // Add the nutrition panel to the right
        panel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom

        return panel;
    }
    // Create Pasta Nutritional Information Panel
    public static JPanel createPastaInfoPanel() {
        JPanel nutritionPanel = new JPanel();
        nutritionPanel.setLayout(new BoxLayout(nutritionPanel, BoxLayout.Y_AXIS));
        nutritionPanel.setBorder(BorderFactory.createTitledBorder("Nutrition Info"));

        JLabel caloriesLabel = new JLabel("Calories: 434");
        JLabel fatLabel = new JLabel("Fat: 12g");
        JLabel carbsLabel = new JLabel("Carbohydrates: 47g");
        JLabel proteinLabel = new JLabel("Protein: 31g");

        nutritionPanel.add(caloriesLabel);
        nutritionPanel.add(fatLabel);
        nutritionPanel.add(carbsLabel);
        nutritionPanel.add(proteinLabel);

        return nutritionPanel;
    }
}
