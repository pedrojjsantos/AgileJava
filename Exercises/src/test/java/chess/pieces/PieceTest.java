package chess.pieces;

import chess.Board;
import org.junit.Test;

import java.util.List;

import static chess.pieces.Piece.*;
import static org.junit.Assert.*;

abstract public class PieceTest {
    protected final double STRENGTH_PRECISION = 0.05;

    abstract protected Piece createWhitePiece();
    abstract protected Piece createBlackPiece();
    abstract protected void verifyCreation(Piece whitePiece, Piece blackPiece);

    protected void assertContains(List<String> list, String...expected) {
        assertTrue(list.containsAll(List.of(expected)));
    }

    @Test
    public void testCreate() {
        Piece whitePiece = createWhitePiece();
        Piece blackPiece = createBlackPiece();

        assertEquals("", whitePiece.getPosition());
        assertEquals("", blackPiece.getPosition());

        verifyCreation(whitePiece, blackPiece);

        Piece whitePieceFromChar = Piece.fromChar(whitePiece.print());
        Piece blackPieceFromChar = Piece.fromChar(Character.toUpperCase(whitePiece.print()));

        assertEquals("", whitePieceFromChar.getPosition());
        assertEquals("", blackPieceFromChar.getPosition());

        verifyCreation(whitePieceFromChar, blackPieceFromChar);
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
    public void testStrength() {
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
        // if both have the same strength, return 1 instead of 0;
        assertEquals(1, whiteQueen.compareTo(blackQueen));
        assertEquals(1, blackQueen.compareTo(whiteQueen));
    }

    @Test
    public void testGetPosition() {
        Board board = new Board();
        Piece piece = Piece.createWhitePawn();

        board.put("a2", piece);
        assertEquals("a2", piece.getPosition());

        board.put("a7", piece);
        assertEquals("a7", piece.getPosition());
    }
}