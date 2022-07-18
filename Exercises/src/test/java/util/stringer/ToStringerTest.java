package util.stringer;

import org.junit.Test;

public class ToStringerTest {
    @Test
    public void testDump() {
        String result = toStringer.dump(new TestDumpOne(123, "hey"));

    }

    static class TestDumpOne {
        int num;
        String msg;
    }
}
