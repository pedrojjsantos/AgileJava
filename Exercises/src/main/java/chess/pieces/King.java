package chess.pieces;

import chess.Board;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    King(Color color) {
        super(color, 'k', 0.0);
    }

    @Override
    public List<Position> getPossibleMoves(Position pos) {
        ArrayList<Position> moves = new ArrayList<>();

        if (Board.isValidPosition(pos)) {
            int file = pos.getFile();
            int rank = pos.getRank();

            moves.add(new Position(file + 1, rank + 1));
            moves.add(new Position(file + 1, rank));
            moves.add(new Position(file + 1, rank - 1));
            moves.add(new Position(file, rank + 1));
            moves.add(new Position(file, rank - 1));
            moves.add(new Position(file - 1, rank + 1));
            moves.add(new Position(file - 1, rank));
            moves.add(new Position(file - 1, rank - 1));

            moves.removeIf(position -> !Board.isValidPosition(position));
        }

        return moves;
    }
}
