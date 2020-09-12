package org.chaoscoders.jfxextensionapi.frontend.util;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import org.chaoscoders.jfxextensionapi.api.util.GuiManager;

public class Widget extends Parent implements Cloneable {

    private Rectangle background;
    private Rectangle backgroundHighlighter;
    private Rectangle backgroundHighlighterSlider;
    private Color colorHighlighter;
    private Color backgroundColor;
    private Label extensionNameLabel;
    private Label settingsLabel;
    private Label infoLabel;
    private ImageView icon;
    private String tooltip;
    private String name;
    private Color textColor;
    private int layoutX;
    private int layoutY;
    private int widgetID;



    @Override
    public Object clone() {
        Widget w = new Widget(this.layoutX, this.layoutY, this.extensionNameLabel.getText(), icon.getImage()
        , this.tooltip, this.colorHighlighter, this.backgroundColor, this.textColor, this.widgetID);
        return w;
    }

    private void performOnEnter(){

        //bg colorHighlighter
        FillTransition fillTransition = new FillTransition(Duration.seconds(0.25));
        fillTransition.setFromValue(Color.web(this.backgroundColor.toString(), 0.8));
        fillTransition.setToValue(Color.web(this.backgroundColor.toString()));
        fillTransition.setShape(background);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5));
        fadeTransition.setNode(infoLabel);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(0.5));
        fadeTransition2.setNode(settingsLabel);
        fadeTransition2.setFromValue(0.0);
        fadeTransition2.setToValue(1.0);

        Timeline tl = new Timeline();
        tl.setAutoReverse(false);

        KeyValue kv = new KeyValue(backgroundHighlighterSlider.heightProperty(), 50);
        KeyFrame kf = new KeyFrame(Duration.millis(250), kv);
        tl.getKeyFrames().addAll(kf);

        ParallelTransition parallelTransition = new ParallelTransition(fillTransition, fadeTransition, fadeTransition2, tl);
        settingsLabel.setVisible(true);
        infoLabel.setVisible(true);
        parallelTransition.play();


    }

    private void performonExit(){

        FillTransition fillTransition = new FillTransition(Duration.seconds(0.25));
        fillTransition.setFromValue(this.backgroundColor);
        fillTransition.setToValue(Color.web(this.backgroundColor.toString(), 0.8));
        fillTransition.setShape(background);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5));
        fadeTransition.setNode(infoLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(0.5));
        fadeTransition2.setNode(settingsLabel);
        fadeTransition2.setFromValue(1.0);
        fadeTransition2.setToValue(0.0);

        Timeline tl = new Timeline();
        tl.setAutoReverse(false);

        KeyValue kv = new KeyValue(backgroundHighlighterSlider.heightProperty(), 0);
        KeyFrame kf = new KeyFrame(Duration.millis(250), kv);
        tl.getKeyFrames().addAll(kf);

        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, fillTransition, tl);
        parallelTransition.play();
        settingsLabel.setVisible(false);
        infoLabel.setVisible(false);
    }

    public Widget(int layoutX, int layoutY, String extensionName, Image iconImage, String toolTip, Color hightlight, Color backgroundColor, Color textColor, int widgetID) {
        this.widgetID = widgetID;
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.backgroundColor = backgroundColor;
        this.tooltip = toolTip;
        this.name = extensionName;
        this.textColor = textColor;

        colorHighlighter = hightlight;
        background = new Rectangle(170, 220);
        background.setFill(Color.web(backgroundColor.toString(), 0.8));
        background.setLayoutX(layoutX);
        background.setLayoutY(layoutY);
        background.setArcHeight(8);
        background.setArcWidth(8);
        background.setStrokeWidth(0);

        backgroundHighlighterSlider = new Rectangle(170, 0);

        backgroundHighlighter = new Rectangle(170, 80);
        backgroundHighlighter.setFill(colorHighlighter);
        backgroundHighlighter.setLayoutX(layoutX);
        backgroundHighlighter.setLayoutY(layoutY);
        backgroundHighlighter.setArcHeight(8);
        backgroundHighlighter.setArcWidth(8);
        backgroundHighlighter.setClip(backgroundHighlighterSlider);
        backgroundHighlighter.setCursor(Cursor.HAND);

        extensionNameLabel = new Label(extensionName);
        extensionNameLabel.setTextFill(textColor);
        extensionNameLabel.setAlignment(Pos.CENTER);
        extensionNameLabel.setTextAlignment(TextAlignment.CENTER);
        extensionNameLabel.setPrefSize(170, 20);
        extensionNameLabel.setFont(Font.font(20));
        extensionNameLabel.setLayoutX(layoutX);
        extensionNameLabel.setLayoutY(layoutY + 12);
        extensionNameLabel.setCursor(Cursor.HAND);

        Tooltip infoTT = new Tooltip(toolTip);
        this.extensionNameLabel.setTooltip(infoTT);

        infoLabel = new Label("Info");
        infoLabel.setTextFill(textColor);
        infoLabel.setAlignment(Pos.CENTER);
        infoLabel.setTextAlignment(TextAlignment.CENTER);
        infoLabel.setPrefSize(170, 17);
        infoLabel.setLayoutX(layoutX);
        infoLabel.setLayoutY(layoutY + 170);
        infoLabel.setVisible(false);
        infoLabel.setCursor(Cursor.HAND);

        settingsLabel = new Label("Settings");
        settingsLabel.setTextFill(textColor);
        settingsLabel.setAlignment(Pos.CENTER);
        settingsLabel.setTextAlignment(TextAlignment.CENTER);
        settingsLabel.setPrefSize(170, 17);
        settingsLabel.setLayoutX(layoutX);
        settingsLabel.setLayoutY(layoutY + 190);
        settingsLabel.setVisible(false);
        settingsLabel.setCursor(Cursor.HAND);

        icon = new ImageView(iconImage);
        icon.setFitWidth(64);
        icon.setFitHeight(64);
        icon.setLayoutX(layoutX + 53);
        icon.setLayoutY(layoutY + 80);

        getChildren().addAll(background, backgroundHighlighter, extensionNameLabel, icon, infoLabel, settingsLabel);

        //TODO: underline animieren - vllt zwei linien die nach links
        // und rechts sich ausdehnen und unter dem label liegen?
        infoLabel.setOnMouseEntered(event -> {
            infoLabel.setUnderline(true);
        });

        infoLabel.setOnMouseExited(event -> {
            infoLabel.setUnderline(false);
        });

        settingsLabel.setOnMouseEntered(event -> {
            settingsLabel.setUnderline(true);
        });

        settingsLabel.setOnMouseExited(event -> {
            settingsLabel.setUnderline(false);
        });

        settingsLabel.setOnMouseClicked(event -> {
            GuiManager.openSettingsPage(widgetID);
        });

        infoLabel.setOnMouseClicked(event -> {
            GuiManager.openInfoPage(widgetID);
        });

        this.setOnMouseEntered(event -> {
            performOnEnter();
        });

        this.setOnMouseExited(event -> {
            performonExit();
        });

        this.backgroundHighlighterSlider.setOnMouseClicked(event -> {
            GuiManager.openPlugin(widgetID);
        });

        this.extensionNameLabel.setOnMouseClicked(event -> {
            GuiManager.openPlugin(widgetID);
        });

    }

}