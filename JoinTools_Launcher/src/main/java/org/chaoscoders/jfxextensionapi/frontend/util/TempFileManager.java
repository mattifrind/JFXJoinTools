package org.chaoscoders.jfxextensionapi.frontend.util;

import org.chaoscoders.jfxextensionapi.frontend.Main;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.UUID;

public class TempFileManager {

    public static void cleanUp(){
        if(Files.exists(Paths.get(Main.getTmpdir()))){
            try {
                Files.walkFileTree(Paths.get(Main.getTmpdir()), new FileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                        Files.delete(path);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path path, IOException e) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path path, IOException e) throws IOException {
                        Files.delete(path);
                        return FileVisitResult.CONTINUE;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void initTmpDirs(){
        TempFileManager.cleanUp();
        try {
            Files.createDirectories(Paths.get(Main.getTmpdir()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void initTmpDirs(UUID pluginUUID){
        TempFileManager.cleanUp();
        try {
            if(Files.notExists(Paths.get(Main.getTmpdir(pluginUUID)))){
                Files.createDirectories(Paths.get(Main.getTmpdir(pluginUUID)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
