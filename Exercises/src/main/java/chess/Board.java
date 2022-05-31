package chess;

import chess.pieces.Piece;
import util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
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

        Collections.sort(blackPieces);
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

        Collections.sort(whitePieces);
    }
    
    public int pieceCount() {
        return whitePieces.size() + blackPieces.size();
    }

    public int pieceCount(Piece piece) {
        int count = 0;

        for (List<Piece> rank : ranks) {
            for (Piece p : rank) {
                if (piece.isEqualTo(p))
                    count++;
            }
        }

        return count;
    }

    private int pieceCountInFile(int file, Piece piece) {
        int count = 0;

        for (int i = 0; i < 8; i++) {
            if (ranks.get(i).get(file).isEqualTo(piece))
                count++;
        }

        return count;
    }

    private String printRank(List<Piece> rank) {
        StringBuilder buffer = new StringBuilder();
        for (Piece piece : rank) {
            char representation = piece.print();
            if (piece.isBlack())
                representation = Character.toUpperCase(representation);

            buffer.append(representation).append(' ');
        }
        buffer.setLength(buffer.length() - 1);
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

        if (piece.isWhite()) {
            whitePieces.add(piece);
            Collections.sort(whitePieces);
        }
        else {
            blackPieces.add(piece);
            Collections.sort(blackPieces);
        }
    }

    public double getWhiteStrength() {
        double strength = 0;

        for (Piece piece : whitePieces) {
            if (piece.getType() != Piece.Type.PAWN)
                strength += piece.getStrength();
        }

        strength += getWhitePawnsStrength();

        return strength;
    }

    private double getWhitePawnsStrength() {
        double strengthCount = 0;

        for (int file = 0; file < 8; file++) {
            int nPawnsInFile = pieceCountInFile(file, Piece.createWhitePawn());
            double pawnStrength = (nPawnsInFile > 1) ? 0.5 : 1.0;

            strengthCount += pawnStrength * nPawnsInFile;
        }
        return strengthCount;
    }

    public double getBlackStrength() {
        double strength = 0;

        for (Piece piece : blackPieces) {
            if (piece.getType() != Piece.Type.PAWN)
                strength += piece.getStrength();
        }

        strength += getBlackPawnsStrength();

        return strength;
    }

    private double getBlackPawnsStrength() {
        double strengthCount = 0;

        for (int file = 0; file < 8; file++) {
            int nPawnsInFile = pieceCountInFile(file, Piece.createBlackPawn());
            double pawnStrength = (nPawnsInFile > 1) ? 0.5 : 1.0;

            strengthCount += pawnStrength * nPawnsInFile;
        }

        return strengthCount;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }
    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public void moveKing(String currentPos, String nextPos) {
        if (isValidKingMovement(currentPos, nextPos)) {
            Piece king = getPiece(currentPos);
            putPiece(nextPos, king);
            putPiece(currentPos, Piece.noPiece());
        }
    }

    private boolean isValidKingMovement(String currentPos, String nextPos) {
        if (!isValidPosition(currentPos) || !isValidPosition(nextPos))
            return false;

        int fileCurrPos = currentPos.charAt(0) - 'a';
        int rankCurrPos = currentPos.charAt(1) - '1';
        int fileNextPos = nextPos.charAt(0) - 'a';
        int rankNextPos = nextPos.charAt(1) - '1';

        return Math.abs(fileCurrPos - fileNextPos) == 1 || Math.abs(rankCurrPos - rankNextPos) == 1;
    }

    private boolean isValidPosition(String pos) {
        if (pos.length() < 2) return false;

        int file = pos.charAt(0) - 'a';
        int rank = pos.charAt(1) - '1';

        return file >= 0 && file < 8 && rank >= 0 && rank < 8;
    }
}