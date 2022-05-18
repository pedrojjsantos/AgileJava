package chess.pieces;

public class Piece {
    // Colors
    public final static String WHITE = "white";
    public final static String BLACK = "black";
    // Char representations
    public final static char PAWN_CHAR = 'p';
    public final static char KNIGHT_CHAR = 'n';
    public final static char ROOK_CHAR = 'r';
    public final static char BISHOP_CHAR = 'b';
    public final static char QUEEN_CHAR = 'q';
    public final static char KING_CHAR = 'k';

    private enum Color {WHITE, BLACK}
    public enum Type {PAWN, KNIGHT, ROOK, BISHOP, QUEEN, KING}

    private static int countWhite;
    private static int countBlack;

    private final Type type;
    private final Color color;
    private final char representation;

    private Piece(Color color, Type type, char representation) {
        this.color = color;
        this.type = type;
        this.representation = representation;
    }

    // White Pieces
    public static Piece createWhitePawn() {
        return new Piece(Color.WHITE, Type.PAWN, PAWN_CHAR);
    }
    public static Piece createWhiteKnight() {
        return new Piece(Color.WHITE, Type.KNIGHT, KNIGHT_CHAR);
    }
    public static Piece createWhiteRook() {
        return new Piece(Color.WHITE, Type.ROOK, ROOK_CHAR);
    }
    public static Piece createWhiteBishop() {
        return new Piece(Color.WHITE, Type.BISHOP, BISHOP_CHAR);
    }
    public static Piece createWhiteQueen() {
        return new Piece(Color.WHITE, Type.QUEEN, QUEEN_CHAR);
    }
    public static Piece createWhiteKing() {
        return new Piece(Color.WHITE, Type.KING, KING_CHAR);
    }

    // Black Pieces
    public static Piece createBlackPawn() {
        return new Piece(Color.BLACK, Type.PAWN, Character.toUpperCase(PAWN_CHAR));
    }
    public static Piece createBlackKnight() {
        return new Piece(Color.BLACK, Type.KNIGHT, Character.toUpperCase(KNIGHT_CHAR));
    }
    public static Piece createBlackRook() {
        return new Piece(Color.BLACK, Type.ROOK, Character.toUpperCase(ROOK_CHAR));
    }
    public static Piece createBlackBishop() {
        return new Piece(Color.BLACK, Type.BISHOP, Character.toUpperCase(BISHOP_CHAR));
    }
    public static Piece createBlackQueen() {
        return new Piece(Color.BLACK, Type.QUEEN, Character.toUpperCase(QUEEN_CHAR));
    }
    public static Piece createBlackKing() {
        return new Piece(Color.BLACK, Type.KING, Character.toUpperCase(KING_CHAR));
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

    public static Piece createWhite(Type type, char printableRef) {
        Piece.incrementCountWhite();
        return new Piece(Color.WHITE, type, printableRef);
    }
    public static Piece createBlack(Type type, char printableRef) {
        Piece.incrementCountBlack();
        return new Piece(Color.BLACK, type, printableRef);
    }

    public String getColor() {
        return switch (color) {
            case WHITE -> WHITE;
            case BLACK -> BLACK;
        };
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public Type getType() {
        return type;
    }

    public char print() {
        return representation;
    }
}
