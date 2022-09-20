package chess;

import chess.pieces.Piece;

import java.io.*;
import java.util.Collection;

public class Game {
    private Board board;

    public Game() {
        board = new Board();
    }

    public void initialize() {
        board.clearBoard();
        initWhiteRanks();
        initBlackRanks();
    }

    private void initWhiteRanks() {
        board.put(new Position("a1"), Piece.createWhiteRook());
        board.put(new Position("b1"), Piece.createWhiteKnight());
        board.put(new Position("c1"), Piece.createWhiteBishop());
        board.put(new Position("d1"), Piece.createWhiteQueen());
        board.put(new Position("e1"), Piece.createWhiteKing());
        board.put(new Position("f1"), Piece.createWhiteBishop());
        board.put(new Position("g1"), Piece.createWhiteKnight());
        board.put(new Position("h1"), Piece.createWhiteRook());

        for (int file = 0; file < 8; file++)
            board.put(new Position(file, 1), Piece.createWhitePawn());
    }
    private void initBlackRanks() {
        board.put(new Position("a8"), Piece.createBlackRook());
        board.put(new Position("b8"), Piece.createBlackKnight());
        board.put(new Position("c8"), Piece.createBlackBishop());
        board.put(new Position("d8"), Piece.createBlackQueen());
        board.put(new Position("e8"), Piece.createBlackKing());
        board.put(new Position("f8"), Piece.createBlackBishop());
        board.put(new Position("g8"), Piece.createBlackKnight());
        board.put(new Position("h8"), Piece.createBlackRook());

        for (int file = 0; file < 8; file++)
            board.put(new Position(file, 6), Piece.createBlackPawn());
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

    public void putPiece(Position pos, Piece piece) {
        board.put(pos, piece);
    }

    public Piece getPiece(Position pos) {
        return board.getPiece(pos);
    }

    private double getStrength(Collection<Piece> pieces) {
        double totalStrength = 0;

        int[] pawnsPerFile = {0,0,0,0,0,0,0,0};

        for (Piece piece : pieces) {
            if (piece.isPawn()) {
                int file = piece.getPosition().getFile();
                pawnsPerFile[file]++;
            } else totalStrength += piece.getStrength();
        }

        totalStrength += getPawnStrength(pawnsPerFile);

        return totalStrength;
    }

    private double getPawnStrength(int[] pawnsPerFile) {
        double totalStrength = 0;
        double pawnStrength = Piece.createBlackPawn().getStrength();

        for (int nPawns : pawnsPerFile) {
            double fileStrength = pawnStrength * nPawns;
            totalStrength += (nPawns > 1) ? fileStrength / 2 : fileStrength;
        }
        return totalStrength;
    }

    public double getWhiteStrength() {
        return getStrength(board.getWhitePieces());
    }

    public double getBlackStrength() {
        return getStrength(board.getBlackPieces());
    }

    public void saveSerialized(String filename) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(board);
        }
    }

    public void loadSerialized(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            this.board = (Board) inputStream.readObject();
        }
    }


    public void saveTextual(String filename) throws IOException{
        try (BufferedWriter saveFile = new BufferedWriter(new FileWriter(filename))) {
            saveFile.write(""+pieceCount());
            saveFile.newLine();

            for (Piece piece : board) {
                saveFile.write(piece.print());
                saveFile.write(piece.getPosition().toString());
                saveFile.newLine();
            }
        }
    }

    public void loadTextual(String filename) throws IOException {
        try (BufferedReader saveFile = new BufferedReader(new FileReader(filename))) {
            try{
                int nLines = Integer.parseInt(saveFile.readLine());

                Board newBoard = new Board();

                for (int i = 0; i < nLines; i++) {
                    char piece = (char) saveFile.read();
                    String position = saveFile.readLine();

                    newBoard.put(new Position(position), Piece.fromChar(piece));
                }

                this.board = newBoard;
            } catch (Exception e) {
                throw new InvalidSaveFileException(e);
            }
        }
    }
}