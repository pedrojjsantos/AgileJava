package chess;

import chess.pieces.Piece;
import util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides representation of a chess board
 */
public class Board {
    private final List<List<Piece>> ranks = new ArrayList<>(8);
    private final List<Piece> whitePieces = new ArrayList<>();
    private final List<Piece> blackPieces = new ArrayList<>();


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

        updatePiecesStrength();
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

        blackPieces.addAll(ranks.get(6));
        blackPieces.addAll(ranks.get(7));
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

        whitePieces.addAll(ranks.get(0));
        whitePieces.addAll(ranks.get(1));
    }

    /**
     * @return The number of pieces added to the board
     */
    public int pieceCount() {
        return Piece.getCountWhite() + Piece.getCountBlack();
    }

    public int pieceCount(char representation) {
        int count = 0;

        for (List<Piece> rank : ranks) {
            for (Piece piece : rank) {
                if (piece.print() == representation)
                    count++;
            }
        }

        return count;
    }

    private int pieceCountInFile(int file, char representation) {
        int count = 0;

        for (int i = 0; i < 8; i++) {
            if (ranks.get(i).get(file).print() == representation)
                count++;
        }
        return count;
    }

    private String printRank(List<Piece> rank) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 7; i++)
            buffer.append(rank.get(i).print()).append(' ');
        buffer.append(rank.get(7).print());
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

    public void putPiece(String position, Piece piece) {
        int index = position.charAt(0) - 'a';
        int rankNumber = position.charAt(1) - '1';

        ranks.get(rankNumber).set(index, piece);

        if (Character.isLowerCase(piece.print()))
            whitePieces.add(piece);
        else
            blackPieces.add(piece);
    }

    public double getWhiteStrength() {
        double strength = 0;
        updatePiecesStrength();

        for (Piece piece : whitePieces)
            strength += piece.getStrength();

        return strength;
    }

    public double getBlackStrength() {
        double strength = 0;
        updatePiecesStrength();

        for (Piece piece : blackPieces)
            strength += piece.getStrength();

        return strength;
    }

    private void updatePiecesStrength() {
        for (Piece piece : whitePieces)
            assignStrengthTo(piece);
        for (Piece piece : blackPieces)
            assignStrengthTo(piece);

        for (int file = 0; file < 8; file++) {
            int numberOfWhitePawns = pieceCountInFile(file, Piece.PAWN_CHAR);
            int numberOfBlackPawns = pieceCountInFile(file, Character.toUpperCase(Piece.PAWN_CHAR));

            assignStrengthToPawns(file, numberOfWhitePawns, numberOfBlackPawns);
        }

        Collections.sort(whitePieces);
        Collections.sort(blackPieces);
    }

    private void assignStrengthToPawns(int file, int numberOfWhitePawns, int numberOfBlackPawns) {
        double strength = 1;
        if (numberOfWhitePawns > 1)
            strength = 0.5;

        for (int index = 0; index < 8; index++) {
            if (ranks.get(index).get(file).print() == Piece.PAWN_CHAR)
                ranks.get(index).get(file).setStrength(strength);
        }

        strength = 1;
        if (numberOfBlackPawns > 1)
            strength = 0.5;

        for (int index = 0; index < 8; index++) {
            if (ranks.get(index).get(file).print() == Character.toUpperCase(Piece.PAWN_CHAR))
                ranks.get(index).get(file).setStrength(strength);
        }
    }


    private void assignStrengthTo(Piece piece) {
        double strength = switch (piece.getType()) {
            case ROOK   -> 5.0;
            case KNIGHT -> 2.5;
            case BISHOP -> 3.0;
            case QUEEN  -> 9.0;
            default     -> 0.0;
        };
        piece.setStrength(strength);
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }
    public List<Piece> getBlackPieces() {
        return blackPieces;
    }
}

