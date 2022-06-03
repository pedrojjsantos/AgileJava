package chess.pieces;

import chess.BoardTest;
import chess.CharacterTest;
import chess.GameTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PieceTest.class,
        BishopTest.class,
        KingTest.class,
        KnightTest.class,
        PawnTest.class,
        QueenTest.class,
        RookTest.class,
})
public class AllTests {
}
