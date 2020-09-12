package org.chaoscoders.firstextension;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //lädt das root-Objekt aus der FXML (mit allem was so drin ist)
        Parent root = FXMLLoader.load(getClass().getResource("layout/Main.fxml"));

        primaryStage.setTitle("Hello World");

        //bei Bedarf hier den Namen des Icons anpassen
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
