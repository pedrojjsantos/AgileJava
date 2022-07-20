package util.stringer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ToStringer {
    private ToStringer() {}

    private static boolean isDumpable(Field field) {
        return field.isAnnotationPresent(Dump.class);
    }

    private static int getOrder(Field field) {
        return field.getAnnotation(Dump.class).order();
    }

    private static boolean hasQuotes(Field field) {
        return field.getAnnotation(Dump.class).quote();
    }

    private static String getContent(Field field, Object obj, String methodName) {
        try {
            if (methodName.equals("toString"))
                return field.get(obj).toString();
            Method method = obj.getClass().getDeclaredMethod(methodName);
            return method.invoke(obj).toString();
        } catch (Exception e) {
            throw new RuntimeException("Invalid output Method: " + methodName, e);
        }
    }

    private static String fromFieldToString(Field field, Object obj) {
        if (!field.canAccess(obj))
            field.setAccessible(true);

        String[] methodNames = field.getAnnotation(Dump.class).outputMethod();
        StringBuilder buffer = new StringBuilder(field.getName() + ": [");

        for (String methodName : methodNames) {
            String content = getContent(field, obj, methodName);

            if (hasQuotes(field))
                buffer.append('"').append(content).append("\" ");
            else
                buffer.append(content).append(' ');
        }
        buffer.setCharAt(buffer.length() - 1, ']');

        return buffer.toString();
    }

    public static String dump(Object obj){
        if (obj == null) return "";
        Class<?> klass = obj.getClass();
        TreeSet<Field> fields = new TreeSet<>(new FieldComparator());

        for (Field field : klass.getDeclaredFields())
            if (isDumpable(field))
                fields.add(field);

        return fields.stream()
                .map(field -> fromFieldToString(field, obj))
                .collect(Collectors.joining("%n")).formatted();
    }

    static class FieldComparator implements Comparator<Field> {
        @SuppressWarnings("ComparatorMethodParameterNotUsed")
        public int compare(Field f1, Field f2) {
            int compare = Integer.compare(getOrder(f1), getOrder(f2));
            return (compare == 0) ? 1 : compare;
        }
    }
}
