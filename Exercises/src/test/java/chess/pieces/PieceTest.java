package chess.pieces;

import org.junit.Test;

import static chess.pieces.Piece.*;
import static org.junit.Assert.*;

abstract public class PieceTest {
    abstract protected Piece createWhitePiece();
    abstract protected Piece createBlackPiece();
    abstract protected void verifyCreation(Piece whitePiece, Piece blackPiece);

    @Test
    public void testCreate() {
        Piece whitePiece = createWhitePiece();
        Piece blackPiece = createBlackPiece();

        verifyCreation(whitePiece, blackPiece);
    }

    //    @Test
//    public void testCreate() {
//        char PAWN_CHAR = 'p';
//        verifyCreation(
//                Piece.createWhitePawn(), Piece.createBlackPawn(), PAWN_CHAR);
//        char ROOK_CHAR = 'r';
//        verifyCreation(
//                Piece.createWhiteRook(), Piece.createBlackRook(), ROOK_CHAR);
//        char KNIGHT_CHAR = 'n';
//        verifyCreation(
//                Piece.createWhiteKnight(), Piece.createBlackKnight(), KNIGHT_CHAR);
//        char BISHOP_CHAR = 'b';
//        verifyCreation(
//                Piece.createWhiteBishop(), Piece.createBlackBishop(), BISHOP_CHAR);
//        char QUEEN_CHAR = 'q';
//        verifyCreation(
//                Piece.createWhiteQueen(), Piece.createBlackQueen(), QUEEN_CHAR);
//        char KING_CHAR = 'k';
//        verifyCreation(
//                Piece.createWhiteKing(), Piece.createBlackKing(), KING_CHAR);
//
//        Piece blank = Piece.noPiece();
//        char NO_PIECE_CHAR = '.';
//        assertSame(blank.getClass(), NoPiece.class);
//    }
//
//
//    private void verifyCreation(Piece whitePiece, Piece blackPiece, char representation) {
//        assertTrue(whitePiece.isWhite());
//        assertEquals(type, whitePiece.getType());
//        assertEquals(representation, whitePiece.print());
//
//        assertTrue(blackPiece.isBlack());
//        assertEquals(type, blackPiece.getType());
//        assertEquals(representation, blackPiece.print());
//    }

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
        assertEquals(0, whiteQueen.compareTo(blackQueen));
        assertEquals(0, blackQueen.compareTo(whiteQueen));
    }
}