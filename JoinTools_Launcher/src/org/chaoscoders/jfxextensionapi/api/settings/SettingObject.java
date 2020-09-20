package org.chaoscoders.jfxextensionapi.api.settings;

public abstract class SettingObject {

    private String settingName;
    private String description;
    private int orderNo;

    public SettingObject(String settingName, String description, int orderNo) {
        this.settingName = settingName;
        this.description = description;
        this.orderNo = orderNo;
    }

    public abstract Object getValue();

    public abstract void setValue(Object value);

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
