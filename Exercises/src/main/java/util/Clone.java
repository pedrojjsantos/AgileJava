package util;

import java.io.*;
import java.lang.reflect.*;
import java.util.List;

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
        if (obj == null) return null;

        Field[] fields = obj.getClass().getDeclaredFields();
        Class<?> klass = obj.getClass();

        Object copy = klass.getDeclaredConstructor().newInstance();

        for (Field field : fields) {
            if (!Modifier.isFinal(field.getModifiers())) {
                field.setAccessible(true);
                field.set(copy, field.get(obj));
            }
        }

        return copy;
    }
}
