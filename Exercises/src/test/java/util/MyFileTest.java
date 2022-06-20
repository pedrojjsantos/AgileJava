package util;

import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyFileTest {
    private final String fileName = "testMyFile.txt";
    private final String content = content();
    private final List<String> contentList = contentList();
    MyFile file;

    private static String content() {
        return ("Create a test to write the text of this exercise to the file system.%n"+
                "The test should read the file back in and make assertions about the content.%n" +
                "Ensure that you can run the test multiple times and have it pass.%n" +
                "Finally, make sure that there are no leftover files when the test finishes,%n" +
                "even if an exception is thrown.%n").formatted();
    }
    private static List<String> contentList() {
        List<String> list = new ArrayList<>();
        list.add("Create a test to write the text of this exercise to the file system.");
        list.add("The test should read the file back in and make assertions about the content.");
        list.add("Ensure that you can run the test multiple times and have it pass.");
        list.add("Finally, make sure that there are no leftover files when the test finishes,");
        list.add("even if an exception is thrown.");
        return list;
    }

    @Before
    public void setUp() {
        file = new MyFile(fileName);
    }

    @Test
    public void testWrite() throws IOException {
        try {
            file.write(content);

            String fileContent = readFile();

            assertEquals(content, fileContent);
        }
        finally {
            if (new File(fileName).exists())
                assertTrue(file.delete());
        }
    }
    private String readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            StringBuilder buffer = new StringBuilder();

            for (String line : reader.lines().toList()) {
                buffer.append(line).append("%n".formatted());
            }

            return buffer.toString();
        }
    }

    @Test
    public void testWriteException() throws IOException{
        try {
            file.write("");
            file.write("");
            fail("Expected Exception");
        } catch (FileAlreadyExistException success) {
        }
        finally {
            if (new File(fileName).exists())
                assertTrue(file.delete());
        }
    }

    @Test
    public void testWriteLines() throws IOException {
        try {
            file.write(contentList);

            String fileContent = file.read();

            assertEquals(content, fileContent);
        }
        finally {
            if (new File(fileName).exists())
                assertTrue(file.delete());
        }
    }
    @Test
    public void testWriteLinesException() throws IOException{
        try {
            file.write(new ArrayList<>());
            file.write(new ArrayList<>());
            fail("Expected Exception");
        } catch (FileAlreadyExistException success) {
        }
        finally {
            if (new File(fileName).exists())
                assertTrue(file.delete());
        }
    }

    @Test
    public void testOverwrite() throws IOException {
        try {
            file.write(content);
            file.overwrite(content);

            String fileContent = file.read();

            assertEquals(content, fileContent);
        }
        finally {
            if (new File(fileName).exists())
                assertTrue(file.delete());
        }
    }

    @Test
    public void testOverwriteLines() throws IOException {
        try {
            file.write(contentList);
            file.overwrite(contentList);

            List<String> fileContent = file.readLines();

            assertEquals(contentList, fileContent);
        }
        finally {
            if (new File(fileName).exists())
                assertTrue(file.delete());
        }
    }

    @Test
    public void testRead() throws IOException {
        try {
            file.write(content);

            String fileContent = file.read();

            assertEquals(content, fileContent);
        }
        finally {
            if (new File(fileName).exists())
                assertTrue(file.delete());
        }
    }

    @Test
    public void testReadLines() throws IOException {
        try {
            file.write(contentList);

            List<String> fileContent = file.readLines();

            assertEquals(contentList, fileContent);
        }
        finally {
            if (new File(fileName).exists())
                assertTrue(file.delete());
        }
    }
}
