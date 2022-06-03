package chess.pieces;

import chess.Board;
import chess.pieces.Piece.Type;
import org.junit.Test;

import java.util.List;

import static chess.pieces.Piece.*;
import static org.junit.Assert.*;

public class PieceTest {
    private final char PAWN_CHAR     = 'p';
    private final char KNIGHT_CHAR   = 'n';
    private final char ROOK_CHAR     = 'r';
    private final char BISHOP_CHAR   = 'b';
    private final char QUEEN_CHAR    = 'q';
    private final char KING_CHAR     = 'k';
    private final char NO_PIECE_CHAR = '.';
    @Test
    public void testCreate() {
        verifyCreation(
                Piece.createWhitePawn(), Piece.createBlackPawn(),
                Type.PAWN, PAWN_CHAR);
        verifyCreation(
                Piece.createWhiteRook(), Piece.createBlackRook(),
                Type.ROOK, ROOK_CHAR);
        verifyCreation(
                Piece.createWhiteKnight(), Piece.createBlackKnight(),
                Type.KNIGHT, KNIGHT_CHAR);
        verifyCreation(
                Piece.createWhiteBishop(), Piece.createBlackBishop(),
                Type.BISHOP, BISHOP_CHAR);
        verifyCreation(
                Piece.createWhiteQueen(), Piece.createBlackQueen(),
                Type.QUEEN, QUEEN_CHAR);
        verifyCreation(
                Piece.createWhiteKing(), Piece.createBlackKing(),
                Type.KING, KING_CHAR);
        Piece blank = Piece.noPiece();
        assertEquals(NO_PIECE_CHAR, blank.print());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    private void verifyCreation(Piece whitePiece, Piece blackPiece,
                                Type type, char representation) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());
        assertEquals(representation, whitePiece.print());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
        assertEquals(representation, blackPiece.print());
    }

    @Test
    public void testCount() {
        Piece.resetCount();
        assertEquals(0, Piece.getCountWhite());
        Piece.createWhitePawn();
        assertEquals(1, Piece.getCountWhite());
        Piece.createWhitePawn();
        assertEquals(2, Piece.getCountWhite());


        assertEquals(0, Piece.getCountBlack());
        Piece.createBlackPawn();
        assertEquals(1, Piece.getCountBlack());
        Piece.createBlackPawn();
        assertEquals(2, Piece.getCountBlack());

        assertEquals(2, Piece.getCountWhite());
    }

    @Test
    public void testIsEqual() {
        Piece whitePawn1 = Piece.createWhitePawn();
        Piece whitePawn2 = Piece.createWhitePawn();
        Piece blackPawn  = Piece.createBlackPawn();

        assertTrue(whitePawn1.isEqualTo(whitePawn2));
        assertTrue(whitePawn2.isEqualTo(whitePawn1));

        assertFalse(whitePawn1.isEqualTo(blackPawn));
        assertFalse(blackPawn.isEqualTo(whitePawn1));

    }

    @Test
    public void testSetStrength() {
        verifyStrength(1.0, Piece.createWhitePawn());
        verifyStrength(9.0, Piece.createWhiteQueen());
        verifyStrength(5.0, Piece.createBlackRook());
        verifyStrength(2.5, Piece.createBlackKnight());
        verifyStrength(5.0, Piece.createWhiteRook());
        verifyStrength(9.0, Piece.createBlackQueen());
    }

    private void verifyStrength(double strength, Piece piece) {
        assertEquals(strength, piece.getStrength(), 0.05);
    }

    @Test
    public void testCompare() {
        Piece whiteQueen = createWhiteQueen();
        Piece blackRook = createBlackRook();
        Piece blackQueen = createBlackQueen();

        assertTrue(whiteQueen.compareTo(blackRook) < 0);
        assertTrue(blackRook.compareTo(whiteQueen) > 0);
        assertEquals(0, whiteQueen.compareTo(blackQueen));
        assertEquals(0, blackQueen.compareTo(whiteQueen));
    }

    @Test
    public void testKingMoves() {
        Board board = new Board();
        Piece king = Piece.createWhiteKing();

        List<String> possibleMoves = king.getPossibleMoves("d4", board);

        assertEquals(8, possibleMoves.size());
        assertTrue(possibleMoves.contains("c3"));
        assertTrue(possibleMoves.contains("c4"));
        assertTrue(possibleMoves.contains("c5"));
        assertTrue(possibleMoves.contains("d3"));
        assertTrue(possibleMoves.contains("d5"));
        assertTrue(possibleMoves.contains("e3"));
        assertTrue(possibleMoves.contains("e4"));
        assertTrue(possibleMoves.contains("e5"));

        board.put("a2", king);

        possibleMoves = king.getPossibleMoves("a2", board);

        assertEquals(5, possibleMoves.size());
        assertTrue(possibleMoves.contains("a1"));
        assertTrue(possibleMoves.contains("a3"));
        assertTrue(possibleMoves.contains("b1"));
        assertTrue(possibleMoves.contains("b2"));
        assertTrue(possibleMoves.contains("b3"));
    }

    @Test
    public void testQueenMoves() {
        Board board = new Board();
        Piece queen = Piece.createBlackQueen();
        board.put("d3", queen);

        List<String> possibleMoves = queen.getPossibleMoves("d3", board);

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