package chess.pieces;

import chess.Board;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    Rook(Color color) {
        super(color, 'r', 5.0);
    }

    @Override
    public List<Position> getPossibleMoves(Position pos, Board board) {
        ArrayList<Position> moves = new ArrayList<>();

        if (Board.isValidPosition(pos)){
            moves.addAll(board.getFilePositionsAt(pos));
            moves.addAll(board.getRankPositionsAt(pos));

            moves.removeIf(pos::equals);
        }

        return moves;
    }
}
