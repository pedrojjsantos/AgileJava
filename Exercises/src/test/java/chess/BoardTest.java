package chess;

import chess.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import util.StringUtil;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
        board.initialize();
    }

    @Test
    public void testCreate() {
        assertEquals(32, board.pieceCount());
        assertEquals(16, Piece.getCountWhite());
        assertEquals(16, Piece.getCountBlack());

        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(
                StringUtil.appendNewLine("RNBQKBNR") +
                        StringUtil.appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("pppppppp") +
                        StringUtil.appendNewLine("rnbqkbnr"),
                board.print());
        System.out.println(board.print());
    }

    @Test
    public void testPieceCount() {
        final char whitePawnRepresentation = Piece.PAWN_CHAR;
        final char blackPawnRepresentation = Character.toUpperCase(Piece.PAWN_CHAR);
        final char whiteRookRepresentation = Piece.ROOK_CHAR;
        final char blackKingRepresentation = Character.toUpperCase(Piece.KING_CHAR);

        assertEquals(8, board.pieceCount(whitePawnRepresentation));
        assertEquals(8, board.pieceCount(blackPawnRepresentation));
        assertEquals(2, board.pieceCount(whiteRookRepresentation));
        assertEquals(1, board.pieceCount(blackKingRepresentation));

    }
}
