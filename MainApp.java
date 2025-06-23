import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalorieCalculatorUI ui = new CalorieCalculatorUI();
            ui.setVisible(true);
        });
    }
}
