package chess.pieces;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PawnTest {
    @Test
    public void testCreate() {
        Pawn whitePawn = Piece.createWhitePawn();
        Pawn blackPawn = Piece.createBlackPawn();

        assertTrue(whitePawn.isWhite());
        assertEquals(Piece.Type.PAWN, whitePawn.getType());
        assertEquals('p', whitePawn.print());

        assertTrue(blackPawn.isBlack());
        assertEquals(Piece.Type.PAWN, blackPawn.getType());
        assertEquals('p', blackPawn.print());
    }
}
