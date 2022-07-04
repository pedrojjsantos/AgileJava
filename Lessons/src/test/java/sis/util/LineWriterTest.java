package sis.util;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class LineWriterTest {
    @Test
    public void testMultipleRecords() throws IOException {
        final String file = "LineWriterTest.testCreate.txt";
        try {
            LineWriter.write(file, new String[] {"a", "b"});
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                assertEquals("a", reader.readLine());
                assertEquals("b", reader.readLine());
                assertNull(reader.readLine());
            }
        }
        finally {
            TestUtil.delete(file);
        }
    }
}
