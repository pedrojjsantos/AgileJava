package chess;

import chess.pieces.Piece;

import java.io.*;

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

    public double getWhiteStrength() {
        double strength = 0;
        Piece pawn = Piece.createWhitePawn();

        for (Piece piece : board.getWhitePieces()) {
            if (!piece.isEqualTo(pawn))
                strength += piece.getStrength();
        }

        strength += getPawnStrength(pawn);

        return strength;
    }

    public double getBlackStrength() {
        double strength = 0;
        Piece pawn = Piece.createBlackPawn();

        for (Piece piece : board.getBlackPieces()) {
            if (!piece.isEqualTo(pawn))
                strength += piece.getStrength();
        }

        strength += getPawnStrength(pawn);

        return strength;
    }

    private double getPawnStrength(Piece pawn) {
        double totalStrength = 0;

        for (int file = 0; file < 8; file++) {
            int nPawnsInFile = board.pieceCountInFile(file, pawn);
            double pawnStrength = pawn.getStrength();
            pawnStrength = (nPawnsInFile <= 1) ? pawnStrength : pawnStrength / 2;

            totalStrength += pawnStrength * nPawnsInFile;
        }

        return totalStrength;
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