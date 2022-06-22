package chess;

import chess.pieces.Piece;
import util.StringUtil;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provides representation of a chess board
 */
public class Board implements Iterable<Piece>, Serializable {
    private final Piece[][] ranks = new Piece[8][8];
    private final List<Piece> whitePieces = new ArrayList<>();
    private final List<Piece> blackPieces = new ArrayList<>();

    public Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                ranks[i][j] = Piece.noPiece();
        }
    }

    public static Board fromString(String boardString) {
        Board board = new Board();
        List<List<Character>> representations =
                boardString.lines().map(StringUtil::getInitials).toList();

        if (representations.size() != 8) return null;

        for (int i = 7; i >= 0; i--) {
            Piece[] rank = board.ranks[i];
            List<Character> rankChars = representations.get(7-i);

            for (int j = 0; j < 8; j++) {
                char ch = rankChars.get(j);
                boolean isLower = Character.isLowerCase(ch);
                rank[j] = (isLower) ? readWhitePiece(ch) : readBlackPiece(ch);
            }
        }
        return board;
    }
    private static Piece readWhitePiece(char ch) {
        return switch (ch) {
            case 'b' -> Piece.createWhiteBishop();
            case 'k' -> Piece.createWhiteKing();
            case 'n' -> Piece.createWhiteKnight();
            case 'p' -> Piece.createWhitePawn();
            case 'q' -> Piece.createWhiteQueen();
            case 'r' -> Piece.createWhiteRook();
            default  -> Piece.noPiece();
        };
    }
    private static Piece readBlackPiece(char ch) {
        return switch (ch) {
            case 'B' -> Piece.createBlackBishop();
            case 'K' -> Piece.createBlackKing();
            case 'N' -> Piece.createBlackKnight();
            case 'P' -> Piece.createBlackPawn();
            case 'Q' -> Piece.createBlackQueen();
            case 'R' -> Piece.createBlackRook();
            default  -> Piece.noPiece();
        };
    }

    public void initialize() {
        whitePieces.clear();
        blackPieces.clear();

        initWhiteRanks();
        initBlackRanks();
    }

    private void initBlackRanks() {
        for (int i = 0; i < 8; i++)
            ranks[6][i] = Piece.createBlackPawn();

        ranks[7][0] = Piece.createBlackRook();
        ranks[7][1] = Piece.createBlackKnight();
        ranks[7][2] = Piece.createBlackBishop();
        ranks[7][3] = Piece.createBlackQueen();
        ranks[7][4] = Piece.createBlackKing();
        ranks[7][5] = Piece.createBlackBishop();
        ranks[7][6] = Piece.createBlackKnight();
        ranks[7][7] = Piece.createBlackRook();

        blackPieces.addAll(List.of(ranks[6]));
        blackPieces.addAll(List.of(ranks[7]));

        Collections.sort(blackPieces);
    }
    private void initWhiteRanks() {
        ranks[0][0] = Piece.createWhiteRook();
        ranks[0][1] = Piece.createWhiteKnight();
        ranks[0][2] = Piece.createWhiteBishop();
        ranks[0][3] = Piece.createWhiteQueen();
        ranks[0][4] = Piece.createWhiteKing();
        ranks[0][5] = Piece.createWhiteBishop();
        ranks[0][6] = Piece.createWhiteKnight();
        ranks[0][7] = Piece.createWhiteRook();

        for (int i = 0; i < 8; i++)
            ranks[1][i] = Piece.createWhitePawn();

        whitePieces.addAll(List.of(ranks[0]));
        whitePieces.addAll(List.of(ranks[1]));

        Collections.sort(whitePieces);
    }

    public int getCountWhite() {
        return whitePieces.size();
    }
    public int getCountBlack() {
        return blackPieces.size();
    }

    public int pieceCount() {
        return whitePieces.size() + blackPieces.size();
    }

    public int pieceCount(Piece piece) {
        int count = 0;

        for (Piece p : this)
            if (piece.isEqualTo(p))
                count++;

        return count;
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

    private String printRank(Piece[] rank) {
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
            printedBoard.append(StringUtil.appendNewLine(printRank(ranks[rank])));

        return  printedBoard.toString();
    }

    public Piece getPiece(String position) {
        int file = position.charAt(0) - 'a';
        int rank = position.charAt(1) - '1';

        return ranks[rank][file];
    }

    public void put(String position, Piece piece) {
        int file = position.charAt(0) - 'a';
        int rank = position.charAt(1) - '1';

        ranks[rank][file] = piece;
        piece.setPosition(position);

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

    @Override
    public Iterator<Piece> iterator() {
        return new BoardIterator();
    }

    class BoardIterator implements Iterator<Piece> {
        private int rank;
        private int file;

        public BoardIterator() {
            this.rank = 7;
            this.file = 0;
        }

        public boolean hasNext() {
            return (this.rank >= 0);
        }

        public Piece next() {
            Piece current = ranks[rank][file];

            nextCoordinates();

            return current;
        }

        private void nextCoordinates() {
            do {
                file++;
                if (file >= 8) {
                    file = 0;
                    rank--;
                }
            } while (hasNext() && ranks[rank][file].isEmpty());
        }
    }
}