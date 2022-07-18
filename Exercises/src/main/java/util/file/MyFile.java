package util.file;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class MyFile {
    private final File file;

    public MyFile(String filename) {
        file = new File(filename);
    }

    public void write(String content) throws IOException {
        if (file.exists())
            throw new FileAlreadyExistException("The file already exists!");

        overwrite(content);
    }


    public void write(List<String> contentList) throws IOException {
        if (file.exists())
            throw new FileAlreadyExistException("The file already exists!");

        overwrite(contentList);
    }

    public void overwrite(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            writer.write(content);
            writer.flush();
        }
    }

    public void overwrite(List<String> contentList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (String line : contentList) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        }
    }

    public String read() throws IOException {
        if (!file.exists())
            throw new FileDoesntExistException("The file does not exist!");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder buffer = new StringBuilder();

            for (int i = 0; i < file.length(); i++)
                buffer.append((char) reader.read());

            return buffer.toString();
        }
    }


    public List<String> readLines() throws IOException {
        if (!file.exists())
            throw new FileDoesntExistException("The file does not exist!");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines().collect(Collectors.toList());
        }
    }


    public boolean delete() {
        return file.delete();
    }
}
