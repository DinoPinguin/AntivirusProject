package AntvirusProject;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SimpleAntivirus {

    private final List<Path> foundFiles = new ArrayList<>();

    public void searchFiles(Path startPath) throws IOException {
        Files.walkFileTree(startPath, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (file.toString().endsWith(".exe") || file.toString().endsWith(".bat")) {
                    foundFiles.add(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void printReport() {
        System.out.println("Found files:");
        for (Path path : foundFiles) {
            System.out.println(path);
        }
    }

    public void removeFiles() throws IOException {
        for (Path path : foundFiles) {
            Files.delete(path);
            System.out.println("Deleted: " + path);
        }
    }
}