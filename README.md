# JFXJoinTools

Willkommen zu unserem JavaFX Learning Repository. Der Setup ist nicht ganz so trivial, aber wir haben versucht alle Schritte möglichst vollständig aufzuschreiben.

## Getting Started
- Git installieren https://git-scm.com/
- Download und Auspacken Java 15 - https://jdk.java.net/15/ (zip) → zu euren Java Versionen kopieren. Unter Windows: C:\Program Files\Java
- Download und Auspacken JavaFX 15 - https://gluonhq.com/products/javafx/ (sdk) → in irgendnen Ordner kopieren wo ihr es wieder findet. Zum Beispiel wieder C:\Program Files\Java
- Projektordner erstellen. Rechtsklick in dem Ordner → Git Bash here → git clone https://github.com/mattifrind/JFXJoinTools.git
- Mit JoinTools_Sample: 
  - Projekt in IntelliJ öffnen
  - File → Project Structure → Project → Project SDK → New → JDK → Java 15 Ordner auswählen (C:\Program Files\Java\jdk-15)
  - File → Project Structure → Libraries → “lib” → Library Verbindung wiederherstellen. Auf Minus klicken, auf Plus klicken. Ordner zu JavaFX 15 auswählen und hinzufügen
  - File → Project Structure → Project → VM Options: Pfad zu JavaFX lib anpassen (also z.B. C:\Program Files\Java\openjfx-15_windows-x64_bin-sdk\javafx-sdk-15\lib)
  - Rechtsklick auf “Main” → Run Main.main(). Das wirft vermutlich nen Fehler. Rechts oben auf den Dropdown mit "Main" klicken → Edit Configurations → In die VM Options eintragen: "--module-path "path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml" (ab jetzt kannst du rechts oben auf den grünen Haken zum Starten klicken)
  - File → Project Structure → Artifacts → Output directory → auf Ordner “plugins” in Repository Ordner umlegen
- Mit JoinTools_Launcher:
  - Projekt in IntelliJ öffnen
  - File → Project Structure → Libraries → “lib” → Library Verbindung wiederherstellen. Auf Minus klicken, auf Plus klicken. Ordner zu JavaFX 15 auswählen und hinzufügen
in den frontend Ordner gehen → Rechtsklick auf “Main” → Run Main.main() (ab jetzt kannst du rechts oben auf den grünen Haken zum Starten klicken)

## Wichtige Dateien
- Main.java - startet die Anwendung standalone. Lädt das Layout aus der Main.fxml. (braucht ihr nix tun)
- ExtensionMain.java - startet die Anwendung als Plugin (getRoot(), getSettings(), getInfo() entsprechend anpassen)
- Main.fxml - Mit dem SceneBuilder könnt ihr dort euer Layout zusammenbasteln
- MainController.java - Verleiht dem Layout seine Funktionalität. Gehört zur Main.fxml
- icon.png - durch euer eigenes ersetzen.
- plugin.yml - Metainformationen für euer Plugin eintragen
