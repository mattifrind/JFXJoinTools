package org.chaoscoders.extensions.numbersystemconverter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    // ToggleGroup Original System
    @FXML
    ToggleGroup tgCurrentSys;
    @FXML
    RadioButton rbtnCurBin;
    @FXML
    RadioButton rbtnCurOct;
    @FXML
    RadioButton rbtnCurDec;
    @FXML
    RadioButton rbtnCurHex;

    //ToggleGroup  Target System
    @FXML
    ToggleGroup tgTargetSys;
    @FXML
    RadioButton rbtnTarBin;
    @FXML
    RadioButton rbtnTarOct;
    @FXML
    RadioButton rbtnTarDec;
    @FXML
    RadioButton rbtnTarHex;

    @FXML
    TextField tfResult;

    @FXML
    Button btnConvert;

    @FXML
    TextField tfNumberInput;

    @FXML
    private void convertButton(ActionEvent event){
        NumberSystem originSys = (NumberSystem) tgCurrentSys.getSelectedToggle().getUserData();
        NumberSystem tarSys = (NumberSystem) tgTargetSys.getSelectedToggle().getUserData();
        String numberInput = tfNumberInput.getText();
        tfResult.setText(NumberSysConverter.instantConversion(originSys, tarSys, numberInput));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rbtnCurBin.setUserData(NumberSystem.BINARY);
        rbtnCurOct.setUserData(NumberSystem.OCTAL);
        rbtnCurDec.setUserData(NumberSystem.DECIMAL);
        rbtnCurHex.setUserData(NumberSystem.HEX);

        rbtnTarBin.setUserData(NumberSystem.BINARY);
        rbtnTarOct.setUserData(NumberSystem.OCTAL);
        rbtnTarDec.setUserData(NumberSystem.DECIMAL);
        rbtnTarHex.setUserData(NumberSystem.HEX);
    }
}
