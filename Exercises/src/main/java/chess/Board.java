package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;

/**
 * Provides representation of a chess board
 */
public class Board {
    public static final String NEWLINE = System.getProperty("line.separator");
    private ArrayList<Pawn> rank2 = new ArrayList<>();
    private ArrayList<Pawn> rank7 = new ArrayList<>();



    public Board() {
        initialize();
    }
    public void initialize() {
        for (int i = 0; i < 8; i++)
            rank2.add(new Pawn());

        for (int i = 0; i < 8; i++)
            rank7.add(new Pawn(Pawn.BLACK, 'P'));
    }
    /**
     * @return The number of pieces added to the board
     */
    public int getNumberOfPieces() {
        return rank2.size() + rank7.size();
    }

    public String getSecondRankString() {
        StringBuilder buffer = new StringBuilder();
        for (Pawn pawn : rank2)
            buffer.append(pawn.print());
        return buffer.toString();
    }
    public String getSeventhRankString() {
        StringBuilder buffer = new StringBuilder();
        for (Pawn pawn : rank7)
            buffer.append(pawn.print());
        return buffer.toString();
    }

    public String printBoard() {
        return  "........" + NEWLINE +
                getSeventhRankString() + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                getSecondRankString() + NEWLINE +
                "........" + NEWLINE;
    }
}
