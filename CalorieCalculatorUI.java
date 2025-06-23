import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;

public class CalorieCalculatorUI extends JFrame {
    private JTextField ageField, weightField, heightField;
    private JComboBox<String> genderBox, activityBox;
    private ResultPanel resultPanel;
    private boolean isDarkMode = false;

    public CalorieCalculatorUI() {
        setTitle("Calorie Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 420);
        setLocationRelativeTo(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu settingsMenu = new JMenu("Settings");
        JMenuItem themeToggle = new JMenuItem("Toggle Dark/Light Mode");
        themeToggle.addActionListener(e -> {
            isDarkMode = !isDarkMode;
            applyTheme();
        });
        settingsMenu.add(themeToggle);
        menuBar.add(settingsMenu);
        setJMenuBar(menuBar);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField();
        formPanel.add(weightField);

        formPanel.add(new JLabel("Height (cm):"));
        heightField = new JTextField();
        formPanel.add(heightField);

        formPanel.add(new JLabel("Gender:"));
        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        formPanel.add(genderBox);

        formPanel.add(new JLabel("Activity Level:"));
        activityBox = new JComboBox<>(new String[]{
                "Sedentary", "Lightly active", "Moderately active", "Very active", "Super active"});
        formPanel.add(activityBox);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> handleCalculation());
        formPanel.add(calculateButton);

        // Result Panel
        resultPanel = new ResultPanel();

        add(formPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);

        applyTheme(); // apply default theme
    }

    private void handleCalculation() {
        try {
            int age = Integer.parseInt(ageField.getText().trim());
            double weight = Double.parseDouble(weightField.getText().trim());
            double height = Double.parseDouble(heightField.getText().trim());
            String gender = (String) genderBox.getSelectedItem();
            String activityLevel = (String) activityBox.getSelectedItem();

            UserData user = new UserData(age, weight, height, gender, activityLevel);
            double bmr = CalorieCalculator.calculateBMR(user);
            double tdee = CalorieCalculator.calculateTDEE(bmr, activityLevel);

            resultPanel.showResults(bmr, tdee);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values for age, weight, and height.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void applyTheme() {
        Color background = isDarkMode ? new Color(40, 40, 40) : Color.WHITE;
        Color foreground = isDarkMode ? Color.WHITE : Color.BLACK;

        getContentPane().setBackground(background);
        for (Component c : getContentPane().getComponents()) {
            updateComponentTheme(c, background, foreground);
        }

        JMenuBar menuBar = getJMenuBar();
        menuBar.setBackground(background);
        menuBar.setForeground(foreground);
        for (MenuElement menuElement : menuBar.getSubElements()) {
            JMenuItem item = (JMenuItem) menuElement.getComponent();
            item.setBackground(background);
            item.setForeground(foreground);
        }

        resultPanel.setTheme(isDarkMode);
    }

    private void updateComponentTheme(Component c, Color bg, Color fg) {
        if (c instanceof JPanel panel) {
            panel.setBackground(bg);
            for (Component child : panel.getComponents()) {
                updateComponentTheme(child, bg, fg);
            }
        } else if (c instanceof JLabel label) {
            label.setForeground(fg);
        } else if (c instanceof JTextField field) {
            field.setBackground(bg);
            field.setForeground(fg);
            field.setCaretColor(fg);
        } else if (c instanceof JComboBox<?> combo) {
            combo.setBackground(bg);
            combo.setForeground(fg);
        } else if (c instanceof JButton button) {
            button.setBackground(bg.darker());
            button.setForeground(fg);
        }
    }
}
