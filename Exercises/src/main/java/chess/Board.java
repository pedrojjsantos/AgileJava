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

    public void put(String position, Piece piece) {
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

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }
    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public static boolean isValidPosition(String pos) {
        if (pos.length() < 2) return false;

        int file = pos.charAt(0) - 'a';
        int rank = pos.charAt(1) - '1';

        return file >= 0 && file < 8 && rank >= 0 && rank < 8;
    }

    public int pieceCountInFile(int file, Piece piece) {
        List<String> filePositions = getFilePositionsAt(StringUtil.join2Chars('a' + file, '1'));
        int count = 0;
        for (String pos : filePositions) {
            if (getPiece(pos).isEqualTo(piece))
                count++;
        }
        return count;
    }

    public List<String> getFilePositionsAt(String pos) {
        ArrayList<String> positions = new ArrayList<>();

        if (isValidPosition(pos)) {
            char file = pos.charAt(0);

            for (int i = 0; i < 8; i++)
                positions.add(StringUtil.join2Chars(file, i + '1'));
        }
        return positions;
    }

    public List<String> getRankPositionsAt(String pos) {
        ArrayList<String> positions = new ArrayList<>();

        if (isValidPosition(pos)){
            char rank = pos.charAt(1);

            for (int i = 0; i < 8; i++)
                positions.add(StringUtil.join2Chars(i + 'a', rank));
        }
        return positions;
    }

    public List<String> getDiagonalPositionsAt(String pos) {
        ArrayList<String> positions = new ArrayList<>();

        if (Board.isValidPosition(pos)) {
            char file = pos.charAt(0);
            char rank = pos.charAt(1);

            // First diagonal
            for (int i = 0; i <= 8; i++) {
                if (file + i > 'h' || rank + i > '8') break;
                positions.add(StringUtil.join2Chars(file+i, rank+i));
            }
            for (int i = 1; i <= 8; i++) {
                if (file - i < 'a' || rank - i < '1') break;
                positions.add(StringUtil.join2Chars(file-i, rank-i));
            }

            // Second Diagonal
            for (int i = 1; i <= 8; i++) {
                if (file + i > 'h' || rank - i < '1') break;
                positions.add(StringUtil.join2Chars(file+i, rank-i));
            }
            for (int i = 1; i <= 8; i++) {
                if (file - i < 'a' || rank + i > '8') break;
                positions.add(StringUtil.join2Chars(file-i, rank+i));
            }
        }

        return positions;
    }
}