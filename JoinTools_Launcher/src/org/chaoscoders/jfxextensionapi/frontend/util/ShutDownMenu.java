package org.chaoscoders.jfxextensionapi.frontend.util;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.chaoscoders.jfxextensionapi.api.util.GuiManager;


/**
 * Created by Admin on 09.08.2017.
 */
public class ShutDownMenu extends Parent {

    public ShutDownMenu(){

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        hBox.setSpacing(50);

        ImageView backImg = new ImageView(GuiManager.image_back);
        backImg.setCursor(Cursor.HAND);

        backImg.setOnMouseClicked(event ->  {
            if(GuiManager.lastScreen != null){
                GuiManager.root.setCenter(GuiManager.lastScreen);
            }else{
                GuiManager.showHomeScreen();
            }
        });

        ImageView shutdownImg = new ImageView(GuiManager.image_shutdown);

        shutdownImg.setCursor(Cursor.HAND);
        shutdownImg.setOnMouseClicked(event -> {
            ((Stage) GuiManager.root.getScene().getWindow()).close();
            //PiStation.getInstance().stop();
        });

        ImageView restartImg = new ImageView(GuiManager.image_restart);
        restartImg.setCursor(Cursor.HAND);
        restartImg.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Feature noch nicht verfügbar!");
            alert.show();
        });
        hBox.getChildren().addAll(backImg, shutdownImg, restartImg);

        this.getChildren().addAll(hBox);
    }

}
