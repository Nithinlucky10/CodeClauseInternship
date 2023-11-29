import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SimpleSwingCalculator {

    private JTextField display;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            new SimpleSwingCalculator().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Simple Swing Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI(frame);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    private void createUI(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        panel.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            switch (buttonText) {
                case "=":
                    evaluateExpression();
                    break;
                case "C":
                    clearDisplay();
                    break;
                default:
                    appendToDisplay(buttonText);
                    break;
            }
        }
    }

    private void evaluateExpression() {
        try {
            String expression = display.getText();
            double result = Calculator.evaluate(expression);
            display.setText(Double.toString(result));
        } catch (Exception e) {
            display.setText("Error");
        }
    }

    private void clearDisplay() {
        display.setText("");
    }

    private void appendToDisplay(String value) {
        display.setText(display.getText() + value);
    }

    static class Calculator {
        static double evaluate(String expression) {
            // Implement your expression evaluation logic here
            // For simplicity, we'll use a basic approach for demonstration purposes
            return new java.util.Scanner(expression).nextDouble();
        }
    }
}
