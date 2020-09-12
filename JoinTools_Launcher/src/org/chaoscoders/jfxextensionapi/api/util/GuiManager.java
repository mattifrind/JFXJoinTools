package org.chaoscoders.jfxextensionapi.api.util;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.chaoscoders.jfxextensionapi.api.settings.SettingManager;
import org.chaoscoders.jfxextensionapi.frontend.Main;
import org.chaoscoders.jfxextensionapi.frontend.util.CustomMenubar;
import org.chaoscoders.jfxextensionapi.frontend.util.ShutDownMenu;

import java.util.Objects;

public class GuiManager {

    public static BorderPane root;

    public static Node home;
    public static Node poweroffscreen;
    public static Node lastScreen;
    public static CustomMenubar customMenubar;

    public static Image image_back;
    public static Image image_restart;
    public static Image image_shutdown;
    public static Image image_power;
    public static Image image_home;

    public static void initIcons(){
        image_back = new Image(Main.class.getResourceAsStream("/back.png"));
        image_shutdown = new Image(Main.class.getResourceAsStream("/shutdown.png"));
        image_home = new Image(Main.class.getResourceAsStream("/home.png"));
        image_power = new Image(Main.class.getResourceAsStream("/power.png"));
        image_restart = new Image(Main.class.getResourceAsStream("/r.png"));

    }

    public static void changeGuiFrame(Node content){
        lastScreen = root.getCenter();
        customMenubar.setHome(false);
        root.setCenter(content);
    }

    public static void openPlugin(int pluginID){
        customMenubar.setHome(false);
        root.setCenter(Objects.requireNonNull(Main.resolvePluginID(pluginID)).getRoot());
    }


    public static void openSettingsPage(int pluginID){
        customMenubar.setHome(false);
        GuiManager.root.setCenter(SettingManager.getSettingsPage(pluginID));
    }

    public static void openInfoPage(int pluginID){
        //TODO: öffnen einer seite abhängig von der pluginID
        customMenubar.setHome(false);
        GuiManager.root.setCenter(new AnchorPane());
    }

    public static void showHomeScreen(){
        lastScreen = null;
        customMenubar.setHome(true);
        customMenubar.showPowerButton();
        GuiManager.root.setCenter(GuiManager.home);
    }

    public static void showPowerOffScreen(){
        lastScreen = root.getCenter();
        customMenubar.setHome(false);
        root.setCenter(GuiManager.poweroffscreen);
    }
}
