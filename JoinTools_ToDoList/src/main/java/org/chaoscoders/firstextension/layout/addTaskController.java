package org.chaoscoders.firstextension.layout;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;

public class addTaskController {
    @FXML
    TextArea descriptionArea;
    @FXML
    Button doneButton;
    @FXML
    public void addTaskAndGoToMain(){
        Task newTask = new Task(descriptionArea.getText());
        Main.Tasks.add(newTask);
        Stage primaryStage = Main.primaryStage;
        primaryStage.setScene(Main.addTaskScene);
    }
}
