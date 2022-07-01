package util;

import java.lang.reflect.*;
import java.util.*;

public class DumpProxy implements InvocationHandler {
    private List<String> objectMethods;
    private String dumpMethod;
    private Object target;

    public DumpProxy(Object target, String dumpMethod, String... objectMethods) {
        this.target = target;
        this.objectMethods = Arrays.asList(objectMethods);
        this.dumpMethod = dumpMethod;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (isObjectMethod(method))
                return method.invoke(target, args);
            else if (isDumpMethod(method))
                return ObjectDumper.print(target);
            throw new NoSuchMethodException();
        }
        catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    private boolean isDumpMethod(Method method) {
        return dumpMethod.equals(method.getName());
    }

    private boolean isObjectMethod(Method method) {
        return objectMethods.contains(method.getName());
    }
}
