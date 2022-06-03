package chess.pieces;

import chess.Board;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    King(Color color) {
        super(color, Type.KING, 0.0);
    }

    public List<String> getPossibleMoves(String pos, Board board) {
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
}
