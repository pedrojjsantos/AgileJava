package util;

import java.lang.reflect.*;

public class ObjectDumper {
    private ObjectDumper() {}

    public static String print(Object obj) {
        Class objClass = obj.getClass();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            ;
        }
    }
}
