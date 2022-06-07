package chess.pieces;

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
}
