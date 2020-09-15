package org.chaoscoders.jfxextensionapi.frontend;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import org.chaoscoders.jfxextensionapi.api.JavaFXExtension;
import org.chaoscoders.jfxextensionapi.api.util.GuiManager;
import org.chaoscoders.jfxextensionapi.frontend.util.CustomMenubar;
import org.chaoscoders.jfxextensionapi.frontend.util.Helper;
import org.chaoscoders.jfxextensionapi.frontend.util.ShutDownMenu;
import org.chaoscoders.jfxextensionapi.frontend.util.Widget;
import org.chaoscoders.jfxextensionapi.frontend.loader.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Main extends Application {

    //TODO: Layout pfad in plugin.yml hinzufügen, die dateien daraus in einen TMP Ordner hinzufügen

    public static String pluginFolder;
    public static HashMap<Integer, Node> pluginMainpages;
    public static ArrayList<JavaFXExtension> plugins;

    private static String tmpdir;

    public static String getTmpdir(UUID pluginUUID){
        return tmpdir + "\\" + pluginUUID.toString();
    }

    public static String getTmpdir(){
        return tmpdir;
    }

    public static JavaFXExtension resolvePluginID(UUID pluginID){
        for(JavaFXExtension javaFXExtension : plugins){
            if(javaFXExtension.getPluginID() == pluginID){
                return javaFXExtension;
            }
        }
        System.out.println("Error. Couldn't resolve pluginID");
        return null;
    }

    //TODO: prüfen dass kein Pluginname doppelt ist
    //TODO: UUIDs zuweisen und plugins zuordnen - UUID manager?

    public static void setCenter(Node node){
        GuiManager.root.setCenter(node);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initInstances(){
        plugins = new ArrayList<>();
        tmpdir = System.getProperty("java.io.tmpdir");
        pluginFolder = "../plugins";
        pluginMainpages = new HashMap<>();
        GuiManager.initIcons();
        GuiManager.poweroffscreen = new ShutDownMenu();
    }

    @Override
    public void start(Stage primaryStage) {

        initInstances();

        GuiManager.customMenubar = new CustomMenubar(Screen.getPrimary().getVisualBounds().getWidth());

        GuiManager.home = new AnchorPane();

        GuiManager.root = new BorderPane();
        GuiManager.showHomeScreen();
        GuiManager.root.setTop(GuiManager.customMenubar);
        primaryStage.setTitle("Application Hub");
        //primaryStage.setFullScreen(true);
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(GuiManager.root, 720, 540));
        primaryStage.show();
    }


}