package chess.pieces;

import org.junit.Test;

import static org.junit.Assert.*;

public class PieceTest {
    @Test
    public void testCreate() {
        verifyCreation(
                Piece.createWhitePawn(), Piece.createBlackPawn(),
                Piece.Type.PAWN, Piece.PAWN_CHAR);
        verifyCreation(
                Piece.createWhiteRook(), Piece.createBlackRook(),
                Piece.Type.ROOK, Piece.ROOK_CHAR);
        verifyCreation(
                Piece.createWhiteKnight(), Piece.createBlackKnight(),
                Piece.Type.KNIGHT, Piece.KNIGHT_CHAR);
        verifyCreation(
                Piece.createWhiteBishop(), Piece.createBlackBishop(),
                Piece.Type.BISHOP, Piece.BISHOP_CHAR);
        verifyCreation(
                Piece.createWhiteQueen(), Piece.createBlackQueen(),
                Piece.Type.QUEEN, Piece.QUEEN_CHAR);
        verifyCreation(
                Piece.createWhiteKing(), Piece.createBlackKing(),
                Piece.Type.KING, Piece.KING_CHAR);
//        Piece blank = Piece.noPiece();
//        assertEquals('.', blank.print());
//        assertEquals(Piece.Type.NO_PIECE, blank.getType());
    }

    @Test
    public void testCount() {
        Piece.resetCount();
        assertEquals(0, Piece.getCountWhite());
        createWhitePiece();
        assertEquals(1, Piece.getCountWhite());
        createWhitePiece();
        assertEquals(2, Piece.getCountWhite());


        assertEquals(0, Piece.getCountBlack());
        createBlackPiece();
        assertEquals(1, Piece.getCountBlack());
        createBlackPiece();
        assertEquals(2, Piece.getCountBlack());

        assertEquals(2, Piece.getCountWhite());
    }

    private void verifyCreation(Piece whitePiece, Piece blackPiece,
                                Piece.Type type, char representation) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());
        assertEquals(representation, whitePiece.print());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
        assertEquals(Character.toUpperCase(representation), blackPiece.print());
    }

    private Piece createWhitePiece() {
        return Piece.createWhite(Piece.Type.PAWN, 'p');

    }

    private Piece createBlackPiece() {
        return Piece.createBlack(Piece.Type.PAWN, 'P');
    }
}