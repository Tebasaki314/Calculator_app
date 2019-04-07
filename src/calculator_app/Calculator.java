package calculator_app;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Window;

public class Calculator {
    private JPanel mainPanel;

    private JTextField resultTextField;
    private JRadioButton ONRadioButton;
    private JRadioButton OFFRadioButton;

    private JButton num0Button;
    private JButton num1Button;
    private JButton num2Button;
    private JButton num3Button;
    private JButton num4Button;
    private JButton num5Button;
    private JButton num6Button;
    private JButton num7Button;
    private JButton num8Button;
    private JButton num9Button;
    private JButton dotButton;

    private JButton opPlusButton;
    private JButton opMinusButton;
    private JButton opDivButton;
    private JButton opTimesButton;

    private JButton resultButton;
    private JButton clearButton;
    private JButton backSpaceButton;
    private JLabel operationBufferLabel;

    private double result, num;
    private MathOperation mathOperation;

    private enum MathOperation {
        MO_PLUS,
        MO_MINUS,
        MO_TIMES,
        MO_DIV
    }

    private Calculator() {
        num0Button.addActionListener(e -> resultTextField.setText(resultTextField.getText() + "0"));
        num1Button.addActionListener(e -> resultTextField.setText(resultTextField.getText() + "1"));
        num2Button.addActionListener(e -> resultTextField.setText(resultTextField.getText() + "2"));
        num3Button.addActionListener(e -> resultTextField.setText(resultTextField.getText() + "3"));
        num4Button.addActionListener(e -> resultTextField.setText(resultTextField.getText() + "4"));
        num5Button.addActionListener(e -> resultTextField.setText(resultTextField.getText() + "5"));
        num6Button.addActionListener(e -> resultTextField.setText(resultTextField.getText() + "6"));
        num7Button.addActionListener(e -> resultTextField.setText(resultTextField.getText() + "7"));
        num8Button.addActionListener(e -> resultTextField.setText(resultTextField.getText() + "8"));
        num9Button.addActionListener(e -> resultTextField.setText(resultTextField.getText() + "9"));
        dotButton.addActionListener(e -> resultTextField.setText(resultTextField.getText() + "."));

        opPlusButton.addActionListener(e -> {
            num = Double.parseDouble(resultTextField.getText());
            mathOperation = MathOperation.MO_PLUS;
            resultTextField.setText("");
            operationBufferLabel.setText(num + "+");
        });
        opMinusButton.addActionListener(e -> {
            num = Double.parseDouble(resultTextField.getText());
            mathOperation = MathOperation.MO_MINUS;
            resultTextField.setText("");
            operationBufferLabel.setText(num + "-");
        });
        opTimesButton.addActionListener(e -> {
            num = Double.parseDouble(resultTextField.getText());
            mathOperation = MathOperation.MO_TIMES;
            resultTextField.setText("");
            operationBufferLabel.setText(num + "*");
        });
        opDivButton.addActionListener(e -> {
            num = Double.parseDouble(resultTextField.getText());
            mathOperation = MathOperation.MO_DIV;
            resultTextField.setText("");
            operationBufferLabel.setText(num + "/");
        });

        resultButton.addActionListener(e -> {
            arithmetic_operation();
            operationBufferLabel.setText("");
        });

        clearButton.addActionListener(e -> resultTextField.setText(""));
        OFFRadioButton.addActionListener(e -> setAllEnable(false));
        ONRadioButton.addActionListener(e -> setAllEnable(true));
        backSpaceButton.addActionListener(e -> {
            int length = resultTextField.getText().length();
            int number = resultTextField.getText().length() - 1;
            String store;

            if (length > 0) {
                StringBuilder back = new StringBuilder(resultTextField.getText());
                back.deleteCharAt(number);
                store = back.toString();
                resultTextField.setText(store);
            }
        });
    }

    private void arithmetic_operation() {
        switch(mathOperation) {
            case MO_PLUS:
                result = num + Double.parseDouble(resultTextField.getText());
                resultTextField.setText(Double.toString(result));
                break;
            case MO_MINUS:
                result = num - Double.parseDouble(resultTextField.getText());
                resultTextField.setText(Double.toString(result));
                break;
            case MO_TIMES:
                result = num * Double.parseDouble(resultTextField.getText());
                resultTextField.setText(Double.toString(result));
                break;
            case MO_DIV:
                result = num / Double.parseDouble(resultTextField.getText());
                resultTextField.setText(Double.toString(result));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + mathOperation);
        }
    }

    private void setAllEnable(Boolean bool) {
        resultTextField.setEnabled(bool);
        num0Button.setEnabled(bool);
        num1Button.setEnabled(bool);
        num2Button.setEnabled(bool);
        num3Button.setEnabled(bool);
        num4Button.setEnabled(bool);
        num5Button.setEnabled(bool);
        num6Button.setEnabled(bool);
        num7Button.setEnabled(bool);
        num8Button.setEnabled(bool);
        num9Button.setEnabled(bool);
        dotButton.setEnabled(bool);

        resultButton.setEnabled(bool);
        opDivButton.setEnabled(bool);
        opTimesButton.setEnabled(bool);
        opMinusButton.setEnabled(bool);
        opPlusButton.setEnabled(bool);
        clearButton.setEnabled(bool);
        backSpaceButton.setEnabled(bool);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setType(Window.Type.UTILITY);
        frame.setResizable(false);
        frame.setLocation(500, 250);
        frame.setPreferredSize(new Dimension(270, 432));
        frame.pack();
        frame.setVisible(true);
    }
}
