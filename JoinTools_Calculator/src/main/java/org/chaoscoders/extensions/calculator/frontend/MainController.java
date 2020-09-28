package org.chaoscoders.extensions.calculator.frontend;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
    //jeweils alle im FXML definiert. siehe kleines Symbol links neben der Zeile
    public TextField tfMain;
    public Button btnMain;
    public Label lblOutput;

    //Diese Methode wird von dem im FXML definierten Button (btnMain) aufgerufen
    public void printToLabel(ActionEvent actionEvent) {
        lblOutput.setText(getLabelText(tfMain.getText()));
    }

    public String getLabelText(String inputString) {
        return "Du hast \"" + inputString + "\" eingegeben.";
    }
}
