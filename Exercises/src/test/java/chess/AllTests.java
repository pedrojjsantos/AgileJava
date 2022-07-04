package chess;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BoardTest.class,
        GameTest.class,
        PositionTest.class,
        chess.pieces.AllTests.class,
})

public class AllTests {
}


