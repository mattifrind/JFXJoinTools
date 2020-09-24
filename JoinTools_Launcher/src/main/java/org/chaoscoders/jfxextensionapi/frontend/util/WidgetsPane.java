package org.chaoscoders.jfxextensionapi.frontend.util;

import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionType;


public class WidgetsPane extends Accordion {

    public WidgetsPane(Stage primaryStage) {
        TitledPane utilPane = new TitledPane();
        Label lblUtil = new Label("Utils");
        lblUtil.setFont(Font.font(25));
        utilPane.setGraphic(lblUtil);
        ScrollPane utilScrollPane = new ScrollPane(new WidgetsGrid(primaryStage, ExtensionType.UTIL));
        utilScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        utilScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        utilPane.setContent(utilScrollPane);

        TitledPane mathPane = new TitledPane();
        Label lblMath = new Label("Math");
        lblMath.setFont(Font.font(25));
        mathPane.setGraphic(lblMath);
        ScrollPane mathScrollPane = new ScrollPane(new WidgetsGrid(primaryStage, ExtensionType.MATH));
        mathScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mathScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        mathPane.setContent(mathScrollPane);

        TitledPane gamePane = new TitledPane();
        Label lblGame = new Label("Games");
        lblGame.setFont(Font.font(25));
        gamePane.setGraphic(lblGame);
        ScrollPane gamesScrollPane = new ScrollPane(new WidgetsGrid(primaryStage, ExtensionType.GAME));
        gamesScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gamesScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        gamePane.setContent(gamesScrollPane);

        this.getPanes().addAll(utilPane, mathPane, gamePane);
        this.setPadding(new Insets(10));
        this.setExpandedPane(utilPane);

    }
}
