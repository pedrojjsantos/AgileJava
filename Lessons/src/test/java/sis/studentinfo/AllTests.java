package sis.studentinfo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import sis.util.DateUtilTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StudentTest.class,
        CourseSessionTest.class,
        DateUtilTest.class,
        BasicGradingStrategyTest.class,
        HonorsGradingStrategyTest.class,
        PerformanceTest.class,
        ScorerTest.class,
        CourseTest.class,
        AccountTest.class,
        CourseCatalogTest.class,
        StudentDirectoryTest.class,
        AccountFactoryTest.class,
        MultiThreadedAccountTest.class,
})
public class AllTests {
}
