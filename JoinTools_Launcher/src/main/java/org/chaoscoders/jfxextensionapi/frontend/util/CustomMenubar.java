package org.chaoscoders.jfxextensionapi.frontend.util;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import org.chaoscoders.jfxextensionapi.api.util.GuiManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Admin on 09.08.2017.
 */
public class CustomMenubar extends Parent {

    private boolean isHome = true;
    private ImageView homeImg;
    private ImageView powerImg;
    private StackPane homePane;
    private StackPane powerPane;
    public boolean isHome() {
        return isHome;
    }

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


    public CustomMenubar(double width) {
        homeImg = new ImageView(GuiManager.image_home);
        powerImg = new ImageView(GuiManager.image_power);
        homePane = new StackPane(homeImg);
        powerPane = new StackPane(powerImg);

        HBox pane = new HBox();
        pane.setPrefWidth(width);
        Label time = new Label("00:00:00");


        pane.setAlignment(Pos.CENTER);
        System.out.println(pane.getPrefWidth());
        pane.setPrefHeight(100);

        time.setFont(Font.font(45));
        time.setAlignment(Pos.CENTER);
        time.setTextAlignment(TextAlignment.CENTER);



        pane.setSpacing(width / 3 );

        pane.getChildren().addAll(powerPane, time, homePane);
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
