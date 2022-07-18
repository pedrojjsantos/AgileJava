import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        chess.AllTests.class,
        etc.AllTests.class,
        util.AllTests.class,
        alarm.AllTests.class,
        ring.AllTests.class,
})
public class AllTests {
}
