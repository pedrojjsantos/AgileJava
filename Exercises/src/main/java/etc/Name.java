package etc;

public class Name {
    private final String string;

    public Name(String str) {
        this.string = str;
    }

    public String getString() {
        return string;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Name.class)
            return false;
        Name that = (Name) obj;
        return this.string.equals(that.string);
    }

    @Override
    public int hashCode() {
        return string.hashCode();
    }
}
