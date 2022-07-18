package sis.testing;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public class TestRunnerUI {
    private final TestRunner runner;

    public static void main(String[] args) throws Exception {
        TestRunnerUI ui = new TestRunnerUI(args[0]);
        ui.run();
        System.exit(ui.getNumberOfFailedTests());
    }

    public TestRunnerUI(String testClassName) throws Exception {
        runner = new TestRunner(testClassName);
    }

    public void run() {
        runner.run();
        showResults();
        showIgnoredMethods();
    }
    public int getNumberOfFailedTests() {
        return runner.failed();
    }

    private void showResults() {
        System.out.println(
                "passed: " + runner.passed() + " failed: " + runner.failed());
    }

    private void showIgnoredMethods() {
        if (runner.getIgnoredMethods().isEmpty())
            return;
        System.out.println("\nIgnored Methods");
        for (Map.Entry<Method, Ignore> entry:
                runner.getIgnoredMethods().entrySet()) {
            Ignore ignore = entry.getValue();
            System.out.printf("%s: %s (by %s)",
                    entry.getKey(),
                    Arrays.toString(ignore.reasons()),
                    ignore.initials());
        }
    }
}
