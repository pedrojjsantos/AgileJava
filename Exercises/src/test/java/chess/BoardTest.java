package chess;

import chess.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import util.StringUtil;


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

        assertEquals(
                StringUtil.appendNewLine("R N B Q K B N R") +
                        StringUtil.appendNewLine("P P P P P P P P") +
                        blankRank + blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("p p p p p p p p") +
                        StringUtil.appendNewLine("r n b q k b n r"),
                board.print());
        System.out.println(board.print());
    }

    @Test
    public void testPieceCount() {
        board.initialize();
        final char whitePawnRepresentation = Piece.PAWN_CHAR;
        final char blackPawnRepresentation = Character.toUpperCase(Piece.PAWN_CHAR);
        final char whiteRookRepresentation = Piece.ROOK_CHAR;
        final char blackKingRepresentation = Character.toUpperCase(Piece.KING_CHAR);

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
        final double constraint = 0.05;
        assertEquals(0.0, board.getWhiteStrength(), constraint);
        assertEquals(0.0, board.getBlackStrength(), constraint);

        board.putPiece("g4", Piece.createWhiteQueen());
        board.putPiece("c8", Piece.createBlackRook());

        assertEquals(9.0, board.getWhiteStrength(), constraint);
        assertEquals(5.0, board.getBlackStrength(), constraint);

        
    }
}
