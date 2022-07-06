package sis.search;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SearchTest.class,
        ServerTest.class,
        SearchSchedulerTest.class,
})
public class AllTests {
}
