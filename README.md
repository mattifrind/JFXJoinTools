# JFXJoinTools

Willkommen zu unserem JavaFX Learning Repository. Der Setup ist nicht ganz so trivial, aber wir haben versucht alle Schritte möglichst vollständig aufzuschreiben.

## Getting Started
- IntelliJ Idea 2020 installieren
- Git installieren https://git-scm.com/
- Scene Builder installieren https://gluonhq.com/products/scene-builder/
- Download und Auspacken Java 15 - https://jdk.java.net/15/ (zip) → zu euren Java Versionen kopieren. Unter Windows: C:\Program Files\Java
- GitHub Account erstellen https://github.com/
- IntelliJ öffnen
    - Neues Projekt "From Version Control" → GitHub Account auswählen → JFXJoinTools auswählen → clone
    - Rechts oben Configurations Dropdown öffnen → im Fenster + → Maven auswählen
        - Working directory: ganz rechts auf directory icon (blau) klicken und JFXJoinTools_Launcher auswählen
        - Command line: `javafx:run`
        - Resolve workspace artifacts auswählen
        - Schritte wiederholen für Debug-Konfiguration, allerdings mit `javafx:run@debug`
        - Schritte wiederholen für JFXJoinTools_Sample
> ***Wichtig***: Das Sample Projekt soll nicht verändert werden. Wenn ihr ein eigenes Projekt beginnen wollt, macht bei "Eigenes Projekt erstellen" weiter.
### Eigenes Plugin/Tool erstellen
- JoinTools_Sample auswählen → Strg + C → JFXJoinTools auswählen → Strg + V → Neuen Namen vergeben (z.B. JoinTools_Calculator) → Ok
- Add Files to Git "ok"
- File → Project Structure → Modules
    - JoinTools_Sample auswählen und duplizieren (kleines Icon drüber) → Module Name "JoinTools_Calculator" angeben (wie der Ordnername)
    - JoinTools_Calculator auswählen → "src" Ordner auswählen und als "Sources" festlegen
- Im Ordner JoinTools_Calculator: pom.xml öffnen → artifactId von "JoinTools_Sample" zu "JoinTools_Calculator" o.ä. ändern#
- Im Ordner JFXJoinTools: pom.xml öffnen → im modules Tag das neue module einfügen (z.B. `<module>JoinTools_Calculator</module>`)
```
<modules>
    <module>JoinTools_Launcher</module>
    <module>JoinTools_Sample</module>
</modules>
```
## Wichtige Dateien im JoinTools_Sample
- Main.java - startet die Anwendung standalone. Lädt das Layout aus der Main.fxml. (braucht ihr nix tun)
- ExtensionMain.java - startet die Anwendung als Plugin (getRoot(), getSettings(), getInfo() entsprechend anpassen)
- Main.fxml (resources Ordner) - Mit dem SceneBuilder könnt ihr dort euer Layout zusammenbasteln
- MainController.java - Verleiht dem Layout seine Funktionalität. Gehört zur Main.fxml
- icon.png - durch euer eigenes ersetzen.
- plugin.yml - Metainformationen für euer Plugin eintragen

## Maven Befehle
####Im Hauptmodul (JFXJoinTools)
Alles löschen und sauber installieren. Dadurch werden alle Jars neu gebaut und in den Plugin Ordner geschrieben:
```
mvn clean install
```
Es werden außerdem alle Tests ausgeführt
####In Plugins (z.B. JoinTools_Sample)
Ausführen/Debuggen der einzelnen Programme:
```
mvn javafx:run
mvn javafx:run@debug
```