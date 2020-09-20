package org.chaoscoders.jfxextensionapi.api.util;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionInfo;
import org.chaoscoders.jfxextensionapi.api.settings.SettingManager;
import org.chaoscoders.jfxextensionapi.frontend.Main;
import org.chaoscoders.jfxextensionapi.frontend.loader.ExtensionLoader;
import org.chaoscoders.jfxextensionapi.frontend.util.CustomMenubar;

import java.util.Objects;
import java.util.UUID;

public class GuiManager {

    public static BorderPane root;

    public static Node home;
    public static Node powerOffScreen;
    public static Node lastScreen;
    public static CustomMenubar customMenubar;

    public static Image image_back;
    public static Image image_restart;
    public static Image image_shutdown;
    public static Image image_power;
    public static Image image_home;

    public static void initIcons(){
        image_back = new Image("back.png");
        image_shutdown = new Image("shutdown.png");
        image_home = new Image("home.png");
        image_power = new Image("power.png");
        image_restart = new Image("r.png");
    }

    public static void changeGuiFrame(Node content){
        lastScreen = root.getCenter();
        customMenubar.setHome(false);
        root.setCenter(content);
    }

    public static void openPlugin(UUID pluginID){
        customMenubar.setHome(false);
        root.setCenter(Objects.requireNonNull(ExtensionLoader.resolvePluginID(pluginID)).getRoot());
    }


    public static void openSettingsPage(UUID pluginID){
        customMenubar.setHome(false);
        GuiManager.root.setCenter(SettingManager.getSettingsPage(pluginID));
    }

    public static void openInfoPage(UUID pluginUUID){
        customMenubar.setHome(false);
        ExtensionInfo extInfo = ExtensionLoader.getExtensionInfo(pluginUUID);
        GuiManager.root.setCenter(extInfo.getInfoPage());
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
        root.setCenter(GuiManager.powerOffScreen);
    }
}
