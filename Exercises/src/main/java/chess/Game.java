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
            int nPawnsInFile = pieceCountInFile(file, Piece.createWhitePawn());
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
            int nPawnsInFile = pieceCountInFile(file, Piece.createBlackPawn());
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
            char file = pos.charAt(0);
            char rank = pos.charAt(1);

            // Horizontal and Vertical movement
            for (int i = 0; i < 8; i++) {
                if (i + '1' != rank)
                    moves.add(StringUtil.join2Chars(file, i + '1'));
                if (i + 'a' != file)
                    moves.add(StringUtil.join2Chars(i + 'a', rank));
            }

            // First diagonal
            for (int i = 1; i <= 8; i++) {
                if (file + i > 'h' || rank + i > '8') break;
                moves.add(StringUtil.join2Chars(file+i, rank+i));
            }
            for (int i = 1; i <= 8; i++) {
                if (file - i < 'a' || rank - i < '1') break;
                moves.add(StringUtil.join2Chars(file-i, rank-i));
            }

            // Second Diagonal
            for (int i = 1; i <= 8; i++) {
                if (file + i > 'h' || rank - i < '1') break;
                moves.add(StringUtil.join2Chars(file+i, rank-i));
            }
            for (int i = 1; i <= 8; i++) {
                if (file - i < 'a' || rank + i > '8') break;
                moves.add(StringUtil.join2Chars(file-i, rank+i));
            }
        }

        return moves;
    }

}