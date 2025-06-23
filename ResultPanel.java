import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    private JLabel resultLabel;

    public ResultPanel() {
        setLayout(new BorderLayout());
        resultLabel = new JLabel("Your calorie details will appear here.", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(resultLabel, BorderLayout.CENTER);
    }

    public void showResults(double bmr, double tdee) {
        String resultText = "<html><center>" +
                "BMR: " + String.format("%.2f", bmr) + " kcal/day<br>" +
                "TDEE: " + String.format("%.2f", tdee) + " kcal/day<br>" +
                "To lose weight: " + String.format("%.2f", tdee - 500) + " kcal/day<br>" +
                "To gain weight: " + String.format("%.2f", tdee + 500) + " kcal/day" +
                "</center></html>";
        resultLabel.setText(resultText);
    }

    public void setTheme(boolean dark) {
        setBackground(dark ? new Color(40, 40, 40) : Color.WHITE);
        resultLabel.setForeground(dark ? Color.WHITE : Color.BLACK);
    }
}
