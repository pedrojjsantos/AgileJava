package util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ObjectDumper {
    private ObjectDumper() {}

    public static String print(Object obj) throws IllegalAccessException {
        StringBuilder buffer = new StringBuilder(obj.getClass().getName());
        String indent = "\t";

        print(buffer, obj, indent);

        return buffer.toString();
    }

    private static void print(StringBuilder buffer, Object obj, String indent)
            throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            buffer.append("%n%s".formatted(indent));
            if (isPrivate(field))
                field.setAccessible(true);

            if (isFromJavaLangOrJavax(field))
                printField(buffer, field, field.get(obj));

            else {
                printFieldWithoutValue(buffer, field);
                print(buffer, field.get(obj), indent + "-\t");
            }
        }
    }

    private static boolean isFromJavaLangOrJavax(Field field) {
        return field.getType().getPackageName().startsWith("java");
    }

    private static void printFieldWithoutValue(StringBuilder buffer, Field field) {
        String name = StringUtil.truncate(field.getName(), 8);
        String type = getType(field);
        buffer.append("%-9s %-15s | %-27s %s"
                .formatted(name + ':', "", type, getModifier(field)));
    }

    private static void printField(StringBuilder buffer, Field field, Object obj) {
        String name = StringUtil.truncate(field.getName(), 8);
        String value = StringUtil.truncate(obj.toString(), 15);
        String type = getType(field);

        buffer.append("%-9s %-15s | %-27s %s"
                .formatted(name + ':', value, type, getModifier(field)));
    }

    private static String getType(Field field) {
        String[] fieldType = field.getGenericType().getTypeName().split("[<>]");
        String type = StringUtil.truncate(StringUtil.splitAndGetLast("[.$]", fieldType[0]), 15);
        String params = "";
        if (fieldType.length > 1) {
            String param = Arrays.stream(fieldType[1].split(", "))
                            .map(s -> StringUtil.splitAndGetLast("[.$]", s))
                            .collect(Collectors.joining(","));
            if (type.length() + param.length() > 25)
                param = StringUtil.truncate(param, 25 - type.length());
            params = '<' + param + '>';
        }
        return type + params;
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
