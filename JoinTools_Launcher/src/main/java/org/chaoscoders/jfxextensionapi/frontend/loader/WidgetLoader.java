package org.chaoscoders.jfxextensionapi.frontend.loader;

import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionType;
import org.chaoscoders.jfxextensionapi.frontend.util.Widget;

import java.util.List;
import java.util.stream.Collectors;

public class WidgetLoader {
    private static List<Widget> widgets;

    public static List<Widget> getWidgets() {
        return widgets;
    }

    public static List<Widget> getTypedWidgets(ExtensionType type) {
        return widgets.stream().filter(wid -> wid.getType().equals(type)).collect(Collectors.toList());
    }

    public static void load() {
        widgets = ExtensionLoader.getPlugins().stream()
                .map(plugin ->
                        ExtensionLoader.getExtensionWidget(plugin.getPluginUUID()))
                .collect(Collectors.toList());
    };
}
