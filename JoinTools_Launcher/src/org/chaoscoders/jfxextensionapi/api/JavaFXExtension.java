package org.chaoscoders.jfxextensionapi.api;

import javafx.scene.Node;
import org.chaoscoders.jfxextensionapi.api.settings.Settings;
import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionInfo;

import java.util.UUID;


public abstract class JavaFXExtension implements Extensionmethods {

    private final UUID pluginUUID;

    public JavaFXExtension(UUID pluginUUID){
        this.pluginUUID = pluginUUID;
    }

    public UUID getPluginUUID(){
        return this.pluginUUID;
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

}

