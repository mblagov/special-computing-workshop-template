package ru.spbu.apcyb.svp.tasks.task3;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Tree {
    private Path pathOfTheWriterFile;
    private Path pathOfTheDirectory;

    public Tree(Path path, Path path2) {
        this.pathOfTheWriterFile = path;
        this.pathOfTheDirectory = path2;
    }

    public void walker() throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(pathOfTheWriterFile)) {
            Files.walkFileTree(pathOfTheDirectory, new MyFileVisitor(writer));
        } catch (IOException e) {
            throw new FileNotFoundException("TheError" + e.getMessage());
        }
    }

    public void setPathOfTheWriterFile(Path pathOfTheWriterFile) {
        this.pathOfTheWriterFile = pathOfTheWriterFile;
    }

    public void setPathOfTheDirectory(Path pathOfTheDirectory) {
        this.pathOfTheDirectory = pathOfTheDirectory;
    }

    public Path getPathOfTheWriterFile() {
        return pathOfTheWriterFile;
    }

    public Path getPathOfTheDirectory() {
        return pathOfTheDirectory;
    }

    public static void main(String[] args) throws IOException {

        Path pathOfTheWriterFile = Path.of(args[0]);
        Path pathOfTheDirectory = Path.of(args[1]);

        Tree tree = new Tree(pathOfTheWriterFile, pathOfTheDirectory);
        tree.walker();
    }
}
