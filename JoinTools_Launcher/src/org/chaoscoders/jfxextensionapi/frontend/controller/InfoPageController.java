package org.chaoscoders.jfxextensionapi.frontend.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class InfoPageController {

    private final String versionText;
    private final String authorText;
    private final String descriptionText;
    private final String pluginText;

    @FXML
    public Label pluginLabel;

    @FXML
    public Label authorLabel;

    @FXML
    public Label versionLabel;

    @FXML
    public Text descriptionTextField;

    public InfoPageController(String versionText, String authorText, String descriptionText, String pluginText) {
        this.versionText = versionText;
        this.authorText = authorText;
        this.descriptionText = descriptionText;
        this.pluginText = pluginText;
    }

    @FXML
    public void initialize(){
        pluginLabel.setText(this.pluginText);
        authorLabel.setText("Entwickler: " + this.authorText);
        versionLabel.setText("Version: " + this.versionText);
        descriptionTextField.setText(this.descriptionText);
    }


}
