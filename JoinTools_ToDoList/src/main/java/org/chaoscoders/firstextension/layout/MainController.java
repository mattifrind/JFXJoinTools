package org.chaoscoders.firstextension.layout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.LinkedList;

public class MainController {
    @FXML
    Button addTaskButton;
    @FXML
    public void addTaskButtonAction(){
        Stage primaryStage = Main.primaryStage;
        primaryStage.setScene(Main.addTaskScene);
    }
}
