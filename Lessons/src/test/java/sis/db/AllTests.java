package sis.db;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import sis.util.DateUtilTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DateUtilTest.class,
        KeyFileTest.class,
})
public class AllTests {
}
