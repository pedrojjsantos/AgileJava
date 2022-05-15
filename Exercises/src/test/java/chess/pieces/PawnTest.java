package chess.pieces;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PawnTest {
    @Test
    public void testCreate() {
        Pawn firstPawn = new Pawn(Pawn.WHITE, 'p');
        assertEquals(Pawn.WHITE, firstPawn.getColor());

        Pawn secondPawn = new Pawn(Pawn.BLACK, 'P');
        assertEquals(Pawn.BLACK, secondPawn.getColor());
    }

    @Test
    public void testCreateWithoutColor() {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE, pawn.getColor());
    }

    @Test
    public void testPrintablePawn() {
        Pawn whitePawn = new Pawn(Pawn.WHITE, 'p');
        Pawn blackPawn = new Pawn(Pawn.BLACK, 'P');

        assertEquals('p', whitePawn.print());
        assertEquals('P', blackPawn.print());
    }
}
