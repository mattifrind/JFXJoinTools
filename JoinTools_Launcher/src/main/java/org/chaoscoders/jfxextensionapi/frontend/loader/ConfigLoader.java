package org.chaoscoders.jfxextensionapi.frontend.loader;

import org.chaoscoders.jfxextensionapi.frontend.Main;
import org.chaoscoders.jfxextensionapi.frontend.util.exceptions.InvalidPluginYMLException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ConfigLoader {

    private static HashMap<UUID, HashMap<String, String>> pluginConfigs;

    private static void init(){
        if(pluginConfigs == null)
            pluginConfigs = new HashMap<>();
    }


    public static String getConfigParameter(URL jar, UUID pluginUUID) {

        init();

        String result = "";
        ZipInputStream zipIs;
        try {
            zipIs = new ZipInputStream(jar.openStream());
            ZipEntry e;
            while((e = zipIs.getNextEntry()) != null) {
                String entryName = e.getName();
                ZipFile zf = new ZipFile(jar.getFile());
                InputStream is = zf.getInputStream(e);
                if(entryName.equalsIgnoreCase("plugin.yml")) {
                    //plugin.yml gefunden
                    Path temp = Paths.get(Main.getTmpdir(pluginUUID) + "\\tmp.txt");
                    Files.createDirectories(temp.getParent());
                    Files.copy(is, temp);

                    BufferedReader br = new BufferedReader(new FileReader(new File(Main.getTmpdir(pluginUUID) + "\\tmp.txt")));
                    String line = null;
                    while (( line = br.readLine()) != null ){
                        if(line.contains("main: ")){
                            result = line.replace("main: ", "");
                        }
                    }
                    br.close();
                    Files.delete(Paths.get(Main.getTmpdir(pluginUUID) + "\\tmp.txt"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
