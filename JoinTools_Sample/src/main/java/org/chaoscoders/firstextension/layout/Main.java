package org.chaoscoders.firstextension.layout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //lädt das root-Objekt aus der FXML (mit allem was so drin ist)

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getClassLoader().getResource("Main.fxml"));
        fxmlLoader.setController(new MainController());
        fxmlLoader.setRoot(new AnchorPane());
        Parent root = fxmlLoader.load();

        //Parent root =  FXMLLoader.load(Main.class.getClassLoader().getResource("Main.fxml"));

        primaryStage.setTitle("JoinTools Sample Plugin");

        //bei Bedarf hier den Namen des Icons anpassen
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}