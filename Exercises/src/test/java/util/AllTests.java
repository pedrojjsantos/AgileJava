package util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StringUtilTest.class,
        CloneTest.class,
        ObjectDumperTest.class,
        util.file.AllTests.class,
        util.stringer.AllTests.class,
})
public class AllTests {
}
