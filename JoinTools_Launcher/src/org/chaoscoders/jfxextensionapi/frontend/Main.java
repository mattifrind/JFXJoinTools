package org.chaoscoders.jfxextensionapi.frontend;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import org.chaoscoders.jfxextensionapi.api.JavaFXExtension;
import org.chaoscoders.jfxextensionapi.api.util.GuiManager;
import org.chaoscoders.jfxextensionapi.frontend.loader.ExtensionLoader;
import org.chaoscoders.jfxextensionapi.frontend.util.CustomMenubar;
import org.chaoscoders.jfxextensionapi.frontend.util.ShutDownMenu;
import org.chaoscoders.jfxextensionapi.frontend.util.TempFileManager;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Main extends Application {

    //TODO: Layout pfad in plugin.yml hinzufügen, die dateien daraus in einen TMP Ordner hinzufügen

    public static String pluginFolder;
    private static String tmpdir;

    public static String getTmpdir(UUID pluginUUID){
        return tmpdir + "\\" + pluginUUID.toString();
    }

    public static String getTmpdir(){
        return tmpdir;
    }

    public static void setCenter(Node node){
        GuiManager.root.setCenter(node);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initInstances(){
        tmpdir = System.getProperty("java.io.tmpdir") + "\\JoinTools";
        pluginFolder = "../plugins";
        GuiManager.initIcons();
        GuiManager.poweroffscreen = new ShutDownMenu();
    }

    @Override
    public void start(Stage primaryStage) {


        initInstances();
        TempFileManager.initTmpDirs();

        ExtensionLoader.loadPlugins();

        GuiManager.customMenubar = new CustomMenubar(Screen.getPrimary().getVisualBounds().getWidth());

        GuiManager.home = new VBox(ExtensionLoader.getExtensionWidget(ExtensionLoader.getPlugins().get(0).getPluginID()));

        GuiManager.root = new BorderPane();
        GuiManager.showHomeScreen();
        GuiManager.root.setTop(GuiManager.customMenubar);
        primaryStage.setTitle("Application Hub");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(GuiManager.root, 720, 540));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        TempFileManager.cleanUp();
    }
}