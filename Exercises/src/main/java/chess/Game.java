package chess;

import chess.pieces.Piece;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;

    Game() {
        board = new Board();
    }

    public void initialize() {
        board.initialize();
    }

    public int pieceCount() {
        return board.pieceCount();
    }

    public String printBoard() {
        return board.print();
    }

    public void putPiece(String pos, Piece piece) {
        board.put(pos, piece);
    }

    public Piece getPiece(String pos) {
        return board.getPiece(pos);
    }

    public double getWhiteStrength() {
        double strength = 0;

        for (Piece piece : board.getWhitePieces()) {
            if (piece.getType() != Piece.Type.PAWN)
                strength += piece.getStrength();
        }

        strength += getWhitePawnsStrength();

        return strength;
    }

    private double getWhitePawnsStrength() {
        double strengthCount = 0;

        for (int file = 0; file < 8; file++) {
            int nPawnsInFile = board.pieceCountInFile(file, Piece.createWhitePawn());
            double pawnStrength = (nPawnsInFile > 1) ? 0.5 : 1.0;

            strengthCount += pawnStrength * nPawnsInFile;
        }
        return strengthCount;
    }

    public double getBlackStrength() {
        double strength = 0;

        for (Piece piece : board.getBlackPieces()) {
            if (piece.getType() != Piece.Type.PAWN)
                strength += piece.getStrength();
        }

        strength += getBlackPawnsStrength();

        return strength;
    }

    private double getBlackPawnsStrength() {
        double strengthCount = 0;

        for (int file = 0; file < 8; file++) {
            int nPawnsInFile = board.pieceCountInFile(file, Piece.createBlackPawn());
            double pawnStrength = (nPawnsInFile > 1) ? 0.5 : 1.0;

            strengthCount += pawnStrength * nPawnsInFile;
        }

        return strengthCount;
    }
    public List<String> getPossibleMoves(String pos) {
        Piece piece = getPiece(pos);

        return switch (piece.getType()) {
            case KING  -> possibleKingMoves(pos);
            case QUEEN -> possibleQueenMoves(pos);
            default    -> new ArrayList<String>();
        };
    }

    private List<String> possibleKingMoves(String pos) {
        ArrayList<String> moves = new ArrayList<>();

        if (Board.isValidPosition(pos)) {
            char file = pos.charAt(0);
            char rank = pos.charAt(1);

            moves.add(StringUtil.join2Chars(file + 1, rank + 1));
            moves.add(StringUtil.join2Chars(file + 1, rank));
            moves.add(StringUtil.join2Chars(file + 1, rank - 1));
            moves.add(StringUtil.join2Chars(file, rank + 1));
            moves.add(StringUtil.join2Chars(file, rank - 1));
            moves.add(StringUtil.join2Chars(file - 1, rank + 1));
            moves.add(StringUtil.join2Chars(file - 1, rank));
            moves.add(StringUtil.join2Chars(file - 1, rank - 1));

            moves.removeIf(position -> !Board.isValidPosition(position));
        }

        return moves;
    }

    private List<String> possibleQueenMoves(String pos) {
        ArrayList<String> moves = new ArrayList<>();

        if (Board.isValidPosition(pos)) {
            moves.addAll(board.getFilePositionsAt(pos));
            moves.addAll(board.getRankPositionsAt(pos));
            moves.addAll(board.getDiagonalPositionsAt(pos));

            moves.removeIf(p -> p.equals(pos));
        }

        return moves;
    }

}