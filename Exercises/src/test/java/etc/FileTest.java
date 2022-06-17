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
            File file = new File(fileName);
            if (file.exists())
                assertTrue(new File(fileName).delete());
        }
    }

    @Test
    public void testPerformance() throws IOException {
        String fileName = "testExercise2.txt";
        long size = 1;


        for (long i = 1; i < 1000000000000000000L; i *= 10) {
            try (FileOutputStream file = new FileOutputStream(fileName)) {
                OutputStreamWriter writer = new OutputStreamWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                long writerTime = writerPerformance(writer, size);
                long bufferedTime = writerPerformance(writer, size);

                System.out.printf("w%d,\tb%d%n", writerTime, bufferedTime);
            }

        }
    }

    private long writerPerformance(Writer writer, long size) throws IOException {
        char ch = 'Z';

        long start = System.currentTimeMillis();

        for (int i = 0; i < size; i++)
            writer.write(ch);

        long stop = System.currentTimeMillis();

        return stop - start;
    }
}
