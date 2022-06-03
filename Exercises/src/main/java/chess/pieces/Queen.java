package chess.pieces;

import chess.Board;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{
    Queen(Color color) {
        super(color, Type.QUEEN, 9.0);
    }

    public List<String> getPossibleMoves(String pos, Board board) {
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
