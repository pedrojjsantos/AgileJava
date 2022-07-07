package chess.pieces;

import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class NoPiece extends Piece {
    NoPiece() {
        super(Color.WHITE, '.', 0.0);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isBlack() {
        return false;
    }

    @Override
    public boolean isWhite() {
        return false;
    }

    @Override
    public List<Position> getPossibleMoves(Position pos) {
        return new ArrayList<>();
    }
}
