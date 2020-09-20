package org.chaoscoders.jfxextensionapi.frontend;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import org.chaoscoders.jfxextensionapi.api.util.GuiManager;
import org.chaoscoders.jfxextensionapi.frontend.loader.ExtensionLoader;
import org.chaoscoders.jfxextensionapi.frontend.util.CustomMenubar;
import org.chaoscoders.jfxextensionapi.frontend.util.ShutDownMenu;
import org.chaoscoders.jfxextensionapi.frontend.util.TempFileManager;
import org.chaoscoders.jfxextensionapi.frontend.util.Widget;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        GuiManager.customMenubar = new CustomMenubar(Screen.getPrimary().getVisualBounds().getWidth());

        //loads all plugin widgets into the home node
        List<Widget> widgets = ExtensionLoader.getPlugins().stream()
                .map(plugin ->
                        ExtensionLoader.getExtensionWidget(plugin.getPluginUUID()))
                .collect(Collectors.toList());
        VBox widgetsContainer = new VBox();
        widgetsContainer.getChildren().addAll(widgets);
        GuiManager.home = widgetsContainer;

        GuiManager.root = new BorderPane();
        GuiManager.showHomeScreen();
        GuiManager.root.setTop(GuiManager.customMenubar);
        primaryStage.setTitle("JFXJoinTools");
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