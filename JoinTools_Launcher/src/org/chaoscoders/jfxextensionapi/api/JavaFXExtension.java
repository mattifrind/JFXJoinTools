package org.chaoscoders.jfxextensionapi.api;

import javafx.scene.Node;
import org.chaoscoders.jfxextensionapi.api.settings.Settings;
import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionInfo;


public abstract class JavaFXExtension implements Extensionmethods {

    private final int pluginID;

    public JavaFXExtension(int pluginID){
        this.pluginID = pluginID;
    }

    public void start(){
        //Node jNode = getRoot();
        //System.out.println(jNode.toString());
    }

    public int getPluginID(){
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

