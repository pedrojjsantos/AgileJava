package chess.pieces;

import chess.Board;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Piece implements Comparable<Piece> {
    public final static String WHITE = "white";
    public final static String BLACK = "black";

    public enum Color {WHITE, BLACK;}
//    public enum Type {
//        PAWN    (1.0, 'p'),
//        KNIGHT  (2.5, 'n'),
//        ROOK    (5.0, 'r'),
//        BISHOP  (3.0, 'b'),
//        QUEEN   (9.0, 'q'),
//        KING    (0.0, 'k'),
//        NO_PIECE(0.0, '.');
//
//        private final double strength;
//        private final char representation;
//
//        Type(double strength, char representation){
//            this.strength = strength;
//            this.representation = representation;
//        }
//
//        private double getStrength() {
//            return this.strength;
//        }
//        private char getCharRepresentation() {
//            return this.representation;
//        }
//    }
//
//    private final Type type;
    private final Color color;
    private final char representation;
    private final double strength;

    protected Piece(Color color, char representation, double strength) {
        this.color = color;
        this.strength = strength;
        this.representation = representation;
    }

    public static NoPiece noPiece() {
        return new NoPiece();
    }

    // White Pieces
    public static Pawn createWhitePawn() {
        return new Pawn(Color.WHITE);
    }
    public static Knight createWhiteKnight() {
        return new Knight(Color.WHITE);
    }
    public static Rook createWhiteRook() {
        return new Rook(Color.WHITE);
    }
    public static Bishop createWhiteBishop() {
        return new Bishop(Color.WHITE);
    }
    public static Queen createWhiteQueen() {
        return new Queen(Color.WHITE);
    }
    public static King createWhiteKing() {
        return new King(Color.WHITE);
    }


    // Black Pieces
    public static Pawn createBlackPawn() {
        return new Pawn(Color.BLACK);
    }
    public static Knight createBlackKnight() {
        return new Knight(Color.BLACK);
    }
    public static Rook createBlackRook() {
        return new Rook(Color.BLACK);
    }
    public static Bishop createBlackBishop() {
        return new Bishop(Color.BLACK);
    }
    public static Queen createBlackQueen() {
        return new Queen(Color.BLACK);
    }
    public static King createBlackKing() {
        return new King(Color.BLACK);
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public char print() {
        return this.representation;
    }

    public boolean isEqualTo(Piece that) {
        return this.getClass() == that.getClass() &&
                this.color == that.color;
    }

    public int compareTo(Piece that) {
        return Double.compare(that.getStrength(), this.getStrength());
    }

    public double getStrength() {
        return strength;
    }

}
