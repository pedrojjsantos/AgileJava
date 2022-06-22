package chess.pieces;

import chess.Board;
import util.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Piece implements Comparable<Piece>, Serializable {
    public boolean isEmpty() {
        return false;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public enum Color {WHITE, BLACK;}

    private final Color color;
    private final char representation;
    private final double strength;
    private String position = "";

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
    public String getPosition() {
        return position;
    }

}
