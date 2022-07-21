package util.stringer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    private static String getSeparator(Field field) {
        return field.getAnnotation(Dump.class).separator();
    }

    private static boolean hasQuotes(Field field) {
        return field.getAnnotation(Dump.class).quote();
    }

    private static String[] getOutputMethods(Field field) {
        return field.getAnnotation(Dump.class).outputMethods();
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

        String separator = getSeparator(field);
        String[] outputMethods = getOutputMethods(field);

        List<String> fieldContent = new ArrayList<>(outputMethods.length);

        for (String methodName : outputMethods) {
            String output = getContent(field, obj, methodName);

            if (hasQuotes(field))
                output = "\"%s\"".formatted(output);

            fieldContent.add(output);
        }

        return field.getName() + ": " + String.join(separator, fieldContent);
    }

    public static String dump(Object obj){
        if (obj == null) return "";

        TreeSet<Field> fields = new TreeSet<>(new FieldOrder());

        for (Field field : obj.getClass().getDeclaredFields())
            if (isDumpable(field))
                fields.add(field);

        return fields.stream()
                .map(field -> fromFieldToString(field, obj))
                .collect(Collectors.joining("%n"))
                .formatted();
    }

    static class FieldOrder implements Comparator<Field> {
        @SuppressWarnings("ComparatorMethodParameterNotUsed")
        public int compare(Field f1, Field f2) {
            int compare = Integer.compare(getOrder(f1), getOrder(f2));
            return (compare == 0) ? 1 : compare;
        }
    }
}
