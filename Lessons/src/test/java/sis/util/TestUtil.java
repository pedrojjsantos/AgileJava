package sis.util;

import java.io.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestUtil {
    public static void assertGone(String... filenames) {
        for (String filename: filenames)
            assertFalse(new File(filename).exists());
    }
    public static void delete(String filename) {
        File file = new File(filename);
        if (file.exists())
            assertTrue(file.delete());
    }
}
