package sis.util;

import java.io.*;

public class LineWriter {
    public static void write(String filename, String[] records) throws IOException {
        File file = new File(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }
        }
    }
}
