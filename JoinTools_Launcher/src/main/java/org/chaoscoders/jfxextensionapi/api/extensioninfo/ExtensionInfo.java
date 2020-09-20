package org.chaoscoders.jfxextensionapi.api.extensioninfo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.chaoscoders.jfxextensionapi.frontend.Main;
import org.chaoscoders.jfxextensionapi.frontend.controller.InfoPageController;

import java.io.IOException;

public class ExtensionInfo {
    private final String author;
    private final String version;
    private final String name;
    private final String tooltip;
    private final String description;

    public ExtensionInfo(String author, String version, String name, String tooltip, String description) {
        this.author = author;
        this.version = version;
        this.name = name;
        this.tooltip = tooltip;
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getTooltip() {
        return tooltip;
    }

    public String getDescription() {
        return description;
    }
    
    public Node getInfoPage(){
        Node pane = null;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("InfoPagePreset.fxml"));
            loader.setController(new InfoPageController(this.getVersion(), this.getAuthor(), this.getDescription(), this.getName()));
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }
}
