package sis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        sis.report.AllTests.class,
        sis.studentinfo.AllTests.class,
        sis.summer.AllTests.class,
        sis.util.AllTests.class,
})
public class AllTests {
}
