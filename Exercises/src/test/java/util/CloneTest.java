package util;

import chess.Board;
import chess.pieces.Piece;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CloneTest {
    @Test
    public void testCloneSerialized() throws IOException, ClassNotFoundException {
        String str = "123456789";
        String strCopy = (String) Clone.cloneSerialized(str);

        assertEquals(str, strCopy);
        assertNotSame(str, strCopy);

        Board board = new Board();
        board.put("a6", Piece.createBlackRook());
        Board boardCopy = (Board) Clone.cloneSerialized(board);

        assertEquals(board.print(), boardCopy.print());
        assertNotSame(board, boardCopy);
    }

    @Test
    public void testCloneReflection() throws Exception {
        String str = "123456789";
        String strCopy = (String) Clone.cloneReflection(str);

        assertEquals(str, strCopy);
        assertNotSame(str, strCopy);

        Board board = new Board();
        board.put("a6", Piece.createBlackRook());
        Board boardCopy = (Board) Clone.cloneReflection(board);

        assertEquals(board.print(), boardCopy.print());
        assertNotSame(board, boardCopy);
    }
}
