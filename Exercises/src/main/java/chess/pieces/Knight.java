package chess.pieces;

import chess.Board;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    Knight(Color color) {
        super(color, 'n', 2.5);
    }

    @Override
    public List<Position> getPossibleMoves(Position pos) {
        ArrayList<Position> moves = new ArrayList<>();

        if (Board.isValidPosition(pos)) {
            int file = pos.getFile();
            int rank = pos.getRank();

            moves.add(new Position(file + 1, rank + 2));
            moves.add(new Position(file - 1, rank + 2));
            moves.add(new Position(file + 1, rank - 2));
            moves.add(new Position(file - 1, rank - 2));

            moves.add(new Position(file + 2, rank + 1));
            moves.add(new Position(file + 2, rank - 1));
            moves.add(new Position(file - 2, rank + 1));
            moves.add(new Position(file - 2, rank - 1));

            moves.removeIf(position -> !Board.isValidPosition(position));
        }

        return moves;
    }
}
