package org.chaoscoders.jfxextensionapi.frontend;

import javafx.scene.image.Image;
import org.chaoscoders.jfxextensionapi.frontend.Main;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Helper {

    public Image getExtensionIcon(URL jar){
        //Icon aus jar lesen ansonsten standard icon aus res ordner zurückgeben
        String path = "";
        try{
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            while(true) {
                ZipEntry e = zip.getNextEntry();
                if (e == null) {
                    break;
                }
                String name = e.getName();
                ZipFile zf = new ZipFile(jar.getFile());
                String iconname = jar.toString().substring(jar.toString().lastIndexOf("/") + 1, jar.toString().length() - 4);
                path = Main.tmpdir + "icon_" + iconname + ".png";
                InputStream is = zf.getInputStream(e);
                if (name.equalsIgnoreCase("icon.png")) {
                    //plugin.yml gefunden
                    try {
                        Files.delete(Paths.get(path));
                    } catch (NoSuchFileException eFn) {
                        //....
                    }
                    Files.copy(is, Paths.get(path));
                }
            }
        }catch (IOException e ){
            e.printStackTrace();
        }
        if(path.equalsIgnoreCase("")){
            return new Image("/iconrasp64.png", true);
        }else{
            System.out.println("file:" + path);
            return new Image("file:" + path);
        }
    }



    public Class<?> getClassFromPath(File jar, String classpath){

        try{
            ClassLoader loader = URLClassLoader.newInstance(
                    new URL[] { jar.toURL() },
                    getClass().getClassLoader()
            );
            Class<?> clazz = Class.forName(classpath, true, loader);
            return clazz;
        }catch (MalformedURLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("Klasse nicht gefunden!");
        return null;
    }

}
