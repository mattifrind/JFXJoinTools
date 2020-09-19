package org.chaoscoders.jfxextensionapi.api;

import javafx.scene.Node;
import javafx.scene.image.Image;
import org.chaoscoders.jfxextensionapi.api.settings.Settings;
import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionInfo;

import java.util.UUID;


public abstract class JavaFXExtension implements Extensionmethods {

    private final UUID pluginID;

    public JavaFXExtension(UUID pluginID){
        this.pluginID = pluginID;
    }

    public UUID getPluginUUID(){
        return this.pluginID;
    }
}

interface Extensionmethods {

    /**
     * Returns front page of your application
     * @return Node rootparent
     */
    Node getRoot();

    /**
     * Returns information required for Settings Page
     * @return list of SettingObjects*/
    Settings getSettings();

    /**
     * Returns information required for Information Page
     * @return ExtensionInfo info
     * */
    ExtensionInfo getInfo();


    //TODO: methode für das hauptfenster und methode für das settingsfenster
}

