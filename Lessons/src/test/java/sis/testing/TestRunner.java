package sis.testing;


import java.lang.reflect.*;
import java.util.*;

public class TestRunner {
    private final Class<?> testClass;
    private final Set<Method> testMethods = new HashSet<>();
    private final Map<Method, Ignore> ignoredMethods = new HashMap<>();
    private int failed = 0;

    public static void main(String[] args) throws Exception {
        TestRunner runner = new TestRunner(args[0]);
        runner.run();

        System.out.println("passed: " + runner.passed() + " failed: " + runner.failed());
        if (runner.failed() > 0)
            System.exit(1);
    }

    private boolean isTestMethod(Method method) {
        return method.isAnnotationPresent(TestMethod.class);
    }

    private boolean shouldIgnore(Method method) {
        return method.isAnnotationPresent(Ignore.class);
    }

    private void addToIgnoreOrToTestList(Method method) {
        if (shouldIgnore(method))
            ignoredMethods.put(method, method.getAnnotation(Ignore.class));
        else testMethods.add(method);
    }

    public TestRunner(Class<?> testClass) {
        this.testClass = testClass;

        Arrays.stream(testClass.getDeclaredMethods())
                .filter(this::isTestMethod)
                .forEach(this::addToIgnoreOrToTestList);
    }
    public TestRunner(String className) throws Exception {
        this(Class.forName(className));
    }

    public Set<Method> getTestMethods() {
        return testMethods;
    }

    public Map<Method, Ignore> getIgnoredMethods() {
        return ignoredMethods;
    }

    public void run() {
        for (Method method: getTestMethods())
            run(method);
    }

    private void run(Method method) {
        try {
            Object testObject = testClass.getDeclaredConstructor().newInstance();
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
        return testMethods.size() - failed;
    }

    public int failed() {
        return failed;
    }
}
