package chess.pieces;

import chess.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class PawnTest extends PieceTest {
    @Override
    protected Piece createWhitePiece() {
        return Piece.createWhitePawn();
    }

    @Override
    protected Piece createBlackPiece() {
        return Piece.createBlackPawn();
    }

    @Override
    protected void verifyCreation(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertSame(Pawn.class, whitePiece.getClass());
        assertEquals('p', whitePiece.print());
        assertEquals(1, whitePiece.getStrength(), STRENGTH_PRECISION);

        assertTrue(blackPiece.isBlack());
        assertSame(Pawn.class, blackPiece.getClass());
        assertEquals('P', blackPiece.print());
        assertEquals(1, blackPiece.getStrength(), STRENGTH_PRECISION);
    }

    @Test
    public void testPawnMoves() {
        Pawn blackPawn = Piece.createBlackPawn();
        Pawn whitePawn = Piece.createWhitePawn();

        List<Position> blackMoves = blackPawn.getPossibleMoves(new Position("e5"));
        List<Position> whiteMoves = whitePawn.getPossibleMoves(new Position("e5"));

        assertEquals(1, blackMoves.size());
        assertEquals(1, whiteMoves.size());

        assertContains(blackMoves, "e4");
        assertContains(whiteMoves, "e6");


        blackMoves = blackPawn.getPossibleMoves(new Position("e7"));
        whiteMoves = whitePawn.getPossibleMoves(new Position("e2"));

        assertEquals(2, blackMoves.size());
        assertEquals(2, whiteMoves.size());

        assertContains(blackMoves, "e6", "e5");
        assertContains(whiteMoves, "e3", "e4");


        blackMoves = blackPawn.getPossibleMoves(new Position("e1"));
        whiteMoves = whitePawn.getPossibleMoves(new Position("e8"));

        assertEquals(0, blackMoves.size());
        assertEquals(0, whiteMoves.size());
    }
}
