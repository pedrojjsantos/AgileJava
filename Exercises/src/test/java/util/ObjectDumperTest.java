package util;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ObjectDumperTest {
    @Test
    public void testDump() throws IllegalAccessException{
        final String name = "Jane";
        final int number = 101;
        final String format = "%n\t%-10s %-10s %-25s %s";
        Object obj = new TestObject1(name, number);

        String dump = ObjectDumper.print(obj);
        assertEquals(
                obj.getClass().getCanonicalName() +
                        format.formatted(
                                "name:", "Jane", String.class.getCanonicalName(), "public") +
                        format.formatted(
                                "number:", "101", Integer.class.getCanonicalName(), "public")
                , dump);

        obj = new TestObject2(name, number);
        dump = ObjectDumper.print(obj);
        assertEquals(
                obj.getClass().getCanonicalName() +
                        format.formatted(
                                "name:", "Jane", String.class.getCanonicalName(), "public") +
                        format.formatted(
                                "number:", "101", Integer.class.getCanonicalName(), "public")
                , dump);
    }

    class TestObject1 {
        String name;
        int number;

        TestObject1(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }
    class TestObject2 {
        private ArrayList<Byte> list;
        TestObject1 obj;
        static boolean flag = true;

        TestObject2(String name, int number) {
            list = new ArrayList<>();
            for (byte i = 0; i < 5; i++)
                list.add(i);

            obj = new TestObject1(name, number);
        }
    }
}
