package sis.testing;

import java.lang.reflect.*;
import java.util.*;

public class TestRunnerTest {
    private TestRunner runner;
    private static final String methodNameA = "testA";
    private static final String methodNameB = "testB";
    public static final String IGNORE_REASON1 = "because";
    public static final String IGNORE_REASON2 = "why not";
    public static final String IGNORE_INITIALS = "jjl";

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

    @TestMethod
    public void ignoreWithDefaultReason() {
        runTests(DefaultIgnoreMethodTest.class);
        verifyTests(methodNameA, methodNameB);
        Map<Method, Ignore> ignoredMethods = runner.getIgnoredMethods();
        Map.Entry<Method, Ignore> entry = getSoleEntry(ignoredMethods);
        Ignore ignore = entry.getValue();
        assert TestRunner.DEFAULT_IGNORE_REASON.
                equals(ignore.reasons()[0]);
    }

    @TestMethod
    public void dateTest() {
        runTests(IgnoreDateTest.class);
        Map<Method, Ignore> ignoredMethods = runner.getIgnoredMethods();
        Map.Entry<Method, Ignore> entry = getSoleEntry(ignoredMethods);
        Ignore ignore = entry.getValue();
        sis.testing.Date date = ignore.date();
        assert 1 == date.month();
        assert 2 == date.day();
        assert 2005 == date.year();
    }

    @TestMethod
    public void packageAnnotations() {
        Package pkg = this.getClass().getPackage();
        TestPackage testPackage = pkg.getAnnotation(TestPackage.class);
        assert testPackage.isPerformance();
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
        String[] reasons = ignore.reasons();

        assert 2 == reasons.length :
                "expected two reasons to ignore method: " + entry.getKey().getName();
        assert IGNORE_REASON1.equals(reasons[0]);
        assert IGNORE_REASON2.equals(reasons[1]);
        assert IGNORE_INITIALS.equals(ignore.initials());
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
        @Ignore(reasons = {IGNORE_REASON1, IGNORE_REASON2},
                initials = IGNORE_INITIALS,
                date=@Date(month=1, day=2, year=2005))
        @TestMethod public void testC() {}
    }
    static class DefaultIgnoreMethodTest {
        @TestMethod public void testA() {}
        @TestMethod public void testB() {}
        @Ignore(initials=TestRunnerTest.IGNORE_INITIALS,
                date=@Date(month=1, day=2, year=2005))
        @TestMethod public void testC() {}
    }
    static class IgnoreDateTest {
        @Ignore(
                initials=TestRunnerTest.IGNORE_INITIALS,
                date=@Date(month=1, day=2, year=2005))
        @TestMethod public void testC() {}
    }
}
