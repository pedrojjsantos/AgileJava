package chess;

import chess.pieces.Piece;
import util.StringUtil;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provides representation of a chess board
 */
public class Board implements Iterable<Piece>, Serializable {
    private Piece[][] ranks = new Piece[8][8];
    private TreeSet<Piece> whitePieces;
    private TreeSet<Piece> blackPieces;

    public Board() {
        clearBoard();
    }

    public void clearBoard() {
        for (int i = 0; i < 8; i++)
            Arrays.setAll(ranks[i], j -> Piece.noPiece());

        whitePieces = new TreeSet<>();
        blackPieces = new TreeSet<>();
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
        List<Position> filePositions = getFilePositionsAt(new Position(file, 0));
        int count = 0;
        for (Position pos : filePositions) {
            if (getPiece(pos).isEqualTo(piece))
                count++;
        }
        return count;
    }

    private String printRank(Piece[] rank) {
        StringBuilder buffer = new StringBuilder();

        for (Piece piece : rank)
            buffer.append(piece.print()).append(' ');

        buffer.setLength(buffer.length() - 1);
        return buffer.toString();
    }

    public String print() {
        StringBuilder printedBoard = new StringBuilder();

        for (int rank = 7; rank >= 0; rank--)
            printedBoard.append(StringUtil.appendNewLine(printRank(ranks[rank])));

        return  printedBoard.toString();
    }

    public Piece getPiece(Position pos) {
        return ranks[pos.getRank()][pos.getFile()];
    }

    public void put(Position pos, Piece piece) {
        ranks[pos.getRank()][pos.getFile()] = piece;
        piece.setPosition(pos);

        if (piece.isWhite())
            whitePieces.add(piece);
        else if (piece.isBlack())
            blackPieces.add(piece);
    }

    public Collection<Piece> getWhitePieces() {
        return whitePieces;
    }

    public Collection<Piece> getBlackPieces() {
        return blackPieces;
    }

    public static boolean isValidPosition(Position pos) {
        int file = pos.getFile();
        int rank = pos.getRank();

        return file >= 0 && file < 8 && rank >= 0 && rank < 8;
    }

    public List<Position> getFilePositionsAt(Position pos) {
        ArrayList<Position> positions = new ArrayList<>();

        if (isValidPosition(pos)) {
            int file = pos.getFile();

            for (int rank = 0; rank < 8; rank++)
                positions.add(new Position(file, rank));
        }
        return positions;
    }

    public List<Position> getRankPositionsAt(Position pos) {
        ArrayList<Position> positions = new ArrayList<>();

        if (isValidPosition(pos)){
            int rank = pos.getRank();

            for (int file = 0; file < 8; file++)
                positions.add(new Position(file, rank));
        }
        return positions;
    }

    public List<Position> getDiagonalPositionsAt(Position pos) {
        ArrayList<Position> positions = new ArrayList<>();

        if (Board.isValidPosition(pos)) {
            int file = pos.getFile();
            int rank = pos.getRank();

            // First diagonal
            for (int i = 0; i <= 8; i++) {
                if (file + i > 7 || rank + i > 7) break;
                positions.add(new Position(file+i, rank+i));
            }
            for (int i = 1; i <= 8; i++) {
                if (file - i < 0 || rank - i < 0) break;
                positions.add(new Position(file-i, rank-i));
            }

            // Second Diagonal
            for (int i = 1; i <= 8; i++) {
                if (file + i > 7 || rank - i < 0) break;
                positions.add(new Position(file+i, rank-i));
            }
            for (int i = 1; i <= 8; i++) {
                if (file - i < 0 || rank + i > 7) break;
                positions.add(new Position(file-i, rank+i));
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