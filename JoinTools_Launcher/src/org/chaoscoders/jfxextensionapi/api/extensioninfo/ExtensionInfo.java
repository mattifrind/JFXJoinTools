package org.chaoscoders.jfxextensionapi.api.extensioninfo;

public class ExtensionInfo {
    //TODO: Info Page... ähnlich wie settings machen aber informationen gehen nur vom Plugin zur API
    private final String author;
    private final String version;
    private final String name;
    private final String tooltip;
    private final String mainclass;
    private final String description;

    public ExtensionInfo(String author, String version, String name, String tooltip, String mainclass, String description) {
        this.author = author;
        this.version = version;
        this.name = name;
        this.tooltip = tooltip;
        this.mainclass = mainclass;
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getTooltip() {
        return tooltip;
    }

    public String getMainclass() {
        return mainclass;
    }

    public String getDescription() {
        return description;
    }
}
