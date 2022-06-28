package util;

import java.lang.reflect.*;

public class ObjectDumper {
    private static final String FIELD_VALUE = "%-10s %-25s %s";
    private ObjectDumper() {}

    public static String print(Object obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        StringBuilder buffer = new StringBuilder(objClass.getCanonicalName());
        String indent = "\t";

        print(buffer, obj, indent);

        System.out.println(buffer);
        return buffer.toString();
    }

    private static void print(StringBuilder buffer, Object obj, String indent)
            throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            buffer.append("%n%s".formatted(indent));
            if (isPrivate(field))
                printFieldWithoutValue(buffer, field);

            else if (field.getType().getPackageName().startsWith("java"))
                printField(buffer, field, field.get(obj));

            else {
                printFieldWithoutValue(buffer, field);
                print(buffer, field.get(obj), indent + '\t');
            }
        }
    }

    private static void printFieldWithoutValue(StringBuilder buffer, Field field) {
        buffer.append("%-10s %-25s %s"
                .formatted(field.getName()+':',
                        field.getType().getCanonicalName(),
                        getModifier(field)));
    }

    private static void printField(StringBuilder buffer, Field field, Object obj) {
        buffer.append("%-10s %-10s %-25s %s"
                .formatted(field.getName()+':',
                        obj,
                        obj.getClass().getCanonicalName(),
                        getModifier(field)));
    }

    private static String getModifier(Field field) {
        int mod = field.getModifiers();
        String modifier;

        if (Modifier.isPrivate(mod)) modifier = "private";
        else if (Modifier.isProtected(mod)) modifier = "protected";
        else modifier = "public";

        if (Modifier.isStatic(mod)) modifier += " static";

        return modifier;
    }

    private static boolean isPrivate(Field field) {
        return Modifier.isPrivate(field.getModifiers());
    }
}
