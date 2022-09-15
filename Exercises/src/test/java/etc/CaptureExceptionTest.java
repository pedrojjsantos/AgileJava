package etc;

import org.junit.Test;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CaptureExceptionTest {
    private void throwNullPointerException() {
        throw new NullPointerException("Ops!");
    }
    @Test
    public void testWithWriter(){
        try {
            throwNullPointerException();
            fail("Expected Exception!");

        } catch (Exception e) {
            String output = getStackTraceWithWriter(e);

            assertTrue(output.startsWith(e.toString()));

            for (StackTraceElement trace : e.getStackTrace())
                assertTrue(output.contains(trace.toString()));
        }
    }

    private String getStackTraceWithWriter(Exception e) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintWriter printWriter =
                new PrintWriter(new BufferedWriter(new OutputStreamWriter(byteStream)));

        e.printStackTrace(printWriter);
        printWriter.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(byteStream.toByteArray())));

        return reader.lines().collect(Collectors.joining("%n".formatted()));
    }

    @Test
    public void testWithoutWriter() throws IOException{
        try {
            throwNullPointerException();
            fail("Expected Exception!");
        } catch (Exception e) {
            String output = getStackTraceWithoutWriter(e);

            assertTrue(output.startsWith(e.toString()));

            for (StackTraceElement trace : e.getStackTrace())
                assertTrue(output.contains(trace.toString()));
        }
    }

    private String getStackTraceWithoutWriter(Exception e) throws IOException{
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintWriter printWriter =
                new PrintWriter(new BufferedOutputStream(byteStream));

        e.printStackTrace(printWriter);
        printWriter.flush();

        BufferedInputStream reader = new BufferedInputStream(
                new ByteArrayInputStream(byteStream.toByteArray()));

        return new String(reader.readAllBytes());
    }
}
