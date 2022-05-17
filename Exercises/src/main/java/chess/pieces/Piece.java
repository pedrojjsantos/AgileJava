package chess.pieces;

public class Piece {
    // Colors
    public final static String WHITE = "white";
    public final static String BLACK = "black";
    // Names
    public final static String PAWN = "pawn";
    public final static String KNIGHT = "knight";
    public final static String ROOK = "rook";
    public final static String BISHOP = "bishop";
    public final static String QUEEN = "queen";
    public final static String KING = "king";

    private static int countWhite;
    private static int countBlack;

    private final String name;
    private final String color;
    private final char printableRef;

    private Piece(String color, String type, char printableRef) {
        this.color = color.toLowerCase();
        this.name = type.toLowerCase();
        this.printableRef = printableRef;
    }

    public static void resetCount() {
        Piece.countWhite = 0;
        Piece.countBlack = 0;
    }
    public static void incrementCountWhite() {
        Piece.countWhite++;
    }
    public static void incrementCountBlack() {
        Piece.countBlack++;
    }

    public static int getCountWhite() {
        return countWhite;
    }
    public static int getCountBlack() {
        return countBlack;
    }

    public static Piece createWhite(String name, char printableRef) {
        Piece.incrementCountWhite();
        return new Piece(WHITE, name, printableRef);
    }
    public static Piece createBlack(String name, char printableRef) {
        Piece.incrementCountBlack();
        return new Piece(BLACK, name, printableRef);
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public char print() {
        return printableRef;
    }
}
