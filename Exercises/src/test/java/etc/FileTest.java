package etc;

import org.junit.Test;

import java.io.*;
import static org.junit.Assert.*;

public class FileTest {
    @Test
    public void testWriteFile() throws IOException {
        String fileName = "textExercise1.txt";
        String text = "Create a test to write the text of this exercise to the file system. " +
                "The test should read the file back in and make assertions about the content. " +
                "Ensure that you can run the test multiple times and have it pass. " +
                "Finally, make sure that there are no leftover files when the test finishes, " +
                "even if an exception is thrown.";

        try (BufferedWriter output = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(fileName)));
             BufferedReader input = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName))))
        {
            output.write(text);
            output.flush();

            String fileText = input.readLine();

            assertEquals(text, fileText);
        }
        finally {
            delete(fileName);
        }
    }

    private void delete(String... filename) {
        for (String name : filename) {
            File file = new File(name);
            if (file.exists())
                assertTrue(file.delete());
        }
    }

    @Test
    public void testPerformance() throws IOException {
        String fileName1 = "testNotBuffered.txt";
        String fileName2 = "testBuffered.txt";
        long size = 100000;

        try (FileOutputStream file1 = new FileOutputStream(fileName1);
             FileOutputStream file2 = new FileOutputStream(fileName2)) {
            OutputStreamWriter writer = new OutputStreamWriter(file1);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(file2));

            long writerTime = writerPerformance(writer, size);
            long bufferedTime = writerPerformance(bufferedWriter, size);

//            System.out.printf("w%d,\tb%d%n", writerTime, bufferedTime);
            assertTrue(writerTime > bufferedTime);
        }
        finally {
            delete(fileName1, fileName2);
        }
    }

    private long writerPerformance(Writer writer, long size) throws IOException {
        char ch = 'Z';

        long start = System.currentTimeMillis();

        for (long i = 0; i < size; i++)
            writer.write(ch);
        writer.flush();
        long stop = System.currentTimeMillis();

        return stop - start;
    }
}
