package chess.pieces;

import chess.Board;
import chess.pieces.Piece.Type;
import org.junit.Test;

import java.util.List;

import static chess.pieces.Piece.*;
import static org.junit.Assert.*;

public class PieceTest {

    @Test
    public void testCreate() {
        char PAWN_CHAR = 'p';
        verifyCreation(
                Piece.createWhitePawn(), Piece.createBlackPawn(),
                Type.PAWN, PAWN_CHAR);
        char ROOK_CHAR = 'r';
        verifyCreation(
                Piece.createWhiteRook(), Piece.createBlackRook(),
                Type.ROOK, ROOK_CHAR);
        char KNIGHT_CHAR = 'n';
        verifyCreation(
                Piece.createWhiteKnight(), Piece.createBlackKnight(),
                Type.KNIGHT, KNIGHT_CHAR);
        char BISHOP_CHAR = 'b';
        verifyCreation(
                Piece.createWhiteBishop(), Piece.createBlackBishop(),
                Type.BISHOP, BISHOP_CHAR);
        char QUEEN_CHAR = 'q';
        verifyCreation(
                Piece.createWhiteQueen(), Piece.createBlackQueen(),
                Type.QUEEN, QUEEN_CHAR);
        char KING_CHAR = 'k';
        verifyCreation(
                Piece.createWhiteKing(), Piece.createBlackKing(),
                Type.KING, KING_CHAR);
        Piece blank = Piece.noPiece();
        char NO_PIECE_CHAR = '.';
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
}