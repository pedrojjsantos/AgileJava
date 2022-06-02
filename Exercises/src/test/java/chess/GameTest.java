package chess;

import chess.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import util.StringUtil;

import java.util.List;

import static chess.pieces.Piece.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testKingMoves() {
        Piece king = Piece.createWhiteKing();
        game.putPiece("d4", king);

        List<String> possibleMoves = game.getPossibleMoves("d4");

        assertEquals(8, possibleMoves.size());
        assertTrue(possibleMoves.contains("c3"));
        assertTrue(possibleMoves.contains("c4"));
        assertTrue(possibleMoves.contains("c5"));
        assertTrue(possibleMoves.contains("d3"));
        assertTrue(possibleMoves.contains("d5"));
        assertTrue(possibleMoves.contains("e3"));
        assertTrue(possibleMoves.contains("e4"));
        assertTrue(possibleMoves.contains("e5"));

        game.putPiece("a2", king);

        possibleMoves = game.getPossibleMoves("a2");

        assertEquals(5, possibleMoves.size());
        assertTrue(possibleMoves.contains("a1"));
        assertTrue(possibleMoves.contains("a3"));
        assertTrue(possibleMoves.contains("b1"));
        assertTrue(possibleMoves.contains("b2"));
        assertTrue(possibleMoves.contains("b3"));
    }

    @Test
    public void testQueenMoves() {
        Piece queen = Piece.createBlackQueen();
        game.putPiece("d3", queen);

        List<String> possibleMoves = game.getPossibleMoves("d3");

        assertEquals(25, possibleMoves.size());
        assertTrue(possibleMoves.contains("d1"));
        assertTrue(possibleMoves.contains("d2"));
        assertTrue(possibleMoves.contains("d4"));
        assertTrue(possibleMoves.contains("d5"));
        assertTrue(possibleMoves.contains("d6"));
        assertTrue(possibleMoves.contains("d7"));
        assertTrue(possibleMoves.contains("d8"));

        assertTrue(possibleMoves.contains("a3"));
        assertTrue(possibleMoves.contains("b3"));
        assertTrue(possibleMoves.contains("c3"));
        assertTrue(possibleMoves.contains("e3"));
        assertTrue(possibleMoves.contains("f3"));
        assertTrue(possibleMoves.contains("g3"));
        assertTrue(possibleMoves.contains("h3"));

        assertTrue(possibleMoves.contains("b1"));
        assertTrue(possibleMoves.contains("c2"));
        assertTrue(possibleMoves.contains("e4"));
        assertTrue(possibleMoves.contains("f5"));
        assertTrue(possibleMoves.contains("g6"));
        assertTrue(possibleMoves.contains("h7"));

        assertTrue(possibleMoves.contains("a6"));
        assertTrue(possibleMoves.contains("b5"));
        assertTrue(possibleMoves.contains("c4"));
        assertTrue(possibleMoves.contains("e2"));
        assertTrue(possibleMoves.contains("f1"));
    }
}
