package chess;

import chess.pieces.Piece;

import java.io.*;

public class Game {
    private final Board board;

    Game() {
        board = new Board();
    }

    public void initialize() {
        board.initialize();
    }

    public int pieceCount() {
        return board.pieceCount();
    }

    public int getCountWhite() {
        return board.getCountWhite();
    }
    public int getCountBlack() {
        return board.getCountBlack();
    }

    public String printBoard() {
        return board.print();
    }

    public void putPiece(String pos, Piece piece) {
        board.put(pos, piece);
    }

    public Piece getPiece(String pos) {
        return board.getPiece(pos);
    }

    public double getWhiteStrength() {
        double strength = 0;
        Piece pawn = Piece.createWhitePawn();

        for (Piece piece : board.getWhitePieces()) {
            if (!piece.isEqualTo(pawn))
                strength += piece.getStrength();
        }

        strength += getWhitePawnsStrength();

        return strength;
    }

    private double getWhitePawnsStrength() {
        double strengthCount = 0;

        for (int file = 0; file < 8; file++) {
            int nPawnsInFile = board.pieceCountInFile(file, Piece.createWhitePawn());
            double pawnStrength = (nPawnsInFile > 1) ? 0.5 : 1.0;

            strengthCount += pawnStrength * nPawnsInFile;
        }
        return strengthCount;
    }

    public double getBlackStrength() {
        double strength = 0;
        Piece pawn = Piece.createBlackPawn();

        for (Piece piece : board.getBlackPieces()) {
            if (!piece.isEqualTo(pawn))
                strength += piece.getStrength();
        }

        strength += getBlackPawnsStrength();

        return strength;
    }

    private double getBlackPawnsStrength() {
        double strengthCount = 0;

        for (int file = 0; file < 8; file++) {
            int nPawnsInFile = board.pieceCountInFile(file, Piece.createBlackPawn());
            double pawnStrength = (nPawnsInFile > 1) ? 0.5 : 1.0;

            strengthCount += pawnStrength * nPawnsInFile;
        }

        return strengthCount;
    }

    public void saveSerialized(String filename) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(board);
        }
    }
}