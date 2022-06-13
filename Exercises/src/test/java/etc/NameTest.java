package etc;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class NameTest {
    @Test
    public void testCreate() {
        Name foo = new Name("Foo");
        assertEquals("Foo", foo.getString());
    }

    @Test
    public void testEquality() {
        Name a1 = new Name("Hello!");
        Name a2 = new Name("Hello!");
        Name a3 = new Name("Hello!");

        Name b = new Name("Bye!");

        assertNotEquals(a1, b);
        assertNotEquals(a2, b);
        assertNotEquals(a3, b);

        // reflexivity
        assertEquals(a1, a1);

        // transitivity
        assertEquals(a1, a2);
        assertEquals(a2, a3);
        assertEquals(a1, a3);

        // symmetry
        assertEquals(a1, a2);
        assertEquals(a2, a1);

        // consistency
        assertEquals(a1, a2);

        // comparison to null
        assertFalse(a1.equals(null));

        // apples & oranges
        assertFalse(a1.equals(34));
    }

    @Test
    public void testSetInsert() {
        Set<Name> set = new HashSet<>();
        set.add(new Name(      "i"      ));
        set.add(new Name(     "iii"     ));
        set.add(new Name(    "iiiii"    ));
        set.add(new Name(   "iiiiiii"   ));
        set.add(new Name(  "iiiiiiiii"  ));
        set.add(new Name( "iiiiiiiiiii" ));
        set.add(new Name("iiiiiiiiiiiii"));

        set.add(new Name("Foo"));
        assertTrue(set.contains(new Name("Foo")));

        Name foo = new Name("Foo");
        assertTrue(set.contains(foo));
    }
}
