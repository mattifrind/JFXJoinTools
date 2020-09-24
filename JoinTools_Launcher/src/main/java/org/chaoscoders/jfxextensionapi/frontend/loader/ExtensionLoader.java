package org.chaoscoders.jfxextensionapi.frontend.loader;

import javafx.scene.image.Image;
import org.chaoscoders.jfxextensionapi.api.JavaFXExtension;
import org.chaoscoders.jfxextensionapi.api.extensioninfo.ExtensionInfo;
import org.chaoscoders.jfxextensionapi.api.settings.Settings;
import org.chaoscoders.jfxextensionapi.api.util.CustomFXMLLoader;
import org.chaoscoders.jfxextensionapi.frontend.Main;
import org.chaoscoders.jfxextensionapi.frontend.util.TempFileManager;
import org.chaoscoders.jfxextensionapi.frontend.util.Widget;
import org.chaoscoders.jfxextensionapi.frontend.util.exceptions.InvalidPluginYMLException;
import org.chaoscoders.jfxextensionapi.frontend.util.exceptions.PluginUUIDNotResolvedException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ExtensionLoader {

    private static ArrayList<JavaFXExtension> plugins;

    private static HashMap<UUID, Settings> extensionSettings;
    private static HashMap<UUID, ExtensionInfo> extensionInfos;
    private static HashMap<UUID, Image> extensionIcons;
    private static HashMap<UUID, Widget> extensionWidgets;


    //public methods
    public static ArrayList<JavaFXExtension> getPlugins(){
        return plugins;
    }

    public static Settings getExtensionSettings(UUID pluginUUID){
        init();
        return extensionSettings.get(pluginUUID);
    }

    public static ExtensionInfo getExtensionInfo(UUID pluginUUID){
        init();
        return extensionInfos.get(pluginUUID);
    }

    public static Image getExtensionIcon(UUID pluginUUID){
        init();
        return extensionIcons.get(pluginUUID);
    }

    public static HashMap<UUID, Widget> getExtensionWidgets(){
        init();
        return extensionWidgets;
    }

    public static Widget getExtensionWidget(UUID pluginUUID){
        return extensionWidgets.get(pluginUUID);
    }

    public static void loadPlugins(){
        init();

        File plDir = new File(Main.pluginFolder);
        if(! plDir.exists()) {
            plDir.mkdirs();
        }
        for(File jar : Objects.requireNonNull((new File(Main.pluginFolder)).listFiles())){
            if (jar.isFile() && jar.getName().endsWith(".jar")) {
                try {
                    UUID pluginUUID = UUID.randomUUID();
                    TempFileManager.createTmpDir(pluginUUID);

                    String path = ConfigLoader.getConfigParameter(jar.toURL(), pluginUUID);

                    Class<?> pluginMainClass = getClassFromPath(jar, path);
                    Class<?> constructorParam = UUID.class;

                    CustomFXMLLoader.loadFXMLFiles(jar.toURL(), pluginUUID);


                    if(pluginMainClass.getConstructor(constructorParam).newInstance(pluginUUID) instanceof JavaFXExtension){
                        JavaFXExtension extension = (JavaFXExtension) pluginMainClass.
                                getConstructor(constructorParam).newInstance(pluginUUID);

                        Method getSettingsMethod = pluginMainClass.getMethod("getSettings");
                        Method getInfoMethod = pluginMainClass.getMethod("getInfo");

                        extensionSettings.put(pluginUUID, (Settings) getSettingsMethod.invoke(extension));
                        extensionInfos.put(pluginUUID, (ExtensionInfo) getInfoMethod.invoke(extension));
                        extensionIcons.put(pluginUUID, getExtensionIcon(jar.toURL(), pluginUUID));
                        extensionWidgets.put(pluginUUID, new Widget(pluginUUID));

                        ExtensionLoader.plugins.add(extension);
                    }


                }catch(IOException | NoSuchMethodException | IllegalAccessException |
                        InstantiationException | InvocationTargetException |
                        InvalidPluginYMLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static JavaFXExtension resolvePluginID(UUID pluginID){
        try{
            for(JavaFXExtension javaFXExtension : plugins){
                if(javaFXExtension.getPluginUUID() == pluginID){
                    return javaFXExtension;
                }
            }
            throw new PluginUUIDNotResolvedException("Couldn't resolve Plugin UUID.");
        }catch (PluginUUIDNotResolvedException e1){
            return null;
        }
    }


    //private internal methods
    private static void init(){
        if (plugins == null){
            plugins = new ArrayList<>();
        }
        if(extensionWidgets == null){
            extensionWidgets = new HashMap<>();
        }
        if(extensionIcons == null){
            extensionIcons = new HashMap<>();
        }
        if(extensionSettings == null){
            extensionSettings = new HashMap<>();
        }
        if(extensionInfos == null){
            extensionInfos = new HashMap<>();
        }
    }

    private static Image getExtensionIcon(URL jar, UUID pluginUUID){
        //Icon aus jar lesen ansonsten standard icon aus res ordner zurückgeben
        String path = "";
        try{
            ZipInputStream zipIs = new ZipInputStream(jar.openStream());
            ZipEntry e;
            while((e = zipIs.getNextEntry()) != null) {
                String name = e.getName();
                ZipFile zf = new ZipFile(jar.getFile());
                String iconname = jar.toString().substring(jar.toString().lastIndexOf("/") + 1, jar.toString().length() - 4);
                path = Main.getTmpdir(pluginUUID) + "\\icon_" + iconname + ".png";
                InputStream is = zf.getInputStream(e);
                if (name.equalsIgnoreCase("icon.png")) {
                    //icon gefunden
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

    private static Class<?> getClassFromPath(File jar, String classpath) throws InvalidPluginYMLException {
        try{
            ClassLoader loader = URLClassLoader.newInstance(
                    new URL[] { jar.toURL() },
                    ExtensionLoader.class.getClassLoader()
            );
            return Class.forName(classpath, true, loader);
        }catch (MalformedURLException | ClassNotFoundException e){
            throw new InvalidPluginYMLException("Invalid path to main class " + jar.getName() + ".", e);
        }
    }
}
