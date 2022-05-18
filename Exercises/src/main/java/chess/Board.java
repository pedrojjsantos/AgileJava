package chess;

import chess.pieces.Piece;
import util.StringUtil;

import java.util.ArrayList;

/**
 * Provides representation of a chess board
 */
public class Board {
    private ArrayList<Piece> rank1 = new ArrayList<>();
    private ArrayList<Piece> rank2 = new ArrayList<>();
    private ArrayList<Piece> rank3 = new ArrayList<>();
    private ArrayList<Piece> rank4 = new ArrayList<>();
    private ArrayList<Piece> rank5 = new ArrayList<>();
    private ArrayList<Piece> rank6 = new ArrayList<>();
    private ArrayList<Piece> rank7 = new ArrayList<>();
    private ArrayList<Piece> rank8 = new ArrayList<>();



    public Board() {}
    public void initialize() {
        Piece.resetCount();
        rank1.add(Piece.createWhite(Piece.Type.ROOK,   'r'));
        rank1.add(Piece.createWhite(Piece.Type.KNIGHT, 'n'));
        rank1.add(Piece.createWhite(Piece.Type.BISHOP, 'b'));
        rank1.add(Piece.createWhite(Piece.Type.QUEEN,  'q'));
        rank1.add(Piece.createWhite(Piece.Type.KING,   'k'));
        rank1.add(Piece.createWhite(Piece.Type.BISHOP, 'b'));
        rank1.add(Piece.createWhite(Piece.Type.KNIGHT, 'n'));
        rank1.add(Piece.createWhite(Piece.Type.ROOK,   'r'));
// Q3
        for (int i = 0; i < 8; i++)
            rank2.add(Piece.createWhite(Piece.Type.PAWN, 'p'));
        for (int i = 0; i < 8; i++)
            rank7.add(Piece.createBlack(Piece.Type.PAWN, 'P'));

        rank8.add(Piece.createBlack(Piece.Type.ROOK,   'R'));
        rank8.add(Piece.createBlack(Piece.Type.KNIGHT, 'N'));
        rank8.add(Piece.createBlack(Piece.Type.BISHOP, 'B'));
        rank8.add(Piece.createBlack(Piece.Type.QUEEN,  'Q'));
        rank8.add(Piece.createBlack(Piece.Type.KING,   'K'));
        rank8.add(Piece.createBlack(Piece.Type.BISHOP, 'B'));
        rank8.add(Piece.createBlack(Piece.Type.KNIGHT, 'N'));
        rank8.add(Piece.createBlack(Piece.Type.ROOK,   'R'));
    }
    /**
     * @return The number of pieces added to the board
     */
    public int pieceCount() {
        return Piece.getCountWhite() + Piece.getCountBlack();
    }

    public String printRank(ArrayList<Piece> rank) {
        StringBuilder buffer = new StringBuilder();
        for (Piece p : rank)
            buffer.append(p.print());
        return buffer.toString();
    }

    public String print() {
        final String blankRank = StringUtil.appendNewLine("........");
        return  StringUtil.appendNewLine(printRank(rank8)) +
                StringUtil.appendNewLine(printRank(rank7)) +
                blankRank + blankRank +
                blankRank + blankRank +
                StringUtil.appendNewLine(printRank(rank2)) +
                StringUtil.appendNewLine(printRank(rank1));
    }
}
