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

        assertEquals(
                StringUtil.appendNewLine("R N B Q K B N R") +
                        StringUtil.appendNewLine("P P P P P P P P") +
                        blankRank + blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("p p p p p p p p") +
                        StringUtil.appendNewLine("r n b q k b n r"),
                board.print());
//        System.out.println(board.print());
    }

    @Test
    public void testPieceCount() {
        board.initialize();
        final char whitePawnRepresentation = PAWN_CHAR;
        final char blackPawnRepresentation = Character.toUpperCase(PAWN_CHAR);
        final char whiteRookRepresentation = ROOK_CHAR;
        final char blackKingRepresentation = Character.toUpperCase(KING_CHAR);

        assertEquals(8, board.pieceCount(whitePawnRepresentation));
        assertEquals(8, board.pieceCount(blackPawnRepresentation));
        assertEquals(2, board.pieceCount(whiteRookRepresentation));
        assertEquals(1, board.pieceCount(blackKingRepresentation));
    }

    @Test
    public void testRetrievePiece() {
        board.initialize();
        verifyBoardQuery("a8", Piece.createBlackRook());
        verifyBoardQuery("b7", Piece.createBlackPawn());
        verifyBoardQuery("f2", Piece.createWhitePawn());
        verifyBoardQuery("e1", Piece.createWhiteKing());
        verifyBoardQuery("e4", Piece.noPiece());
    }

    private void verifyBoardQuery(String position, Piece expected) {
        assertTrue(board.getPiece(position).isEqualTo(expected));
    }

    @Test
    public void testPutPiece() {
        verifyPutPiece("b4", Piece.createWhitePawn());
        verifyPutPiece("f5", Piece.createWhiteKing());
        verifyPutPiece("a8", Piece.createBlackBishop());
        verifyPutPiece("d6", Piece.createWhiteRook());
        verifyPutPiece("d6", Piece.noPiece());
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

        System.out.println(board.print());
    }

    private void verifyWhiteStrength() {
        board.putPiece("g4", Piece.createWhiteQueen());
        assertEquals(9.0, board.getWhiteStrength(), STRENGTH_PRECISION);

        board.putPiece("f4", Piece.createWhiteKnight());
        assertEquals(11.5, board.getWhiteStrength(), STRENGTH_PRECISION);

        board.putPiece("f3", Piece.createWhitePawn());
        board.putPiece("h3", Piece.createWhitePawn());
        assertEquals(13.5, board.getWhiteStrength(), STRENGTH_PRECISION);

        board.putPiece("f2", Piece.createWhitePawn());
        board.putPiece("g2", Piece.createWhitePawn());
        assertEquals(14.5, board.getWhiteStrength(), STRENGTH_PRECISION);

        board.putPiece("e1", Piece.createWhiteRook());
        assertEquals(19.5, board.getWhiteStrength(), STRENGTH_PRECISION);
    }
    private void verifyBlackStrength() {
        board.putPiece("c8", Piece.createBlackRook());
        assertEquals(5.0, board.getBlackStrength(), STRENGTH_PRECISION);

        board.putPiece("a7", Piece.createBlackPawn());
        board.putPiece("c7", Piece.createBlackPawn());
        assertEquals(7.0, board.getBlackStrength(), STRENGTH_PRECISION);

        board.putPiece("d7", Piece.createBlackBishop());
        assertEquals(10.0, board.getBlackStrength(), STRENGTH_PRECISION);

        board.putPiece("b6", Piece.createBlackPawn());
        board.putPiece("e6", Piece.createBlackQueen());
        assertEquals(20.0, board.getBlackStrength(), STRENGTH_PRECISION);
    }

    @Test
    public void testPiecesCollections() {
        verifyWhiteSortedPieces();
        verifyBlackSortedPieces();
    }

    private void verifyWhiteSortedPieces() {
        ArrayList<Piece> whitePieces = new ArrayList<>();
        whitePieces.add(createWhitePieceWithStrength(0.0));
        whitePieces.add(createWhitePieceWithStrength(9.5));
        whitePieces.add(createWhitePieceWithStrength(5.5));

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
        blackPieces.add(createBlackPieceWithStrength(0.0));
        blackPieces.add(createBlackPieceWithStrength(9.5));
        blackPieces.add(createBlackPieceWithStrength(5.5));

        board.putPiece("c8",blackPieces.get(2));
        board.putPiece("b8",blackPieces.get(1));
        board.putPiece("a8",blackPieces.get(0));

        Collections.sort(blackPieces);

        List<Piece> boardBlackPieces = board.getBlackPieces();

        for (int index = 0; index < 3; index++)
            assertTrue(blackPieces.get(index).isEqualTo(boardBlackPieces.get(index)));
    }

    private Piece createWhitePieceWithStrength(double strength) {
        Piece piece = Piece.createWhitePawn();
        piece.setStrength(strength);
        return piece;
    }
    private Piece createBlackPieceWithStrength(double strength) {
        Piece piece = Piece.createBlackPawn();
        piece.setStrength(strength);
        return piece;
    }
}
