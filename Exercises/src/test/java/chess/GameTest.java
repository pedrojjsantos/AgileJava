package chess;

import chess.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import util.StringUtil;

import static chess.pieces.Piece.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
    private final String blankRank = StringUtil.appendNewLine(". . . . . . . .");
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testCreate() {
        assertEquals(0, game.pieceCount());

        assertEquals(
                blankRank + blankRank +
                        blankRank + blankRank +
                        blankRank + blankRank +
                        blankRank + blankRank,
                game.printBoard()
        );
    }

    @Test
    public void testAddPiece() {
        Piece pawn = createWhitePawn();
        Piece rook = createBlackRook();

        game.addPiece("d4", pawn);
        assertEquals(pawn, game.getPiece("d4"));
        game.addPiece("d3", rook);
        assertEquals(rook, game.getPiece("d3"));
    }
}
