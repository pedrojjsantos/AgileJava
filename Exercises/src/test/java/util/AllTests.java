package util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StringUtilTest.class,
        MyFileTest.class,
        DirTest.class,
        CloneTest.class,
        ObjectDumperTest.class,
})
public class AllTests {
}
