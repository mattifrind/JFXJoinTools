package org.chaoscoders.jfxextensionapi.frontend;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import org.chaoscoders.jfxextensionapi.api.Helper;
import org.chaoscoders.jfxextensionapi.api.JavaFXExtension;
import org.chaoscoders.jfxextensionapi.api.config.Configloader;
import org.chaoscoders.jfxextensionapi.api.util.GuiManager;
import org.chaoscoders.jfxextensionapi.frontend.util.CustomMenubar;
import org.chaoscoders.jfxextensionapi.frontend.util.ShutDownMenu;
import org.chaoscoders.jfxextensionapi.frontend.util.Widget;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main extends Application {

    //TODO: Layout pfad in plugin.yml hinzufügen, die dateien daraus in einen TMP Ordner hinzufügen

    public static String tmpdir;
    public static String pluginFolder;

    public static HashMap<Integer, Node> pluginMainpages;

    public static ArrayList<JavaFXExtension> plugins;


    public static JavaFXExtension resolvePluginID(int pluginID){
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

    private void initInstances(){
        plugins = new ArrayList<>();
        tmpdir = System.getProperty("java.io.tmpdir");
        pluginFolder = "D:\\Dokumente\\PluginFolder";
        pluginMainpages = new HashMap<>();
        GuiManager.initIcons();
        GuiManager.poweroffscreen = new ShutDownMenu();
    }

    @Override
    public void start(Stage primaryStage) {

        initInstances();

        GuiManager.customMenubar = new CustomMenubar(Screen.getPrimary().getVisualBounds().getWidth());

        GuiManager.home = getHubLayout();

        GuiManager.root = new BorderPane();
        GuiManager.showHomeScreen();
        GuiManager.root.setTop(GuiManager.customMenubar);
        primaryStage.setTitle("Application Hub");
        //primaryStage.setFullScreen(true);
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(GuiManager.root, 720, 540));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Parent getHubLayout(){

        VBox vBox = new VBox();
        HBox row1 = new HBox();
        HBox row2 = new HBox();
        int pluginID = 0;
        for(File plugin : Objects.requireNonNull((new File(pluginFolder)).listFiles())){
            if (plugin.isFile() && plugin.getName().endsWith(".jar")) {
                try {
                    Helper helper = new Helper();
                    //TODO: auf configloader umstellen
                    String path = Configloader.getConfigParameter(plugin.toURL(), "main");
                    String name = Configloader.getConfigParameter(plugin.toURL(), "name");
                    String tooltip = Configloader.getConfigParameter(plugin.toURL(), "tooltip");

                    if(! path.equalsIgnoreCase("")){
                        //TODO: exception handling für fälle wo der pfad falsch ist
                        Class<?> pluginMainClass = helper.getClassFromPath(plugin, path);

                        //TODO: NOsuchmethod, instantiationexception catchen
                        if(pluginMainClass.getConstructor(int.class).newInstance(pluginID) instanceof JavaFXExtension){
                            JavaFXExtension extension = (JavaFXExtension) pluginMainClass.getConstructor(int.class).newInstance(pluginID);
                            Main.plugins.add(extension);

                            Method printMethod = pluginMainClass.getMethod("getRoot");
                            pluginMainpages.put(pluginID, (Node) printMethod.invoke(extension));
                            Widget extensionWidget = new Widget(20, 50, name,
                                    helper.getExtensionIcon(plugin.toURL()),
                                    tooltip, Color.valueOf("#161616"),
                                    Color.valueOf("#444444"), Color.valueOf("#ffffff"), pluginID);
                            if(pluginID % 2 == 0){
                                row1.getChildren().add(extensionWidget);
                            }else {
                                row2.getChildren().add(extensionWidget);
                            }
                            pluginID++;
                        }
                    }

                }catch(URISyntaxException | IOException | NoSuchMethodException | IllegalAccessException e){
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        vBox.getChildren().addAll(row1, row2);
        ScrollPane root = new ScrollPane();
        root.setContent(vBox);
        root.pannableProperty().set(true);
        root.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        root.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        root.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() - 200);
        root.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth() - 200);
        return root;
    }

    public void loadPlugins(){
        //return something
    }

}