package org.chaoscoders.firstextension;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        return CustomFXMLLoader.getFxmlFile("Main.fxml", this, new AnchorPane(),  new MainController());

        //return new HBox(new Button("Klick mich!"), new TextField("Eingabe..."));
    }

    @Override
    public Settings getSettings() {
        return null;
    }

    @Override
    public ExtensionInfo getInfo() {
        return new ExtensionInfo("Luca", "", "Sample", "Dolle Sache", "", "Test description");
    }
}
