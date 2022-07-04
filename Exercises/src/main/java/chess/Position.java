package chess;

public class Position implements java.io.Serializable {
    private char file;
    private char rank;

    public Position(String strPos) {
        this.file = strPos.charAt(0);
        this.rank = strPos.charAt(1);
    }

    public Position(int file, int rank) {
        this.file = (char) (file + 'a');
        this.rank = (char) (rank + '1');
    }

    public Position(char file, char rank) {
        this.file = file;
        this.rank = rank;
    }

    public char getFileChar() {
        return file;
    }
    public char getRankChar() {
        return rank;
    }

    public int getFile() {
        return file - 'a';
    }
    public int getRank() {
        return rank - '1';
    }


    @Override
    public String toString() {
        return "" + file + rank;
    }

    @Override
    public boolean equals(Object that) {
        if (that == null || this.getClass() != that.getClass())
            return false;

        return this.toString().equals(that.toString());
    }
}
