package org.chaoscoders.jfxextensionapi.api.settings;

public class ToggleSettingObject extends SettingObject{

    private boolean isActive;

    public ToggleSettingObject(String settingName, String description, int orderNo) {
        super(settingName, description, orderNo);
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public void setValue(Object value) {

    }


    //TODO: ...
}
