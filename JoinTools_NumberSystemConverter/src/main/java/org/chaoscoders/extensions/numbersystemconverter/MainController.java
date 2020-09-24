package org.chaoscoders.extensions.numbersystemconverter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

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
    Button btnConvert;

    @FXML
    private void printMyShit(ActionEvent event){
        System.out.println(tgCurrentSys.getSelectedToggle().getUserData()+" Original System");
        System.out.println(tgTargetSys.getSelectedToggle().getUserData()+" Target System");
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
