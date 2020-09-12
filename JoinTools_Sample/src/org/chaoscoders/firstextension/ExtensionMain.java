package org.chaoscoders.firstextension;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.chaoscoders.jfxextensionapi.api.JavaFXExtension;
import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionInfo;
import org.chaoscoders.jfxextensionapi.api.settings.Settings;


public class ExtensionMain extends JavaFXExtension {

    public ExtensionMain(int pluginID) {
        super(pluginID);
    }

    @Override
    public Node getRoot() {
        return new HBox(new Button("Klick mich!"), new TextField("Eingabe..."));
    }

    @Override
    public Settings getSettings() {
        return null;
    }

    @Override
    public ExtensionInfo getInfo() {
        return new ExtensionInfo("Luca", "", "", "", "", "Test description");
    }
}
