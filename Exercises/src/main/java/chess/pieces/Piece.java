package chess.pieces;

public class Piece implements Comparable<Piece> {
    public final static String WHITE = "white";
    public final static String BLACK = "black";

    public enum Color {WHITE, BLACK;}
    public enum Type {
        PAWN    (1.0, 'p'),
        KNIGHT  (2.5, 'n'),
        ROOK    (5.0, 'r'),
        BISHOP  (3.0, 'b'),
        QUEEN   (9.0, 'q'),
        KING    (0.0, 'k'),
        NO_PIECE(0.0, '.');

        private final double strength;
        private final char representation;

        Type(double strength, char representation){
            this.strength = strength;
            this.representation = representation;
        }

        private double getStrength() {
            return this.strength;
        }
        private char getCharRepresentation() {
            return this.representation;
        }
    }

    private static int countWhite;
    private static int countBlack;

    private final Type type;
    private final Color color;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public static Piece noPiece() {
        return new Piece(Color.WHITE, Type.NO_PIECE);
    }

    private static Piece createPiece(Color color, Type type) {
        switch (color) {
            case WHITE -> incrementWhiteCount();
            case BLACK -> incrementBlackCount();
        }
        return new Piece(color, type);
    }

    // White Pieces
    public static Piece createWhitePawn() {
        return createPiece(Color.WHITE, Type.PAWN);
    }
    public static Piece createWhiteKnight() {
        return createPiece(Color.WHITE, Type.KNIGHT);
    }
    public static Piece createWhiteRook() {
        return createPiece(Color.WHITE, Type.ROOK);
    }
    public static Piece createWhiteBishop() {
        return createPiece(Color.WHITE, Type.BISHOP);
    }
    public static Piece createWhiteQueen() {
        return createPiece(Color.WHITE, Type.QUEEN);
    }
    public static Piece createWhiteKing() {
        return createPiece(Color.WHITE, Type.KING);
    }


    // Black Pieces
    public static Piece createBlackPawn() {
        return createPiece(Color.BLACK, Type.PAWN);
    }
    public static Piece createBlackKnight() {
        return createPiece(Color.BLACK, Type.KNIGHT);
    }
    public static Piece createBlackRook() {
        return createPiece(Color.BLACK, Type.ROOK);
    }
    public static Piece createBlackBishop() {
        return createPiece(Color.BLACK, Type.BISHOP);
    }
    public static Piece createBlackQueen() {
        return createPiece(Color.BLACK, Type.QUEEN);
    }
    public static Piece createBlackKing() {
        return createPiece(Color.BLACK, Type.KING);
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
        return this.type.getCharRepresentation();
    }

    public boolean isEqualTo(Piece that) {
        return (this.color == that.color) &&
                (this.type == that.type);
    }

    public int compareTo(Piece that) {
        return Double.compare(that.getStrength(), this.getStrength());
    }

    public double getStrength() {
        return this.type.getStrength();
    }
}
