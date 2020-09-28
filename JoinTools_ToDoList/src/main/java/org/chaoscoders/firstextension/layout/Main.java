package org.chaoscoders.firstextension.layout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.LinkedList;

public class Main extends Application {

    public static Stage primaryStage;
    public static Scene mainScene;
    public static Scene addTaskScene;
    public static LinkedList<Task> Tasks = new LinkedList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //lädt das root-Objekt aus der FXML (mit allem was so drin ist)

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getClassLoader().getResource("Main.fxml"));
        fxmlLoader.setController(new MainController());
        fxmlLoader.setRoot(new AnchorPane());
        Parent root = fxmlLoader.load();

        //Parent root =  FXMLLoader.load(Main.class.getClassLoader().getResource("Main.fxml"));

        primaryStage.setTitle("JoinTools ToDoList");

        //bei Bedarf hier den Namen des Icons anpassen
        primaryStage.getIcons().add(new Image("org/chaoscoders/firstextension/res/icon.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //Setup Stage und Scenes
        Main.primaryStage = primaryStage;
        Main.mainScene = new Scene(FXMLLoader.load(getClass().getResource("Main.fxml")));
        Main.addTaskScene = new Scene(FXMLLoader.load(getClass().getResource("addTask.fxml")));
    }

    public static void main(String[] args) {
        launch(args);
    }
}