package util;

import java.io.*;
import java.lang.reflect.*;

public class Clone {
    private Clone() {}

    public static Object cloneSerialized(Object obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteStream);
        outputStream.writeObject(obj);
        byte[] array = byteStream.toByteArray();
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(array));

        return inputStream.readObject();
    }

    public static Object cloneReflection(Object obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        Object copy = obj.getClass().getConstructor().newInstance();

        for (Field field : fields) {
            field.setAccessible(true);
            Field copyField = copy.getClass().getDeclaredField(field.getName());
            copyField.set(copy, field.get(obj));
        }

        return copy;
    }
}
