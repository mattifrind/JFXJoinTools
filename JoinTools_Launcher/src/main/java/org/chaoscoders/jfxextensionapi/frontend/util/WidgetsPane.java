package org.chaoscoders.jfxextensionapi.frontend.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.chaoscoders.jfxextensionapi.frontend.loader.ExtensionLoader;

import java.util.List;
import java.util.stream.Collectors;

public class WidgetsPane extends GridPane {

    private List<Widget> widgets;
    private Stage primaryStage;

    private static final int WIDGET_WIDTH = 180;

    public WidgetsPane(Stage primaryStage) {
        //load all plugin widgets into the home node
        this.primaryStage = primaryStage;
        this.setPadding(new Insets(30));
        //this.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0))));
        widgets = ExtensionLoader.getPlugins().stream()
                .map(plugin ->
                        ExtensionLoader.getExtensionWidget(plugin.getPluginUUID()))
                .collect(Collectors.toList());
        updateWidgets();

        primaryStage.widthProperty().addListener((observableValue, number, t1) -> {
            updateWidgets();
        });
    }

    private void updateWidgets() {
        this.getChildren().clear();
        int computedColumnWidth = getComputedColumnCount();
        for (int i = 0; i < widgets.size(); i++) {
            this.add(widgets.get(i),i % computedColumnWidth,i / computedColumnWidth);
            GridPane.setHgrow(widgets.get(i), Priority.ALWAYS);
        }
    }

    private int getComputedColumnCount() {
        return Math.max((int) Math.round((primaryStage.getWidth() - 150) / WIDGET_WIDTH), 1);
    }
}
