package org.chaoscoders.jfxextensionapi.frontend.util;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.chaoscoders.jfxextensionapi.api.util.GuiManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Admin on 09.08.2017.
 */
public class CustomMenubar extends Pane {

    private boolean isHome = true;
    private ImageView homeImg;
    private ImageView powerImg;
    private StackPane homePane;
    private StackPane powerPane;

    public void setHome(boolean isHome) {
        this.isHome = isHome;
        if(isHome){
            homeImg.setOpacity(0.5);
            homeImg.setCursor(Cursor.DEFAULT);
            homePane.setCursor(Cursor.DEFAULT);
        }else{
            homeImg.setOpacity(1.0);
            homeImg.setCursor(Cursor.HAND);
            homePane.setCursor(Cursor.HAND);
        }
    }


    public CustomMenubar(Stage stage) {
        homeImg = new ImageView(GuiManager.image_home);
        powerImg = new ImageView(GuiManager.image_power);
        homePane = new StackPane(homeImg);
        powerPane = new StackPane(powerImg);

        homePane.setAlignment(Pos.CENTER);
        powerPane.setAlignment(Pos.CENTER);


        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPrefHeight(100);
        pane.prefWidthProperty().bind(stage.widthProperty().subtract(20));
        pane.setPadding(new Insets(10));
        //this.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0))));

        Label time = new Label("00:00:00");
        time.setFont(Font.font(45));
        time.setAlignment(Pos.CENTER);
        time.setTextAlignment(TextAlignment.CENTER);

        pane.add(powerPane, 0,0);
        GridPane.setHgrow(powerPane, Priority.NEVER);

        pane.add(time, 1, 0);
        GridPane.setHgrow(time, Priority.ALWAYS);
        GridPane.setHalignment(time, HPos.CENTER);

        pane.add(homePane,2,0);
        GridPane.setHgrow(homePane, Priority.NEVER);

        this.getChildren().addAll(pane);

        //ausgrauen
        homeImg.setOpacity(0.5);
        homePane.setCursor(Cursor.HAND);
        homePane.setOnMouseClicked(ev -> {
            if(!this.isHome){
                GuiManager.showHomeScreen();
            }
        });

        powerPane.setCursor(Cursor.HAND);

        powerPane.setOnMouseClicked(ev -> {
            GuiManager.showPowerOffScreen();
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            time.setText(sdf.format(cal.getTime()));
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }

    public void hidePowerButton() {
        powerImg.setVisible(false);
        powerPane.setVisible(false);
    }

    public void showPowerButton() {
        powerImg.setVisible(true);
        powerPane.setVisible(true);
    }

}
