package util;

import java.lang.reflect.*;

public class ObjectDumper {
    private ObjectDumper() {}

    public static String print(Object obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        StringBuilder buffer = new StringBuilder(objClass.getCanonicalName());
        String indent = "\t";

        print(buffer, obj, indent);

        return buffer.toString();
    }

    private static void print(StringBuilder buffer, Object obj, String indent)
            throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            buffer.append("%n%s".formatted(indent));
            if (isPrivate(field))
                field.setAccessible(true);

            if (field.getType().getPackageName().startsWith("java"))
                printField(buffer, field, field.get(obj));

            else {
                printFieldWithoutValue(buffer, field);
                print(buffer, field.get(obj), indent + "-\t");
            }
        }
    }

    private static void printFieldWithoutValue(StringBuilder buffer, Field field) {
        String name = StringUtil.truncate(field.getName(), 8);
        String[] fullType = field.getType().getCanonicalName().split("\\.");
        String type = StringUtil.truncate(fullType[fullType.length - 1], 15);

        buffer.append("%-9s %-15s | %-15s %s"
                .formatted(name + ':', " ", type, getModifier(field)));
    }

    private static void printField(StringBuilder buffer, Field field, Object obj) {
        String name = StringUtil.truncate(field.getName(), 8);

        String value = StringUtil.truncate(obj.toString(), 15);

        String[] fullType = obj.getClass().getCanonicalName().split("\\.");
        String type = StringUtil.truncate(fullType[fullType.length - 1], 15);

        buffer.append("%-9s %-15s | %-15s %s"
                .formatted(name + ':', value, type, getModifier(field)));
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
