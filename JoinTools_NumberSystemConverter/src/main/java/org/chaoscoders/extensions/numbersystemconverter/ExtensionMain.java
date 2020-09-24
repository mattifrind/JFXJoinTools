package org.chaoscoders.extensions.numbersystemconverter;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.chaoscoders.jfxextensionapi.api.JavaFXExtension;
import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionInfo;
import org.chaoscoders.jfxextensionapi.api.settings.Settings;
import org.chaoscoders.jfxextensionapi.api.util.CustomFXMLLoader;

import java.util.UUID;


@SuppressWarnings("ALL")
public class ExtensionMain extends JavaFXExtension {

    @SuppressWarnings("unused")
    public ExtensionMain(UUID pluginID) {
        super(pluginID);
    }

    @Override
    public Node getRoot() {
        return CustomFXMLLoader.loadFXMLFile("Main.fxml", this, (Node) new AnchorPane(), new MainController());
    }

    @Override
    public Settings getSettings() {
        return null;
    }

    @Override
    public ExtensionInfo getInfo() {
        return new ExtensionInfo("Justus", "0.1", "Zahlensysteme", "Rechne Zahlensysteme um.","Funktionen....");
    }
}
