package org.chaoscoders.extensions.todolist;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.chaoscoders.jfxextensionapi.api.JavaFXExtension;
import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionInfo;
import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionType;
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
        return new ExtensionInfo("Dominic", "0.1", "ToDoList", "Eine ToDo Liste",
                "Hier kannst du Listen zum effizienten Prokrastinieren schreiben!",
                "#ffffff", "#3232ff", "#ffffff", ExtensionType.UTIL);
    }
}
