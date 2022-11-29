package example;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileWorker {
    private static final String FILE_PATH = System.getProperty("C:");

    public File createFile(String fileName) throws IOException {
        File file = new File(FILE_PATH, fileName);
        if (!file.exists() && !file.createNewFile()) {
            throw new FileNotFoundException();
        }
        return file;
    }

    public void save(String fileName, String row) {
        try (FileWriter fileWriter = new FileWriter(createFile(fileName), StandardCharsets.UTF_8, true)) {
            fileWriter.write(row);
        } catch (SecurityException | IOException e) {
            System.out.println(e);
        }

    }
}
