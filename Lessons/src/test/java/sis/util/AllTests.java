package sis.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ParityCheckerTest.class,
        PasswordGeneratorTest.class,
        DateUtilTest.class,
        IOUtilTest.class,
        LineWriterTest.class,
        StringUtilTest.class,
        MultiHashMapTest.class,
        EventMapTest.class,
})
public class AllTests {
}
