package util.file;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class DirTest {
    private Dir directory;
    private final String dirName = "test";

    @Before
    public void setUp() throws DirPathNameException {
        directory = new Dir(dirName);
    }

    @After
    public void tearDown(){
        if (new File(dirName).exists())
            assertTrue(directory.delete());
    }

    @Test
    public void testGetFiles() throws IOException {
        assertTrue(directory.ensureExists());
        assertTrue(new File(dirName).isDirectory());

        List<MyFile> files = directory.files();
        assertEquals(0, files.size());

        MyFile newFile = new MyFile(dirName + "/new.txt");
        newFile.write("abc");

        files = directory.files();
        assertEquals(1, files.size());
        assertEquals(newFile.read(), files.get(0).read());

    }

    @Test
    public void testNonExistentDir() {
        try {
            directory.files();
            fail("Expected Exception");
        } catch (DirNonExistentException ignoredSuccess) {}
    }

    @Test
    public void testDirectoryNameException() {
        try {
            new Dir("pom.xml");
            fail("Expected Exception!");
        } catch (DirPathNameException ignoredSuccess) {}
    }

    @Test
    public void testGetAttributes() {
        Dir.Attributes att1 = new Dir.Attributes(directory);

        assertFalse(att1.isHidden());
        assertFalse(att1.isReadOnly());

        assertTrue(directory.ensureExists());
        Dir.Attributes att2 = directory.getAttributes();

        assertFalse(att2.isHidden());
        assertFalse(att2.isReadOnly());
    }
}
