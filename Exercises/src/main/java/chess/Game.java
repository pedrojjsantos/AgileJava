package chess;

import chess.pieces.Piece;
import util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private final Board board;

    Game() {
        board = new Board();
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