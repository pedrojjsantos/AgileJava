package chess.pieces;

import chess.Board;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    Pawn(Color color) {
        super(color, 'p', 1.0);
    }

    @Override
    public List<Position> getPossibleMoves(Position pos) {
        List<Position> moves = new ArrayList<>();

        int rankDirection = (this.isBlack()) ? -1 : 1;

        int file = pos.getFile();
        int rank = pos.getRank();

        moves.add(new Position(file, rank + rankDirection));

        if (rank == 6 || rank == 1)
            moves.add(new Position(file, rank + 2*rankDirection));

        moves.removeIf(position -> !Board.isValidPosition(position));

        return moves;
    }
}
