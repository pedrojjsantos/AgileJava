package util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ObjectDumperTest {
    @Test
    public void testDump() throws IllegalAccessException{
        final String name = "Jane";
        final int number = 101;
        final String newline = "%n".formatted();
        Object obj = new TestObject1(name, number);

        String dump = ObjectDumper.print(obj);
        assertEquals(
                obj.getClass().getName() + newline +
                        "\tname:     Jane            | String                      public" + newline +
                        "\tnumber:   101             | int                         public"
                , dump);

        obj = new TestObject2(name, number);
        dump = ObjectDumper.print(obj);

        assertEquals(
            obj.getClass().getName() + newline +
                "\t" + "list:     " + "[0, 1, 2, 3, .. | " + "ArrayList<Byte>             private" + newline +
                "\t" + "hashmap:  " + "{1=1, 2=4, 3=9} | " + "HashMap<String,Short>       private" + newline +
                "\t" + "obj:      " + "                | " + "TestObject1                 public" + newline +
                "\t-\t"  + "name:     " + "Jane            | " + "String                      public" + newline +
                "\t-\t"  + "number:   " + "101             | " + "int                         public" + newline +
                "\t" + "flag:     " + "true            | " + "boolean                     public static" + newline +
                "\t" + "id:       " + "200             | " + "Integer                     protected"

                , dump);
    }

    static class TestObject1 {
        String name;
        int number;

        TestObject1(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }
    static class TestObject2 {
        private final ArrayList<Byte> list;
        private HashMap<String, Short> hashmap;
        TestObject1 obj;
        static boolean flag = true;
        protected Integer id = 200;

        TestObject2(String name, int number) {
            list = new ArrayList<>();
            for (byte i = 0; i < 6; i++)
                list.add(i);

            hashmap = new HashMap<>();
            hashmap.put("1", (short) 1);
            hashmap.put("2", (short) 4);
            hashmap.put("3", (short) 9);

            obj = new TestObject1(name, number);
        }

        public ArrayList<Byte> getList() {
            return list;
        }
    }
}
