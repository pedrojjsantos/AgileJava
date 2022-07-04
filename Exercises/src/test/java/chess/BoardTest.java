package chess;

import chess.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import util.StringUtil;


import java.util.*;

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
        isClear(board);
    }

    @Test
    public void testClearBoard() {
        initialize(board);
        board.clearBoard();
        isClear(board);
    }

    private void isClear(Board board) {
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
        initialize(board);
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
        initialize(board);

        assertEquals(8, board.pieceCount(createWhitePawn()));
        assertEquals(8, board.pieceCount(createBlackPawn()));
        assertEquals(2, board.pieceCount(createWhiteRook()));
        assertEquals(1, board.pieceCount(createBlackKing()));
    }

    @Test
    public void testRetrievePiece() {
        initialize(board);
        verifyBoardQuery(new Position("a8"), createBlackRook());
        verifyBoardQuery(new Position("b7"), createBlackPawn());
        verifyBoardQuery(new Position("f2"), createWhitePawn());
        verifyBoardQuery(new Position("e1"), createWhiteKing());
        verifyBoardQuery(new Position("e4"), noPiece());
    }

    private void verifyBoardQuery(Position pos, Piece expected) {
        assertTrue(board.getPiece(pos).isEqualTo(expected));
    }

    @Test
    public void testPutPiece() {
        verifyPutPiece(new Position("b4"), createWhitePawn());
        verifyPutPiece(new Position("f5"), createWhiteKing());
        verifyPutPiece(new Position("a8"), createBlackBishop());
        verifyPutPiece(new Position("d6"), createWhiteRook());
        verifyPutPiece(new Position("d6"), noPiece());
    }

    private void verifyPutPiece(Position pos, Piece piece) {
        board.put(pos, piece);
        assertEquals(piece, board.getPiece(pos));
    }

    @Test
    public void testPieceCollections() {
        initialize(board);

        verifySortedPieces(board.getWhitePieces());
        verifySortedPieces(board.getBlackPieces());
    }

    private void verifySortedPieces(Collection<Piece> boardPieces) {
        Piece[] pieceList = boardPieces.toArray(new Piece[] {});

        for (int i = 0; i < pieceList.length - 1; i++)
            assertTrue(pieceList[i].getStrength() >= pieceList[i+1].getStrength());
    }


    @Test
    public void testGetPositions() {
        List<Position> positions;

        // File Positions
        positions = board.getFilePositionsAt(new Position("b4"));
        assertEquals(8, positions.size());
        assertContains(positions, "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8");

        // Rank Positions
        positions = board.getRankPositionsAt(new Position("a5"));
        assertEquals(8, positions.size());
        assertContains(positions, "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5");

        // Diagonal Positions
        positions = board.getDiagonalPositionsAt(new Position("d3"));
        assertEquals(12, positions.size());
        assertContains(positions, "b1", "c2", "e4", "f5", "g6", "h7");
        assertContains(positions, "a6", "b5", "c4", "d3", "e2", "f1");
    }

    private void assertContains(List<Position> list, String...expected) {
        List<Position> expectedList = Arrays.stream(expected).map(Position::new).toList();
        assertTrue(list.containsAll(expectedList));
    }

    @Test
    public void testIterable() {
        initialize(board);
        String iteratedPrint = iteratePrint();
        assertEquals(
                "RNBQKBNR" +
                        "PPPPPPPP" +
                        "pppppppp" +
                        "rnbqkbnr", iteratedPrint);
    }

    private String iteratePrint() {
        StringBuilder buffer = new StringBuilder();

        for (Piece piece : board)
            buffer.append(piece.print());

        return buffer.toString();
    }

    private void initialize(Board board) {
        initWhiteRanks(board);
        initBlackRanks(board);
    }

    private void initWhiteRanks(Board board) {
        board.put(new Position("a1"), Piece.createWhiteRook());
        board.put(new Position("b1"), Piece.createWhiteKnight());
        board.put(new Position("c1"), Piece.createWhiteBishop());
        board.put(new Position("d1"), Piece.createWhiteQueen());
        board.put(new Position("e1"), Piece.createWhiteKing());
        board.put(new Position("f1"), Piece.createWhiteBishop());
        board.put(new Position("g1"), Piece.createWhiteKnight());
        board.put(new Position("h1"), Piece.createWhiteRook());

        for (int file = 0; file < 8; file++)
            board.put(new Position(file, 1), Piece.createWhitePawn());
    }

    private void initBlackRanks(Board board) {
        board.put(new Position("a8"), Piece.createBlackRook());
        board.put(new Position("b8"), Piece.createBlackKnight());
        board.put(new Position("c8"), Piece.createBlackBishop());
        board.put(new Position("d8"), Piece.createBlackQueen());
        board.put(new Position("e8"), Piece.createBlackKing());
        board.put(new Position("f8"), Piece.createBlackBishop());
        board.put(new Position("g8"), Piece.createBlackKnight());
        board.put(new Position("h8"), Piece.createBlackRook());

        for (int file = 0; file < 8; file++)
            board.put(new Position(file, 6), Piece.createBlackPawn());
    }
}