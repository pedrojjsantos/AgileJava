package chess;

import chess.pieces.Piece;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

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
        rank1.add(Piece.createWhiteRook());
        rank1.add(Piece.createWhiteKnight());
        rank1.add(Piece.createWhiteBishop());
        rank1.add(Piece.createWhiteQueen());
        rank1.add(Piece.createWhiteKing());
        rank1.add(Piece.createWhiteBishop());
        rank1.add(Piece.createWhiteKnight());
        rank1.add(Piece.createWhiteRook());

        for (int i = 0; i < 8; i++)
            rank2.add(Piece.createWhitePawn());

        for (int i = 0; i < 8; i++)
            rank3.add(Piece.noPiece());
        for (int i = 0; i < 8; i++)
            rank4.add(Piece.noPiece());
        for (int i = 0; i < 8; i++)
            rank5.add(Piece.noPiece());
        for (int i = 0; i < 8; i++)
            rank6.add(Piece.noPiece());

        for (int i = 0; i < 8; i++)
            rank7.add(Piece.createBlackPawn());

        rank8.add(Piece.createBlackRook());
        rank8.add(Piece.createBlackKnight());
        rank8.add(Piece.createBlackBishop());
        rank8.add(Piece.createBlackQueen());
        rank8.add(Piece.createBlackKing());
        rank8.add(Piece.createBlackBishop());
        rank8.add(Piece.createBlackKnight());
        rank8.add(Piece.createBlackRook());
    }
    /**
     * @return The number of pieces added to the board
     */
    public int pieceCount() {
        return Piece.getCountWhite() + Piece.getCountBlack();
    }

    public int pieceCount(char representation) {
        int count = 0;

        count += pieceCountInRank(rank1, representation);
        count += pieceCountInRank(rank2, representation);
        count += pieceCountInRank(rank3, representation);
        count += pieceCountInRank(rank4, representation);
        count += pieceCountInRank(rank5, representation);
        count += pieceCountInRank(rank6, representation);
        count += pieceCountInRank(rank7, representation);
        count += pieceCountInRank(rank8, representation);

        return count;
    }

    private int pieceCountInRank(List<Piece> rank, char representation) {
        int count = 0;
        for (Piece piece : rank) {
            if (piece.print() == representation)
                count++;
        }
        return count;
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
                StringUtil.appendNewLine(printRank(rank6)) +
                StringUtil.appendNewLine(printRank(rank5)) +
                StringUtil.appendNewLine(printRank(rank4)) +
                StringUtil.appendNewLine(printRank(rank3)) +
                StringUtil.appendNewLine(printRank(rank2)) +
                StringUtil.appendNewLine(printRank(rank1));
    }
}

