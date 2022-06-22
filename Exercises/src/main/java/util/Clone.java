package util;

import java.io.*;

public class Clone {
    private Clone() {}

    public static Object clone(Object obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteStream);
        outputStream.writeObject(obj);
        byte[] array = byteStream.toByteArray();
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(array));

        return inputStream.readObject();
    }
}
