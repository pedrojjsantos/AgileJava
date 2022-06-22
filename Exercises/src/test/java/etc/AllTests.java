package etc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CharacterTest.class,
        LoopTest.class,
        ExceptionTest.class,
        CounterHandlerTest.class,
        CustomFormatterTest.class,
        WordCountTest.class,
        NameTest.class,
        NumTest.class,
        FileTest.class,
        PrimitiveSizeTest.class,
})
public class AllTests {
}
