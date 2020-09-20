package org.chaoscoders.jfxextensionapi.api.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.chaoscoders.jfxextensionapi.api.JavaFXExtension;
import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionInfo;
import org.chaoscoders.jfxextensionapi.api.settings.Settings;
import org.chaoscoders.jfxextensionapi.frontend.Main;
import org.chaoscoders.jfxextensionapi.frontend.loader.ExtensionLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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

    private Node returnFXMLFile(String name, JavaFXExtension root, Object rootPane, Object controller){
        String path;
        Node result = null;
        UUID pluginUUID = root.getPluginUUID();
        path = Main.getTmpdir(pluginUUID) + "\\" + name;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File(path).toURL());
            fxmlLoader.setRoot(rootPane);
            if(controller != null){
                fxmlLoader.setController(controller);
            }
            result = fxmlLoader.load();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Node loadFXMLFile(String name, JavaFXExtension root, Object rootPane, Object controller){
        return new CustomFXMLLoader().returnFXMLFile(name, root, rootPane, controller);
    }

    public static Node loadFXMLFile(String name, JavaFXExtension root, Object rootPane){
        return new CustomFXMLLoader().returnFXMLFile(name, root, rootPane, null);
    }

    public static void loadFXMLFiles(URL jar, String layoutPath, UUID pluginUUID){

        String path = "";
        try{
            ZipInputStream zipIs = new ZipInputStream(jar.openStream());
            ZipEntry e;
            while((e = zipIs.getNextEntry()) != null) {

                ZipFile zf = new ZipFile(jar.getFile());
                if(e.getName().contains(layoutPath.replace(".", "/"))
                        && e.getName().endsWith(".fxml")){
                    String name = e.getName().substring(e.getName().lastIndexOf("/"));
                    path = Main.getTmpdir(pluginUUID) + "\\" + name.substring(1);
                    //path = System.getProperty("java.io.tmpdir") + "JoinTools\\" + pluginUUID.toString() + "\\" + name.substring(1);
                    System.out.println(path);
                    File dir = new File(path);
                    (new File(dir.getParent())).mkdirs();
                    InputStream is = zf.getInputStream(e);
                    Files.copy(is, Paths.get(path));
                }


            }
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
