package sis.testing;

import java.lang.reflect.*;
import java.util.*;

public class TestRunnerTest {
    private TestRunner runner;
    private static final String methodNameA = "testA";
    private static final String methodNameB = "testB";
    public static final String IGNORE_REASON1 = "because";

    @TestMethod
    public void singleMethodTest() {
        runTests(SingleMethodTest.class);
        verifyTests(methodNameA);
    }

    @TestMethod
    public void multipleMethodTest() {
        runTests(MultipleMethodTest.class);
        verifyTests(methodNameA, methodNameB);
    }

    @TestMethod
    public void ignoreMethodTest() {
        runTests(IgnoreMethodTest.class);
        verifyTests(methodNameA, methodNameB);
        assertIgnoreReasons();
    }

    private void runTests(Class<?> testClass) {
        runner = new TestRunner(testClass);
        runner.run();
    }

    private void verifyTests(String... expectedTestMethodNames) {
        verifyNumberOfTests(expectedTestMethodNames);
        verifyMethodNames(expectedTestMethodNames);
        verifyCounts(expectedTestMethodNames);
    }

    private void verifyCounts(String... testMethodNames) {
        assert testMethodNames.length == runner.passed() :
            "expected " + testMethodNames.length + " passed";
        assert 0 == runner.failed() : "expected no failures";
    }

    private void verifyNumberOfTests(String... testMethodNames) {
        assert testMethodNames.length == runner.getTestMethods().size() :
                "expected " + testMethodNames.length + " test method(s)";
    }

    private void verifyMethodNames(String... testMethodNames) {
        Set<String> actualMethodNames = getTestMethodNames();
        for (String methodName: testMethodNames)
            assert actualMethodNames.contains(methodName):
                    "expected " + methodName + " as test method";
    }

    private Set<String> getTestMethodNames() {
        Set<String> methodNames = new HashSet<>();
        for (Method method: runner.getTestMethods())
            methodNames.add(method.getName());
        return methodNames;
    }

    private void assertIgnoreReasons() {
        Map<Method, Ignore> ignoredMethods = runner.getIgnoredMethods();
        Map.Entry<Method, Ignore> entry = getSoleEntry(ignoredMethods);
        assert "testC".equals(entry.getKey().getName()):
                "unexpected ignore method: " + entry.getKey();
        Ignore ignore = entry.getValue();
        assert IGNORE_REASON1.equals(ignore.value());
    }

    private <K, V> Map.Entry<K, V> getSoleEntry(Map<K, V> map) {
        assert 1 == map.size(): "expected only one entry";
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        return it.next();
    }

    static class SingleMethodTest {
        @TestMethod public void testA() {}
    }
    static class MultipleMethodTest {
        @TestMethod public void testA() {}
        @TestMethod public void testB() {}
    }
    static class IgnoreMethodTest {
        @TestMethod public void testA() {}
        @TestMethod public void testB() {}
        @Ignore(IGNORE_REASON1)
        @TestMethod public void testC() {}
    }
}
