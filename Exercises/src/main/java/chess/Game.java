package chess;

import chess.pieces.Piece;

public class Game {
    private Board board;

    Game() {
        board = new Board();
    }


    public int pieceCount() {
        return board.pieceCount();
    }

    public String printBoard() {
        return board.print();
    }

    public void addPiece(String pos, Piece piece) {
        board.putPiece(pos, piece);
    }

    public Piece getPiece(String pos) {
        return board.getPiece(pos);
    }
}
