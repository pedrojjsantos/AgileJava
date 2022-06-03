package chess;

import chess.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import util.StringUtil;

import static chess.pieces.Piece.*;
import static org.junit.Assert.*;

public class GameTest {
    private final String blankRank = StringUtil.appendNewLine(". . . . . . . .");
    private final double STRENGTH_PRECISION = 0.05;
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
    public void testInitialize() {
        game.initialize();
        assertEquals(32, game.pieceCount());
        assertEquals(16, Piece.getCountWhite());
        assertEquals(16, Piece.getCountBlack());

        System.out.println(game.printBoard());
        assertEquals(
                StringUtil.appendNewLine("R N B Q K B N R") +
                        StringUtil.appendNewLine("P P P P P P P P") +
                        blankRank + blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("p p p p p p p p") +
                        StringUtil.appendNewLine("r n b q k b n r"),
                game.printBoard());
    }

    @Test
    public void testAddPiece() {
        Piece pawn = createWhitePawn();
        Piece rook = createBlackRook();

        game.putPiece("d4", pawn);
        assertEquals(pawn, game.getPiece("d4"));
        game.putPiece("d3", rook);
        assertEquals(rook, game.getPiece("d3"));
    }

    @Test
    public void testStrength() {
        assertEquals(0.0, game.getWhiteStrength(), STRENGTH_PRECISION);
        assertEquals(0.0, game.getBlackStrength(), STRENGTH_PRECISION);

        verifyWhiteStrength();
        verifyBlackStrength();
    }

    private void verifyWhiteStrength() {
        game.putPiece("g4", createWhiteQueen());
        assertEquals(9.0, game.getWhiteStrength(), STRENGTH_PRECISION);

        game.putPiece("f4", createWhiteKnight());
        assertEquals(11.5, game.getWhiteStrength(), STRENGTH_PRECISION);

        game.putPiece("f3", createWhitePawn());
        game.putPiece("h3", createWhitePawn());
        assertEquals(13.5, game.getWhiteStrength(), STRENGTH_PRECISION);

        game.putPiece("f2", createWhitePawn());
        game.putPiece("g2", createWhitePawn());
        assertEquals(14.5, game.getWhiteStrength(), STRENGTH_PRECISION);

        game.putPiece("e1", createWhiteRook());
        assertEquals(19.5, game.getWhiteStrength(), STRENGTH_PRECISION);
    }
    private void verifyBlackStrength() {
        game.putPiece("c8", createBlackRook());
        assertEquals(5.0, game.getBlackStrength(), STRENGTH_PRECISION);

        game.putPiece("a7", createBlackPawn());
        game.putPiece("c7", createBlackPawn());
        assertEquals(7.0, game.getBlackStrength(), STRENGTH_PRECISION);

        game.putPiece("d7", createBlackBishop());
        assertEquals(10.0, game.getBlackStrength(), STRENGTH_PRECISION);

        game.putPiece("b6", createBlackPawn());
        game.putPiece("e6", createBlackQueen());
        assertEquals(20.0, game.getBlackStrength(), STRENGTH_PRECISION);
    }
}
