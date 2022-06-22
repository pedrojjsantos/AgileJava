package util;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class DirTest {
    private Dir directory;

    @Before
    public void setUp() throws DirPathNameException {
        directory = new Dir("test");
    }

    @Test
    public void testGetFiles() throws IOException {
        try {
            directory.ensureExists();
            assertTrue(new File("test").isDirectory());

            List<MyFile> files = directory.files();
            assertEquals(0, files.size());

            MyFile newFile = new MyFile("test/new.txt");
            newFile.write("abc");

            files = directory.files();
            assertEquals(1, files.size());
            assertEquals(newFile.read(), files.get(0).read());
        }
        finally {
            assertTrue(directory.delete());
        }
    }

    @Test
    public void testNonExistentDir() {
        try {
            List<MyFile> files = directory.files();
            fail("Expected Exception");
        } catch (DirNonExistentException success) {}
    }

    @Test
    public void testDirectoryNameException() {
        try {
            Dir directory = new Dir("pom.xml");
            fail("Expected Exception!");
        } catch (DirPathNameException success) {}
    }

    @Test
    public void testGetAttributes() {
        Dir.Attributes att = new Dir.Attributes(directory);

        assertFalse(att.isHidden());
        assertFalse(att.isReadOnly());
    }
}
