package org.chaoscoders.jfxextensionapi.api.settings;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.UUID;

public class SettingManager {

    //TODO: Methoden um Settings auszulesen und zu schreiben, sowie zu speichern
    //Hint: Preference Package nutzen

    //Methoden für einfacheren zugriff auf preferences

    public static void saveSetting(SettingObject settingObject, int pluginID){
        //Speichern eines Settings abhängig von der pluginID und dem settingobject name
    }

    public static void saveSettings(Settings settings, int pluginID){
        for(SettingObject s : settings.getSettings()){
            saveSetting(s, pluginID);
        }
    }

    public static SettingObject getSetting(String settingname, int pluginID){
        //TODO: setting auslesen und abhängig vom typ das passende object zurückgeben
        return null;
    }

    public static Node getSettingsPage(UUID pluginID){
        //TODO: Build settings pane
        GridPane g = new GridPane();
        return g;
    }

}
