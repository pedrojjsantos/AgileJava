package chess.pieces;

import chess.Board;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    Bishop(Color color) {
        super(color, 'b', 3.0);
    }

    @Override
    public List<Position> getPossibleMoves(Position pos, Board board) {
        ArrayList<Position> moves = new ArrayList<>();

        if (Board.isValidPosition(pos)){
            moves.addAll(board.getDiagonalPositionsAt(pos));

            moves.removeIf(pos::equals);
        }

        return moves;
    }
}
