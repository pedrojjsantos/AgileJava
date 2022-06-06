package chess;

import chess.pieces.PieceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BoardTest.class,
        GameTest.class,
        CharacterTest.class,
        LoopTest.class,
        chess.pieces.AllTests.class,
})

public class AllTests {
}


