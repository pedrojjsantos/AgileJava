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
    private final double STRENGTH_PRECISION = 0.05;

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
        board.putPiece(position, piece);
        assertEquals(piece, board.getPiece(position));
    }

    @Test
    public void testStrength() {
        assertEquals(0.0, board.getWhiteStrength(), STRENGTH_PRECISION);
        assertEquals(0.0, board.getBlackStrength(), STRENGTH_PRECISION);

        verifyWhiteStrength();
        verifyBlackStrength();

//        System.out.println(board.print());
    }

    private void verifyWhiteStrength() {
        board.putPiece("g4", createWhiteQueen());
        assertEquals(9.0, board.getWhiteStrength(), STRENGTH_PRECISION);

        board.putPiece("f4", createWhiteKnight());
        assertEquals(11.5, board.getWhiteStrength(), STRENGTH_PRECISION);

        board.putPiece("f3", createWhitePawn());
        board.putPiece("h3", createWhitePawn());
        assertEquals(13.5, board.getWhiteStrength(), STRENGTH_PRECISION);

        board.putPiece("f2", createWhitePawn());
        board.putPiece("g2", createWhitePawn());
        assertEquals(14.5, board.getWhiteStrength(), STRENGTH_PRECISION);

        board.putPiece("e1", createWhiteRook());
        assertEquals(19.5, board.getWhiteStrength(), STRENGTH_PRECISION);
    }
    private void verifyBlackStrength() {
        board.putPiece("c8", createBlackRook());
        assertEquals(5.0, board.getBlackStrength(), STRENGTH_PRECISION);

        board.putPiece("a7", createBlackPawn());
        board.putPiece("c7", createBlackPawn());
        assertEquals(7.0, board.getBlackStrength(), STRENGTH_PRECISION);

        board.putPiece("d7", createBlackBishop());
        assertEquals(10.0, board.getBlackStrength(), STRENGTH_PRECISION);

        board.putPiece("b6", createBlackPawn());
        board.putPiece("e6", createBlackQueen());
        assertEquals(20.0, board.getBlackStrength(), STRENGTH_PRECISION);
    }

    @Test
    public void testPiecesCollections() {
        verifyWhiteSortedPieces();
        verifyBlackSortedPieces();
    }

    private void verifyWhiteSortedPieces() {
        ArrayList<Piece> whitePieces = new ArrayList<>();
        whitePieces.add(createWhitePawn());
        whitePieces.add(createWhiteQueen());
        whitePieces.add(createWhiteRook());

        board.putPiece("c8",whitePieces.get(2));
        board.putPiece("b8",whitePieces.get(1));
        board.putPiece("a8",whitePieces.get(0));

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

        board.putPiece("c8",blackPieces.get(2));
        board.putPiece("b8",blackPieces.get(1));
        board.putPiece("a8",blackPieces.get(0));

        Collections.sort(blackPieces);

        List<Piece> boardBlackPieces = board.getBlackPieces();

        for (int index = 0; index < 3; index++)
            assertTrue(blackPieces.get(index).isEqualTo(boardBlackPieces.get(index)));
    }

    @Test
    public void testKingMovement() {
        final String originalPos = "d4";
        verifyMovement(originalPos, "c3");  // down-left
        verifyMovement(originalPos, "d3");  // down
        verifyMovement(originalPos, "e3");  // down-right
        verifyMovement(originalPos, "c4");  // left
        verifyMovement(originalPos, "e4");  // right
        verifyMovement(originalPos, "c5");  // up-left
        verifyMovement(originalPos, "d5");  // up
        verifyMovement(originalPos, "e5");  // up-right
    }

    private void verifyMovement(String currentPos, String nextPos) {
        Piece king = createWhiteKing();

        board.putPiece(currentPos, king);
        assertEquals(king, board.getPiece(currentPos));

        board.moveKing(currentPos, nextPos);
        assertEquals(king, board.getPiece(nextPos));
        assertTrue(noPiece().isEqualTo(board.getPiece(currentPos)));
    }
}
