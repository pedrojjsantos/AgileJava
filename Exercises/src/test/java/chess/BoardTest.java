package chess;

import chess.pieces.Pawn;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class BoardTest {
    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @Test
    public void testCreate() {
        assertEquals(16, board.getNumberOfPieces());
        assertEquals("pppppppp", board.getSecondRankString());
        assertEquals("PPPPPPPP", board.getSeventhRankString());

        assertEquals(
                "........" + Board.NEWLINE +
                        "PPPPPPPP" + Board.NEWLINE +
                        "........" + Board.NEWLINE +
                        "........" + Board.NEWLINE +
                        "........" + Board.NEWLINE +
                        "........" + Board.NEWLINE +
                        "pppppppp" + Board.NEWLINE +
                        "........" + Board.NEWLINE,
                board.printBoard()
        );
        System.out.println(board.printBoard());
    }
}
