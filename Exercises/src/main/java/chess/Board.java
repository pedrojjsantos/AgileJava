package chess;

import chess.pieces.Piece;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides representation of a chess board
 */
public class Board {
    private List<List<Piece>> ranks = new ArrayList<>(8);


    public Board() {
        Piece.resetCount();

        for (int i = 0; i < 8; i++) {
            ranks.add(new ArrayList<>(8));

            for (int j = 0; j < 8; j++)
                ranks.get(i).add(Piece.noPiece());
        }
    }

    public void initialize() {
        Piece.resetCount();

        initWhiteRanks();
        initBlankRanks();
        initBlackRanks();
    }

    private void initBlackRanks() {
        for (int i = 0; i < 8; i++)
            ranks.get(6).set(i, Piece.createBlackPawn());

        ranks.get(7).set(0, Piece.createBlackRook());
        ranks.get(7).set(1, Piece.createBlackKnight());
        ranks.get(7).set(2, Piece.createBlackBishop());
        ranks.get(7).set(3, Piece.createBlackQueen());
        ranks.get(7).set(4, Piece.createBlackKing());
        ranks.get(7).set(5, Piece.createBlackBishop());
        ranks.get(7).set(6, Piece.createBlackKnight());
        ranks.get(7).set(7, Piece.createBlackRook());
    }
    private void initBlankRanks() {
        for (int rank = 2; rank < 6; rank++) {
            for (int i = 0; i < 8; i++)
                ranks.get(rank).set(i, Piece.noPiece());
        }
    }
    private void initWhiteRanks() {
        ranks.get(0).set(0, Piece.createWhiteRook());
        ranks.get(0).set(1, Piece.createWhiteKnight());
        ranks.get(0).set(2, Piece.createWhiteBishop());
        ranks.get(0).set(3, Piece.createWhiteQueen());
        ranks.get(0).set(4, Piece.createWhiteKing());
        ranks.get(0).set(5, Piece.createWhiteBishop());
        ranks.get(0).set(6, Piece.createWhiteKnight());
        ranks.get(0).set(7, Piece.createWhiteRook());

        for (int i = 0; i < 8; i++)
            ranks.get(1).set(i, Piece.createWhitePawn());
    }

    /**
     * @return The number of pieces added to the board
     */
    public int pieceCount() {
        return Piece.getCountWhite() + Piece.getCountBlack();
    }

    public int pieceCount(char representation) {
        int count = 0;

        for (List<Piece> rank : ranks)
            count += pieceCountInRank(rank, representation);

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

    public String printRank(List<Piece> rank) {
        StringBuilder buffer = new StringBuilder();
        for (Piece p : rank)
            buffer.append(p.print());
        return buffer.toString();
    }

    public String print() {
        StringBuilder printedBoard = new StringBuilder();

        for (int rank = 7; rank >= 0; rank--)
            printedBoard.append(StringUtil.appendNewLine(printRank(ranks.get(rank))));

        return  printedBoard.toString();
    }

    public Piece getPiece(String position) {
        int index = position.charAt(0) - 'a';
        int rankNumber = position.charAt(1) - '1';

        return ranks.get(rankNumber).get(index);
    }
}

