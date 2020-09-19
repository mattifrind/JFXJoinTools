package org.chaoscoders.jfxextensionapi.api.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import org.chaoscoders.jfxextensionapi.frontend.Main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class CustomFXMLLoader {

    //LayoutPfad aus plugin.yml - Reeller Pfad im TMP Verzeichnis
    private HashMap<String, Path> extensionLayoutPaths;

    public CustomFXMLLoader(){
        this.extensionLayoutPaths = new HashMap<>();
    }

    public Path getPath(String layoutPath){
        return extensionLayoutPaths.get(layoutPath);
    }

    public static Path getExtensionIcon(URL jar, String layoutPath, UUID pluginUUID){

        String path = "";
        try{
            ZipInputStream zipIs = new ZipInputStream(jar.openStream());
            ZipEntry e;
            while((e = zipIs.getNextEntry()) != null) {

                ZipFile zf = new ZipFile(jar.getFile());
                ZipEntry z = zf.getEntry(layoutPath.replace(".", "/") + "/Main.fxml");
                if(e.getName().contains(layoutPath.replace(".", "/"))
                        && e.getName().endsWith(".fxml")){
                    String name = e.getName().substring(e.getName().lastIndexOf("/"));
                    path = Main.getTmpdir(pluginUUID) + "\\" + name;
                    InputStream is = zf.getInputStream(e);
                    Files.copy(is, Paths.get(path));
                }


            }
        }catch (IOException e ){
            e.printStackTrace();
        }
        return null;
    }
}
