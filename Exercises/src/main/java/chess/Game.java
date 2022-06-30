package chess;

import chess.pieces.Piece;
import util.StringUtil;

import java.io.*;

public class Game {
    private Board board;

    public Game() {
        board = new Board();
    }

    public void initialize() {
        initWhiteRanks();
        initBlackRanks();
    }

    private void initWhiteRanks() {
        board.put("a1", Piece.createWhiteRook());
        board.put("b1", Piece.createWhiteKnight());
        board.put("c1", Piece.createWhiteBishop());
        board.put("d1", Piece.createWhiteQueen());
        board.put("e1", Piece.createWhiteKing());
        board.put("f1", Piece.createWhiteBishop());
        board.put("g1", Piece.createWhiteKnight());
        board.put("h1", Piece.createWhiteRook());

        for (int i = 0; i < 8; i++) {
            String position = StringUtil.join2Chars('a'+ i, '2');
            board.put(position, Piece.createWhitePawn());
        }
    }
    private void initBlackRanks() {
        board.put("a8", Piece.createBlackRook());
        board.put("b8", Piece.createBlackKnight());
        board.put("c8", Piece.createBlackBishop());
        board.put("d8", Piece.createBlackQueen());
        board.put("e8", Piece.createBlackKing());
        board.put("f8", Piece.createBlackBishop());
        board.put("g8", Piece.createBlackKnight());
        board.put("h8", Piece.createBlackRook());

        for (int i = 0; i < 8; i++) {
            String position = StringUtil.join2Chars('a'+ i, '7');
            board.put(position, Piece.createBlackPawn());
        }
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
            double pawnStrength = (nPawnsInFile > 1) ? 0.5 : 1.0;

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
                saveFile.write(' ');
                saveFile.write(piece.getPosition());
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
                    saveFile.read();
                    String position = saveFile.readLine();

                    newBoard.put(position, Piece.fromChar(piece));
                }

                this.board = newBoard;
            } catch (Exception e) {
                throw new InvalidSaveFileException(e);
            }
        }
    }
}