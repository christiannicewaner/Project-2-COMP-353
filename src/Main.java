import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JLabel jsFirstName;
    private JTextField jsTextField;
    private JButton jsButton1;
    private JPanel MainPanel;

    public Main() {
        setContentPane(MainPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        // could replace this with lambda, won't for now
        jsButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = jsTextField.getText();
                jsFirstName.setText(firstName);
                JOptionPane.showMessageDialog(Main.this, "Welcome: " + firstName + "!");
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
