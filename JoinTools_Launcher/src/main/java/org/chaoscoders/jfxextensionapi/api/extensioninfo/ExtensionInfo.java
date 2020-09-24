package org.chaoscoders.jfxextensionapi.api.extensioninfo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import org.chaoscoders.jfxextensionapi.frontend.Main;
import org.chaoscoders.jfxextensionapi.frontend.controller.InfoPageController;

import java.io.IOException;

public class ExtensionInfo {
    private final String AUTHOR;
    private final String VERSION;
    private final String NAME;
    private final String TOOLTIP;
    private final String DESCRIPTION;
    private final Color TEXT_COLOR;
    private final Color BACKGROUND_COLOR;
    private final Color HIGHLIGHT_COLOR;
    private final ExtensionType TYPE;

    public ExtensionInfo(String AUTHOR, String VERSION, String NAME, String tooltip, String description) {
        this.AUTHOR = AUTHOR;
        this.VERSION = VERSION;
        this.NAME = NAME;
        this.TOOLTIP = tooltip;
        this.DESCRIPTION = description;

        this.TEXT_COLOR = Color.valueOf("#ffffff");
        this.BACKGROUND_COLOR = Color.valueOf("#444444");
        this.HIGHLIGHT_COLOR = Color.valueOf("#161616");
        this.TYPE = ExtensionType.UTIL;
    }

    public ExtensionInfo(String AUTHOR, String VERSION, String NAME, String tooltip, String description,
                         String textColor, String backgroundColor, String highlightColor) {
        this.AUTHOR = AUTHOR;
        this.VERSION = VERSION;
        this.NAME = NAME;
        this.TOOLTIP = tooltip;
        this.DESCRIPTION = description;
        this.TEXT_COLOR = Color.valueOf(textColor);
        this.BACKGROUND_COLOR = Color.valueOf(backgroundColor);
        this.HIGHLIGHT_COLOR = Color.valueOf(highlightColor);
        this.TYPE = ExtensionType.UTIL;
    }

    public ExtensionInfo(String AUTHOR, String VERSION, String NAME, String tooltip, String description,
                         String textColor, String backgroundColor, String highlightColor, ExtensionType type) {
        this.AUTHOR = AUTHOR;
        this.VERSION = VERSION;
        this.NAME = NAME;
        this.TOOLTIP = tooltip;
        this.DESCRIPTION = description;
        this.TEXT_COLOR = Color.valueOf(textColor);
        this.BACKGROUND_COLOR = Color.valueOf(backgroundColor);
        this.HIGHLIGHT_COLOR = Color.valueOf(highlightColor);
        this.TYPE = type;
    }

    public Color getTextColor() {
        return TEXT_COLOR;
    }

    public Color getBackgroundColor() {
        return BACKGROUND_COLOR;
    }

    public Color getHighlightColor() {
        return HIGHLIGHT_COLOR;
    }

    public String getAuthor() {
        return AUTHOR;
    }

    public String getVersion() {
        return VERSION;
    }

    public String getName() {
        return NAME;
    }

    public ExtensionType getTYPE() {
        return TYPE;
    }

    public String getTooltip() {
        return TOOLTIP;
    }

    public String getDescription() {
        return DESCRIPTION;
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
