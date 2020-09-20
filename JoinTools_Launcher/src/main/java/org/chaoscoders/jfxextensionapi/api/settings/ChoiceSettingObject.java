package org.chaoscoders.jfxextensionapi.api.settings;

import java.util.Arrays;
import java.util.HashSet;

public class ChoiceSettingObject extends SettingObject {

    private HashSet<String> options;
    private String selected;

    public ChoiceSettingObject(String settingName, String description, String defaultValue, int orderID, String ... options) {
        super(settingName, description, orderID);
        this.options = new HashSet<>();
        this.options.addAll(Arrays.asList(options));
        this.options.add(defaultValue);
        this.selected = defaultValue;
    }

    public void addOption(String option){
        this.options.add(option);
    }

    public void removeOption(String option){
        this.options.remove(option);
    }

    public Object getValue() {
        return selected;
    }

    @Override
    public void setValue(Object value) {
        
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
