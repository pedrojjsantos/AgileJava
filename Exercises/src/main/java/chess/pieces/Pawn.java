package chess.pieces;

/**
 * Represents a chess pawn intended to be used with a chess board
 */
public class Pawn {
    public final static String WHITE = "white";
    public final static String BLACK = "black";
    private String color;
    private char printableRef;

    /**
     * Constructs a chess.pieces.Pawn with the default color white
     */
    public Pawn() {
        color = WHITE;
        printableRef = 'p';
    }

    /**
     * Constructs a chess.pieces.Pawn with the given color
     * @param color String representing the color ("white" or "black")
     */
    public Pawn(String color, char printableRef) {
        this.color = color;
        this.printableRef = printableRef;
    }

    /**
     * @return The color of the pawn
     */
    public String getColor() {
        return color;
    }

    public char print() {
        return printableRef;
    }
}
