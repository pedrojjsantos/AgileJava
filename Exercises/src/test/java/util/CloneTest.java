package util;

import chess.Board;
import chess.pieces.Piece;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CloneTest {
    @Test
    public void testClone() throws IOException, ClassNotFoundException {
        String str = "123456789";
        String strCopy = (String) Clone.clone(str);

        assertEquals(str, strCopy);
        assertNotSame(str, strCopy);

        Board board = new Board();
        board.put("a6", Piece.createBlackRook());
        Board boardCopy = (Board) Clone.clone(board);

        assertEquals(board.print(), boardCopy.print());
        assertNotSame(board, boardCopy);
    }
}
