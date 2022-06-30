package util;

import org.junit.Test;

import java.util.ArrayList;

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
                obj.getClass().getCanonicalName() + newline +
                        "\tname:     Jane            | String          public" + newline +
                        "\tnumber:   101             | Integer         public"
                , dump);

        obj = new TestObject2(name, number);
        dump = ObjectDumper.print(obj);

        assertEquals(
            obj.getClass().getCanonicalName() + newline +
                "\t" + "list:     " + "[0, 1, 2, 3, .. | " + "ArrayList       private" + newline +
                "\t" + "obj:      " + "                | " + "TestObject1     public" + newline +
                "\t-\t"  + "name:     " + "Jane            | " + "String          public" + newline +
                "\t-\t"  + "number:   " + "101             | " + "Integer         public" + newline +
                "\t" + "flag:     " + "true            | " + "Boolean         public static" + newline +
                "\t" + "id:       " + "200             | " + "Integer         protected"

                , dump);

//        Game game  = new Game();
//        game.initialize();
//        System.out.println(ObjectDumper.print(game));
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
        TestObject1 obj;
        static boolean flag = true;
        protected int id = 200;

        TestObject2(String name, int number) {
            list = new ArrayList<>();
            for (byte i = 0; i < 6; i++)
                list.add(i);

            obj = new TestObject1(name, number);
        }

        public ArrayList<Byte> getList() {
            return list;
        }
    }
}
