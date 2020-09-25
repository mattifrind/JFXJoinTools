package org.chaoscoders.jfxextensionapi.frontend.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionType;
import org.chaoscoders.jfxextensionapi.frontend.loader.ExtensionLoader;
import org.chaoscoders.jfxextensionapi.frontend.loader.WidgetLoader;

import java.util.List;
import java.util.stream.Collectors;

public class WidgetsGrid extends GridPane {

    private Stage primaryStage;
    private ExtensionType type;

    private static final int WIDGET_WIDTH = 180;

    public WidgetsGrid(Stage primaryStage, ExtensionType type) {
        this.type = type;
        this.primaryStage = primaryStage;
        this.setPadding(new Insets(30));
        updateWidgets();

        //reorganize widgets on Size change
        primaryStage.widthProperty().addListener((observableValue, number, t1) -> {
            updateWidgets();
        });
    }

    private void updateWidgets() {
        this.getChildren().clear();
        int computedColumnWidth = getComputedColumnCount();
        for (int i = 0; i < getWidgets().size(); i++) {
            this.add(getWidgets().get(i),i % computedColumnWidth,i / computedColumnWidth);
            GridPane.setHgrow(getWidgets().get(i), Priority.ALWAYS);
        }
    }

    private List<Widget> getWidgets() {
        return WidgetLoader.getTypedWidgets(type);
    }

    private int getComputedColumnCount() {
        return Math.max((int) Math.round((primaryStage.getWidth() - 150) / WIDGET_WIDTH), 1);
    }
}
