package etc;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class PrimitiveSizeTest {
    @Test
    public void testPrimitiveTypesSize() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(byteStream);

        outputStream.writeByte(0);
        assertEquals(1, byteStream.size());
        byteStream.reset();

        outputStream.writeChar(0);
        assertEquals(2, byteStream.size());
        byteStream.reset();

        outputStream.writeShort(0);
        assertEquals(2, byteStream.size());
        byteStream.reset();

        outputStream.writeInt(0);
        assertEquals(4, byteStream.size());
        byteStream.reset();

        outputStream.writeLong(0);
        assertEquals(8, byteStream.size());
        byteStream.reset();

        outputStream.writeFloat(0);
        assertEquals(4, byteStream.size());
        byteStream.reset();

        outputStream.writeDouble(0);
        assertEquals(8, byteStream.size());
        byteStream.reset();
    }
}
