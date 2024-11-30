import javax.swing.*;
import java.awt.*;

public class SaladPanel {
    // Customization Panel for Salad
    public static JPanel createSaladPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        panel.add(AccountLabelPanel.createAccountLabelPanel(), BorderLayout.NORTH);
        JLabel titleLabel = new JLabel("Customize your Salad", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font size and style
        panel.add(titleLabel, BorderLayout.NORTH);

        // Add checkboxes for customization
        JCheckBox croutonsCheckBox = new JCheckBox("Croutons");
        JCheckBox dressingCheckBox = new JCheckBox("Dressing");
        JCheckBox cheeseCheckBox = new JCheckBox("Cheese");
        JCheckBox baconBitsCheckBox = new JCheckBox("Bacon Bits");
        checkBoxPanel.add(croutonsCheckBox);
        checkBoxPanel.add(dressingCheckBox);
        checkBoxPanel.add(cheeseCheckBox);
        checkBoxPanel.add(baconBitsCheckBox);

        // Nutritional Information Panel
        JPanel nutritionPanel = createSaladInfoPanel();

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Add to Order button
        JButton addButton = new JButton("Add to Order");
        addButton.addActionListener(e -> {
            StringBuilder customizations = new StringBuilder(RestaurantKiosk.currentItemName);
            if (croutonsCheckBox.isSelected()) customizations.append(" + Croutons");
            if (dressingCheckBox.isSelected()) customizations.append(" + Dressing");
            if (cheeseCheckBox.isSelected()) customizations.append(" + Cheese");
            if (baconBitsCheckBox.isSelected()) customizations.append(" + Bacon Bits");

            RestaurantKiosk.currentOrder.addItem(customizations.toString(), RestaurantKiosk.currentItemPrice);
            croutonsCheckBox.setSelected(false);
            dressingCheckBox.setSelected(false);
            cheeseCheckBox.setSelected(false);
            baconBitsCheckBox.setSelected(false);
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
    // Create Salad Nutritional Information Panel
    public static JPanel createSaladInfoPanel() {
        JPanel nutritionPanel = new JPanel();
        nutritionPanel.setLayout(new BoxLayout(nutritionPanel, BoxLayout.Y_AXIS));
        nutritionPanel.setBorder(BorderFactory.createTitledBorder("Nutrition Info"));

        JLabel caloriesLabel = new JLabel("Calories: 491");
        JLabel fatLabel = new JLabel("Fat: 19g");
        JLabel carbsLabel = new JLabel("Carbohydrates: 42g");
        JLabel proteinLabel = new JLabel("Protein: 39g");

        nutritionPanel.add(caloriesLabel);
        nutritionPanel.add(fatLabel);
        nutritionPanel.add(carbsLabel);
        nutritionPanel.add(proteinLabel);

        return nutritionPanel;
    }
}
