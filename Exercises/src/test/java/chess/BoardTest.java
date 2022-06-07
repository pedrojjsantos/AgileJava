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
import static util.StringUtil.appendNewLine;

public class BoardTest {
    private Board board;
    private final String blankRank =
            appendNewLine(". . . . . . . .");

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
        assertEquals(16, board.getCountWhite());
        assertEquals(16, board.getCountBlack());

        assertEquals(
                appendNewLine("R N B Q K B N R") +
                        appendNewLine("P P P P P P P P") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("p p p p p p p p") +
                        appendNewLine("r n b q k b n r"),
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
    public void testGetPositions() {
        List<String> positions;

        // File Positions
        positions = board.getFilePositionsAt("b4");
        assertEquals(8, positions.size());
        assertContains(positions, "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8");

        // Rank Positions
        positions = board.getRankPositionsAt("a5");
        assertEquals(8, positions.size());
        assertContains(positions, "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5");

        // Diagonal Positions
        positions = board.getDiagonalPositionsAt("d3");
        assertEquals(12, positions.size());
        assertContains(positions, "b1", "c2", "e4", "f5", "g6", "h7");
        assertContains(positions, "a6", "b5", "c4", "d3", "e2", "f1");
    }

    private void assertContains(List<String> list, String...expected) {
        assertTrue(list.containsAll(List.of(expected)));
    }
}
