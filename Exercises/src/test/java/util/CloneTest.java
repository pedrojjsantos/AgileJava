package util;

import chess.Board;
import chess.Game;
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
        Game game = new Game();
        game.initialize();
        Game gameCopy = (Game) Clone.cloneReflection(game);

        assertEquals(game.pieceCount(), gameCopy.pieceCount());
        assertNotSame(game, gameCopy);

        Board board = new Board();
        board.put("a6", Piece.createBlackRook());
        board.put("f5", Piece.createWhiteQueen());
        Board boardCopy = (Board) Clone.cloneReflection(board);

        assertEquals(board.print(), boardCopy.print());
        assertNotSame(board, boardCopy);
    }
}
