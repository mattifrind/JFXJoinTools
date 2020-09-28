package org.chaoscoders.extensions.calculator.frontend;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import org.chaoscoders.extensions.calculator.backend.Calculations;

public class CalcView1Controller {

    public TextField tfInput;
    public Button btnCalculate;
    public TitledPane tpAcceptedInput;

    public void calculate(ActionEvent event){
        Calculations.evaluate(tfInput.getText());
    }
}
