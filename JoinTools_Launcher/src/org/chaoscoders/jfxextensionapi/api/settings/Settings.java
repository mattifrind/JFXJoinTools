package org.chaoscoders.jfxextensionapi.api.settings;

import java.util.Arrays;
import java.util.HashSet;

public class Settings {

    private HashSet<SettingObject> settings;

    public Settings(HashSet<SettingObject> settings) {
        this.settings = settings;
    }

    public Settings(SettingObject ... settingObjects){
        this.settings = new HashSet<>();
        this.settings.addAll(Arrays.asList(settingObjects));
    }

    public HashSet<SettingObject> getSettings(){
        return this.settings;
    }

    public void add(SettingObject s){
        this.settings.add(s);
    }

    public void remove(SettingObject s){
        this.settings.remove(s);
    }
}
