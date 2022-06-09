package etc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CharacterTest.class,
        LoopTest.class,
        ExceptionTest.class,
})
public class AllTests {
}
