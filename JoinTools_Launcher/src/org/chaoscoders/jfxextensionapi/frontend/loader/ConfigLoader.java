package org.chaoscoders.jfxextensionapi.frontend.loader;

import org.chaoscoders.jfxextensionapi.frontend.Main;
import org.chaoscoders.jfxextensionapi.frontend.util.exceptions.InvalidPluginYMLException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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

    public static HashMap<String, String> getConfigContent(File jar){


        HashMap<String, String> result = new HashMap<>();

        ArrayList<String> parameters = new ArrayList<>(
                Arrays.asList("main", "name", "version", "tooltip", "layout"));

        for (String s : parameters){
            try {
                String param = getConfigParameter(jar.toURL(), s);
                if(param.equals("")){
                    throw new InvalidPluginYMLException("Invalid plugin.yml (plugin: "
                            + jar.getName() + ").");
                }else{
                    result.put(s, param);
                }
            } catch (IOException | InvalidPluginYMLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String getConfigParameter(URL jar, String parameter) {

        String result = "";


        ZipInputStream zip = null;
        try {
            zip = new ZipInputStream(jar.openStream());
            while(true) {
                ZipEntry e = zip.getNextEntry();
                if (e == null)
                    break;
                String name = e.getName();
                ZipFile zf = new ZipFile(jar.getFile());
                InputStream is = zf.getInputStream(e);
                if(name.equalsIgnoreCase("plugin.yml")){
                    //plugin.yml gefunden
                    try{
                        Files.delete(Paths.get(Main.tmpdir + "\\tmp.txt"));
                    }catch (NoSuchFileException ignored){ }

                    Files.copy(is, Paths.get(Main.tmpdir + "\\tmp.txt"));

                    BufferedReader br = new BufferedReader(new FileReader(new File(Main.tmpdir + "\\tmp.txt")));
                    String line = null;
                    while (( line = br.readLine()) != null ){
                        if(line.contains(parameter + ": ")){
                            result = line.replace(parameter + ": ", "");
                        }
                    }
                    br.close();
                    Files.delete(Paths.get(Main.tmpdir + "\\tmp.txt"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
