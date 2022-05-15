package chess;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import chess.pieces.PawnTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BoardTest.class,
        PawnTest.class,
        CharacterTest.class,
})

public class AllTests {
}
