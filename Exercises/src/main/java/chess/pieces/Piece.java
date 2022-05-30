package chess.pieces;

public class Piece implements Comparable<Piece> {
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
    public final static char BLANK_CHAR = '.';


    private enum Color {WHITE, BLACK;}


    // todo: refactor the representations to the enum;
    public enum Type {
        PAWN(1.0),
        KNIGHT(2.5),
        ROOK(5.0),
        BISHOP(3.0),
        QUEEN(9.0),
        KING(0),
        NO_PIECE(0);

        private double strength;
        Type(double strength){
            this.strength = strength;
        }

        private double getStrength() {
            return this.strength;
        }
    }

    private static int countWhite;
    private static int countBlack;

    private final Type type;
    private final Color color;
    private final char representation;
    private double strength = 0;

    private Piece(Color color, Type type, char representation) {
        this.color = color;
        this.type = type;
        this.representation = representation;
    }

    public static Piece noPiece() {
        return new Piece(Color.WHITE, Type.NO_PIECE, BLANK_CHAR);
    }

    private static Piece createPiece(Color color, Type type, char representation) {
        switch (color) {
            case WHITE -> incrementWhiteCount();
            case BLACK -> incrementBlackCount();
        }
        return new Piece(color, type, representation);
    }

    // White Pieces
    public static Piece createWhitePawn() {
        return createPiece(Color.WHITE, Type.PAWN, PAWN_CHAR);
    }
    public static Piece createWhiteKnight() {
        return createPiece(Color.WHITE, Type.KNIGHT, KNIGHT_CHAR);
    }
    public static Piece createWhiteRook() {
        return createPiece(Color.WHITE, Type.ROOK, ROOK_CHAR);
    }
    public static Piece createWhiteBishop() {
        return createPiece(Color.WHITE, Type.BISHOP, BISHOP_CHAR);
    }
    public static Piece createWhiteQueen() {
        return createPiece(Color.WHITE, Type.QUEEN, QUEEN_CHAR);
    }
    public static Piece createWhiteKing() {
        return createPiece(Color.WHITE, Type.KING, KING_CHAR);
    }


    // Black Pieces
    public static Piece createBlackPawn() {
        return createPiece(Color.BLACK, Type.PAWN, Character.toUpperCase(PAWN_CHAR));
    }
    public static Piece createBlackKnight() {
        return createPiece(Color.BLACK, Type.KNIGHT, Character.toUpperCase(KNIGHT_CHAR));
    }
    public static Piece createBlackRook() {
        return createPiece(Color.BLACK, Type.ROOK, Character.toUpperCase(ROOK_CHAR));
    }
    public static Piece createBlackBishop() {
        return createPiece(Color.BLACK, Type.BISHOP, Character.toUpperCase(BISHOP_CHAR));
    }
    public static Piece createBlackQueen() {
        return createPiece(Color.BLACK, Type.QUEEN, Character.toUpperCase(QUEEN_CHAR));
    }
    public static Piece createBlackKing() {
        return createPiece(Color.BLACK, Type.KING, Character.toUpperCase(KING_CHAR));
    }


    public static void resetCount() {
        Piece.countWhite = 0;
        Piece.countBlack = 0;
    }
    public static void incrementWhiteCount() {
        Piece.countWhite++;
    }
    public static void incrementBlackCount() {
        Piece.countBlack++;
    }

    public static int getCountWhite() {
        return countWhite;
    }
    public static int getCountBlack() {
        return countBlack;
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

    public boolean isEqualTo(Piece that) {
        return (this.color == that.color) &&
                (this.type == that.type);
    }

    public int compareTo(Piece that) {
        if (this.strength > that.strength)
            return -1;
        if (this.strength < that.strength)
            return 1;
        return 0;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }
    public double getStrength() {
        return strength;
    }
}
