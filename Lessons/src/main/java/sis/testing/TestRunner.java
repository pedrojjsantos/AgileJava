package sis.testing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestRunner {
    Class<?> testClass;
    Set<Method> methods = new HashSet<>();
    int failed = 0;

    public static void main(String[] args) throws Exception {
        TestRunner runner = new TestRunner(args[0]);
        runner.run();
        System.out.println(
                "passed: " + runner.passed() + " failed: " + runner.failed());
        if (runner.failed() > 0)
            System.exit(1);
    }

    public TestRunner(Class<?> testClass) {
        this.testClass = testClass;
        methods.addAll(Arrays.asList(testClass.getDeclaredMethods()));
    }
    public TestRunner(String className) throws Exception {
        this(Class.forName(className));
    }

    public Set<Method> getTestMethods() {
        return methods;
    }

    public void run() {
        for (Method method: getTestMethods())
            run(method);
    }
    private void run(Method method) {
        try {
            Object testObject = testClass.newInstance();
            method.invoke(testObject);
        }
        catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof AssertionError)
                System.out.println(cause.getMessage());
            else
                e.printStackTrace();
            failed++;
        }
        catch (Throwable t) {
            t.printStackTrace();
            failed++;
        }
    }

    public int passed() {
        return methods.size() - failed;
    }
    public int failed() {
        return failed;
    }
}
