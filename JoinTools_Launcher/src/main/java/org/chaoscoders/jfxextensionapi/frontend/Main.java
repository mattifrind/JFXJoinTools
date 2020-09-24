package org.chaoscoders.jfxextensionapi.frontend;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import org.chaoscoders.jfxextensionapi.api.util.GuiManager;
import org.chaoscoders.jfxextensionapi.frontend.loader.ExtensionLoader;
import org.chaoscoders.jfxextensionapi.frontend.util.*;

import java.util.UUID;

public class Main extends Application {


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
        GuiManager.powerOffScreen = new ShutDownMenu();
    }

    @Override
    public void start(Stage primaryStage) {
        initInstances();
        TempFileManager.initTmpDirs();
        ExtensionLoader.loadPlugins();

        GuiManager.customMenubar = new CustomMenubar(primaryStage);

        //WidgetPane automatically loads all widgets
        ScrollPane mainScrollPane = new ScrollPane(new WidgetsPane(primaryStage));
        mainScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        GuiManager.home = mainScrollPane;

        GuiManager.root = new BorderPane();
        GuiManager.showHomeScreen();
        GuiManager.root.setTop(GuiManager.customMenubar);
        primaryStage.setTitle("JFXJoinTools");
        primaryStage.setMinWidth(450);
        primaryStage.setMinHeight(400);
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