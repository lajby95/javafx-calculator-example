package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           System.out.println("number1 amikor eredmeny: "+number1);
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
           System.out.println("result: "+result);
           BigDecimal decimal = new BigDecimal(result).setScale(15, RoundingMode.HALF_UP).stripTrailingZeros();
           display.setText(decimal.toPlainString());
           operator = "";
        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
        }
    }

    @FXML
    public void clearAll(){
        startNumber = true;
        operator = "";
        number1 = 0;
        display.setText("0");
        System.out.println("number1: "+number1);
    }

    @FXML
    public void addDecimal(){
        if(display.getText().contains(".")){
            return;
        }
        display.setText(display.getText()+".");
        System.out.println("number1 amikor adddecimal:"+number1);
    }

    @FXML
    public void negateNumber(){
        String num = display.getText();
        if(num.contains("-")) {
            num = num.replace("-", "");
        } else {
            num = "-"+num;
        }
        display.setText(num);
    }
}
