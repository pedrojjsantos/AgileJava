package util.file;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyFileTest {
    private final String filename = "testMyFile.txt";
    private final String content = _content();
    private final List<String> contentList = _contentList();
    MyFile file;

    private static String _content() {
        return ("Create a test to write the text of this exercise to the file system.%n"+
                "The test should read the file back in and make assertions about the content.%n" +
                "Ensure that you can run the test multiple times and have it pass.%n" +
                "Finally, make sure that there are no leftover files when the test finishes,%n" +
                "even if an exception is thrown.%n").formatted();
    }
    private static List<String> _contentList() {
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
        file = new MyFile(filename);
    }

    @After
    public void tearDown() {
        File file = new File(filename);
        if (file.exists())
            assertTrue(file.delete());
    }

    @Test
    public void testWrite() throws IOException {
        file.write(content);

        String fileContent = readFile();
        assertEquals(content, fileContent);
    }
    private String readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder buffer = new StringBuilder();
            int ch;

            while ((ch = reader.read()) != -1)
                buffer.append((char) ch);

            return buffer.toString();
        }
    }

    @Test
    public void testWriteLines() throws IOException {
        file.write(contentList);

        String fileContent = file.read();
        assertEquals(content, fileContent);
    }

    @Test
    public void testOverwrite() throws IOException {
        file.write("");
        file.overwrite(content);

        String fileContent = file.read();
        assertEquals(content, fileContent);
    }

    @Test
    public void testOverwriteLines() throws IOException {
        file.write(new ArrayList<>());
        file.overwrite(contentList);

        List<String> fileContent = file.readLines();
        assertEquals(contentList, fileContent);
    }

    @Test
    public void testRead() throws IOException {
        file.write(content);

        String fileContent = file.read();
        assertEquals(content, fileContent);
    }

    @Test
    public void testReadLines() throws IOException {
            file.write(contentList);

            List<String> fileContent = file.readLines();
            assertEquals(contentList, fileContent);
    }

    @Test
    public void testWriteException() throws IOException{
        file.write("");
        assertThrows("Expected an exception for trying to write inside an existent file",
                FileAlreadyExistException.class, () -> file.write(""));

        assertThrows("Expected an exception for trying to write inside an existent file",
                FileAlreadyExistException.class, () -> file.write(new ArrayList<>()));
    }

    @Test
    public void testReadException() {
        assertThrows("Expected an exception for trying to read a nonexistent file",
                FileDoesntExistException.class, () -> file.read());

        assertThrows("Expected an exception for trying to read a nonexistent file",
                FileDoesntExistException.class, () -> file.readLines());
    }
}
