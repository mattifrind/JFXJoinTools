# JFXJoinTools

Willkommen zu unserem JavaFX Learning Repository. Der Setup ist nicht ganz so trivial, aber wir haben versucht alle Schritte möglichst vollständig aufzuschreiben.

## Getting Started
- IntelliJ Idea 2020 installieren
- Git installieren https://git-scm.com/
- Download und Auspacken Java 15 - https://jdk.java.net/15/ (zip) → zu euren Java Versionen kopieren. Unter Windows: C:\Program Files\Java
- Download und Auspacken JavaFX 15 - https://gluonhq.com/products/javafx/ (sdk) → in irgendnen Ordner kopieren wo ihr es wieder findet. Zum Beispiel wieder C:\Program Files\Java
- GitHub Account erstellen
- Neues Projekt "From Version Control" → GitHub Account auswählen → JFXJoinTools auswählen → clone
- File → Project Structure → Libraries → lib löschen → + → JavaFX lib Ordner angeben → Launcher und Sample auswählen → ok
- JoinTools_Launcher auswählen 
    - runterklicken nach src/org.chaoscoders.jfxextensionapi/frontend → Rechtsklick auf Main → Run Main
    - Rechts oben auf "Main" klicken (zwischen grünem Hammer und Pfeil) → Edit Configurations
        - Unter VM Options: --module-path "path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml
        - Ganz oben Namen ändern (z.B. "Main Launcher")
    - das Programm startet nun über den grünen Pfeil
- JoinTools_Sample auswählen → runterklicken nach src/org.chaoscoders.firstextension → Rechtsklick auf Main → Run Main
    - Rechts oben auf "Main" klicken (zwischen grünem Hammer und Pfeil) → Edit Configurations
        - Unter VM Options: --module-path "path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml
        - Ganz oben Namen ändern (z.B. "Main Sample")
    - das Programm startet nun über den grünen Pfeil
> ***Wichtig***: Das Sample Projekt soll nicht verändert werden. Wenn ihr ein eigenes Projekt beginnen wollt, macht bei "Eigenes Projekt erstellen" weiter.
### Eigenes Projekt erstellen
- 
## Wichtige Dateien im JoinTools_Sample
- Main.java - startet die Anwendung standalone. Lädt das Layout aus der Main.fxml. (braucht ihr nix tun)
- ExtensionMain.java - startet die Anwendung als Plugin (getRoot(), getSettings(), getInfo() entsprechend anpassen)
- Main.fxml - Mit dem SceneBuilder könnt ihr dort euer Layout zusammenbasteln
- MainController.java - Verleiht dem Layout seine Funktionalität. Gehört zur Main.fxml
- icon.png - durch euer eigenes ersetzen.
- plugin.yml - Metainformationen für euer Plugin eintragen
