package util;

import org.junit.Test;

import java.lang.reflect.*;

import static org.junit.Assert.*;

public class ObjectDumperTest {
    @Test
    public void testDump() {
        final String name = "Jane";
        final int number = 101;
        final String newline = "%n".formatted();
        Object obj = new TestObject(name, number);

        String dump = ObjectDumper.print(obj);
        assertEquals(
                "obj:" + newline +
                        "\tname:\tJane" + newline +
                        "\tnumber\t101" + newline
                , dump);
    }

    class TestObject {
        String name;
        int number;

        TestObject(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }
}
