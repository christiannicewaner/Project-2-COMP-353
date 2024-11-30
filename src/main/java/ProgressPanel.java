import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProgressPanel {
    // Progress Screen Panel
    public static JPanel createProgressScreenPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel progressLabel = new JLabel("Processing your order, please wait...", SwingConstants.CENTER);
        progressLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(progressLabel, BorderLayout.NORTH);

        // Create Progress Bar
        JProgressBar progressBar = new JProgressBar(0, 3);
        progressBar.setStringPainted(true);
        progressBar.setString("Sending Order");
        panel.add(progressBar, BorderLayout.CENTER);

        // Countdown label
        JLabel countdownLabel = new JLabel("", SwingConstants.CENTER);
        countdownLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(countdownLabel, BorderLayout.SOUTH);

        // SwingWorker to update progress
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(6666);
                    publish(i + 1);
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int progress = chunks.getLast();
                progressBar.setValue(progress);
                switch (progress) {
                    case 1:
                        progressBar.setString("Order Received");

                        break;
                    case 2:
                        progressBar.setString("Preparing Order");
                        progressBar.setFont(new Font("Serif", Font.BOLD, 24));
                        break;
                    case 3:
                        progressBar.setString("Ready for Pickup!");
                        progressBar.setFont(new Font("Serif", Font.BOLD, 36));
                        startCountdown(countdownLabel);
                        panel.remove(progressLabel);
                        break;
                }
            }

            private void startCountdown(JLabel label) {
                new Thread(() -> {
                    for (int i = 3; i > 0; i--) {
                        try {
                            int finalI = i;
                            SwingUtilities.invokeLater(() -> label.setText("Returning to home in " + finalI + "..."));
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            //noinspection CallToPrintStackTrace
                            e.printStackTrace();
                        }
                    }
                    SwingUtilities.invokeLater(() -> RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Home"));
                }).start();
            }
        };
        worker.execute();
        return panel;
    }
}
