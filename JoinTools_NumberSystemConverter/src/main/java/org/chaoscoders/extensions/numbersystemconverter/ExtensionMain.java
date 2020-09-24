package org.chaoscoders.extensions.numbersystemconverter;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
        return CustomFXMLLoader.loadFXMLFile("Main.fxml", this, (Node) new GridPane(), new MainController());
    }

    @Override
    public Settings getSettings() {
        return null;
    } //Später für Einstellungen, benötigt Settings-Objekt.
    //Ne art Liste für Settings die im Launcher eingestellt werden kann. Tolles zeug, mal sehen ob Luca das packt.

    @Override
    public ExtensionInfo getInfo() {
        return new ExtensionInfo("Justus", "0.1", "Zahlensysteme", "Rechne Zahlensysteme um.","Funktionen....",
                "#ffffff", "#6e04a3", "#2d0044");
    }
}
