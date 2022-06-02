package chess;

import chess.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import util.StringUtil;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.pieces.Piece.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardTest {
    private Board board;
    private final String blankRank =
            StringUtil.appendNewLine(". . . . . . . .");

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testCreate() {
        assertEquals(0, board.pieceCount());

        assertEquals(
                blankRank + blankRank +
                        blankRank + blankRank +
                        blankRank + blankRank +
                        blankRank + blankRank,
                board.print()
        );
    }

    @Test
    public void testInitialize() {
        board.initialize();
        assertEquals(32, board.pieceCount());
        assertEquals(16, Piece.getCountWhite());
        assertEquals(16, Piece.getCountBlack());

        System.out.println(board.print());
        assertEquals(
                StringUtil.appendNewLine("R N B Q K B N R") +
                        StringUtil.appendNewLine("P P P P P P P P") +
                        blankRank + blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("p p p p p p p p") +
                        StringUtil.appendNewLine("r n b q k b n r"),
                board.print());
    }

    @Test
    public void testPieceCount() {
        board.initialize();

        assertEquals(8, board.pieceCount(createWhitePawn()));
        assertEquals(8, board.pieceCount(createBlackPawn()));
        assertEquals(2, board.pieceCount(createWhiteRook()));
        assertEquals(1, board.pieceCount(createBlackKing()));
    }

    @Test
    public void testRetrievePiece() {
        board.initialize();
        verifyBoardQuery("a8", createBlackRook());
        verifyBoardQuery("b7", createBlackPawn());
        verifyBoardQuery("f2", createWhitePawn());
        verifyBoardQuery("e1", createWhiteKing());
        verifyBoardQuery("e4", noPiece());
    }

    private void verifyBoardQuery(String position, Piece expected) {
        assertTrue(board.getPiece(position).isEqualTo(expected));
    }

    @Test
    public void testPutPiece() {
        verifyPutPiece("b4", createWhitePawn());
        verifyPutPiece("f5", createWhiteKing());
        verifyPutPiece("a8", createBlackBishop());
        verifyPutPiece("d6", createWhiteRook());
        verifyPutPiece("d6", noPiece());
    }

    private void verifyPutPiece(String position, Piece piece) {
        board.put(position, piece);
        assertEquals(piece, board.getPiece(position));
    }

    @Test
    public void testPieceCollections() {
        verifyWhiteSortedPieces();
        verifyBlackSortedPieces();
    }

    private void verifyWhiteSortedPieces() {
        ArrayList<Piece> whitePieces = new ArrayList<>();
        whitePieces.add(createWhitePawn());
        whitePieces.add(createWhiteQueen());
        whitePieces.add(createWhiteRook());

        board.put("c8",whitePieces.get(2));
        board.put("b8",whitePieces.get(1));
        board.put("a8",whitePieces.get(0));

        Collections.sort(whitePieces);

        List<Piece> boardWhitePieces = board.getWhitePieces();

        for (int index = 0; index < 3; index++)
            assertTrue(whitePieces.get(index).isEqualTo(boardWhitePieces.get(index)));
    }
    private void verifyBlackSortedPieces() {
        ArrayList<Piece> blackPieces = new ArrayList<>();
        blackPieces.add(createBlackPawn());
        blackPieces.add(createBlackQueen());
        blackPieces.add(createBlackRook());

        board.put("c8",blackPieces.get(2));
        board.put("b8",blackPieces.get(1));
        board.put("a8",blackPieces.get(0));

        Collections.sort(blackPieces);

        List<Piece> boardBlackPieces = board.getBlackPieces();

        for (int index = 0; index < 3; index++)
            assertTrue(blackPieces.get(index).isEqualTo(boardBlackPieces.get(index)));
    }

    @Test
    public void testGetFilePositions() {
        List<String> file = board.getFilePositions("b4");

        assertEquals("b1", file.get(0));
        assertEquals("b2", file.get(1));
        assertEquals("b3", file.get(2));
        assertEquals("b4", file.get(3));
        assertEquals("b5", file.get(4));
        assertEquals("b6", file.get(5));
        assertEquals("b7", file.get(6));
        assertEquals("b8", file.get(7));

    }
}
