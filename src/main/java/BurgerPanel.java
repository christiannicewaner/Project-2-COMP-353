import javax.swing.*;
import java.awt.*;

public class BurgerPanel {
    // Customization Panel for Burger
    public static JPanel createBurgerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        panel.add(AccountLabelPanel.createAccountLabelPanel(), BorderLayout.NORTH);
        JLabel titleLabel = new JLabel("Customize your Burger", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font size and style
        panel.add(titleLabel, BorderLayout.NORTH);

        // Add checkboxes for customization
        JCheckBox lettuceCheckBox = new JCheckBox("Lettuce");
        JCheckBox ketchupCheckBox = new JCheckBox("Ketchup");
        JCheckBox cheeseCheckBox = new JCheckBox("Cheese");
        JCheckBox onionsCheckBox = new JCheckBox("Onions");
        checkBoxPanel.add(lettuceCheckBox);
        checkBoxPanel.add(ketchupCheckBox);
        checkBoxPanel.add(cheeseCheckBox);
        checkBoxPanel.add(onionsCheckBox);

        // Nutritional Information Panel
        JPanel nutritionPanel = createBurgerInfoPanel();

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Add to Order button
        JButton addButton = new JButton("Add to Order");
        addButton.addActionListener(e -> {
            StringBuilder customizations = new StringBuilder(RestaurantKiosk.currentItemName);
            if (lettuceCheckBox.isSelected()) customizations.append(" + Lettuce");
            if (ketchupCheckBox.isSelected()) customizations.append(" + Ketchup");
            if (cheeseCheckBox.isSelected()) customizations.append(" + Cheese");
            if (onionsCheckBox.isSelected()) customizations.append(" + Onions");

            RestaurantKiosk.currentOrder.addItem(customizations.toString(), RestaurantKiosk.currentItemPrice);
            lettuceCheckBox.setSelected(false);
            ketchupCheckBox.setSelected(false);
            cheeseCheckBox.setSelected(false);
            onionsCheckBox.setSelected(false);
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

    // Create Burger Nutritional Information Panel
    public static JPanel createBurgerInfoPanel() {
        JPanel nutritionPanel = new JPanel();
        nutritionPanel.setLayout(new BoxLayout(nutritionPanel, BoxLayout.Y_AXIS));
        nutritionPanel.setBorder(BorderFactory.createTitledBorder("Nutrition Info"));

        JLabel caloriesLabel = new JLabel("Calories: 390");
        JLabel fatLabel = new JLabel("Fat: 20g");
        JLabel carbsLabel = new JLabel("Carbohydrates: 32g");
        JLabel proteinLabel = new JLabel("Protein: 22g");

        nutritionPanel.add(caloriesLabel);
        nutritionPanel.add(fatLabel);
        nutritionPanel.add(carbsLabel);
        nutritionPanel.add(proteinLabel);

        return nutritionPanel;
    }
}