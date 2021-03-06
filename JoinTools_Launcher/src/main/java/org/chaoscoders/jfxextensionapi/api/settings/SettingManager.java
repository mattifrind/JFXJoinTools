package org.chaoscoders.jfxextensionapi.api.settings;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.UUID;
import java.util.prefs.Preferences;

public class SettingManager {

    //TODO: Methoden um Settings auszulesen und zu schreiben, sowie zu speichern
    //Hint: Preference Package nutzen

    //Methoden für einfacheren zugriff auf preferences

    public static void saveSetting(SettingObject settingObject, String tree){
        //Speichern eines Settings abhängig von der pluginID und dem settingobject name
        Preferences myPrefs = Preferences.userRoot().node("JoinTools/" + tree);
        Object value = settingObject.getValue();
        if(value instanceof Integer){
            myPrefs.putInt(settingObject.getSettingName(), (int) value);
        }else if(value instanceof Long){
            myPrefs.putLong(settingObject.getSettingName(), (long) value);
        }else if(value instanceof Boolean){
            myPrefs.putBoolean(settingObject.getSettingName(), (boolean) value);
        }else if(value instanceof Float){
            myPrefs.putFloat(settingObject.getSettingName(), (float) value);
        }else if(value instanceof Double){
            myPrefs.putDouble(settingObject.getSettingName(), (double) value);
        }else{
            myPrefs.put(settingObject.getSettingName(), value.toString());
        }
    }

    public static void saveSettings(Settings settings, String tree){
        for(SettingObject s : settings.getSettings()){
            saveSetting(s, tree);
        }
    }

    public static SettingObject getSetting(String settingname, String tree){
        //TODO: setting auslesen und abhängig vom typ das passende object zurückgeben
        return null;
    }

    public static Node getSettingsPage(UUID pluginUUID){
        //TODO: Build settings pane
        GridPane g = new GridPane();
        return g;
    }

}
